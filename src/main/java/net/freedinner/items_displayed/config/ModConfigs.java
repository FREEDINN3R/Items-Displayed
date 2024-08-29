package net.freedinner.items_displayed.config;

import net.freedinner.items_displayed.ItemsDisplayed;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;

public class ModConfigs {
    public static SimpleConfig CONFIG;

    public static boolean APPEND_ITEM_TOOLTIPS;
    private static final String APPEND_ITEM_TOOLTIPS_KEY = "append_item_tooltips";
    private static final boolean APPEND_ITEM_TOOLTIPS_DEFAULT = true;

    public static boolean APPEND_EXTRA_TOOLTIPS;
    private static final String APPEND_EXTRA_TOOLTIPS_KEY = "append_extra_tooltips";
    private static final boolean APPEND_EXTRA_TOOLTIPS_DEFAULT = true;

    public static int ENTITY_ROTATION_ANGLE;
    private static final String ENTITY_ROTATION_ANGLE_KEY = "entity_rotation_angle";
    private static final int ENTITY_ROTATION_ANGLE_DEFAULT = 15;

    public static ArrayList<String> BLACKLISTED_ITEMS;
    private static final String BLACKLISTED_ITEMS_KEY = "blacklisted_items";
    private static final String BLACKLISTED_ITEMS_DEFAULT = "[ 'minecraft:some_item', 'minecraft:another_item' ]";

    public static void registerConfigs() {
        ItemsDisplayed.LOGGER.info("Registering configs");

        ModConfigProvider configProvider = createConfigProvider();
        CONFIG = SimpleConfig.of(ItemsDisplayed.MOD_ID + "_config").provider(configProvider).request();

        assignConfigs();
    }

    private static ModConfigProvider createConfigProvider() {
        ModConfigProvider configProvider = new ModConfigProvider();

        configProvider.addComment("Whether to add helper tooltips to all placeable items");
        configProvider.addComment("Value = true or false");
        configProvider.addField(APPEND_ITEM_TOOLTIPS_KEY, APPEND_ITEM_TOOLTIPS_DEFAULT);
        configProvider.addComment("");

        configProvider.addComment("Whether to add helper tooltips to other items added by the mod");
        configProvider.addComment("Value = true or false");
        configProvider.addField(APPEND_EXTRA_TOOLTIPS_KEY, APPEND_EXTRA_TOOLTIPS_DEFAULT);
        configProvider.addComment("");

        configProvider.addComment("Min angle by which entities from this mod (e.g., Item Display) can be rotated");
        configProvider.addComment("Example: for Armor Stands this value is 45, and its rotation snaps to the closest 45-degree angle");
        configProvider.addComment("Min value = 1, max value = 90");
        configProvider.addComment("Use only the divisors of 90 (3, 5, 15, etc.) to prevent strange behavior");
        configProvider.addField(ENTITY_ROTATION_ANGLE_KEY, ENTITY_ROTATION_ANGLE_DEFAULT);
        configProvider.addComment("");

        configProvider.addComment("Blacklisted items which the player shouldn't be able to place");
        configProvider.addComment("Use this option in case of incompatibilities with other mods");
        configProvider.addField(BLACKLISTED_ITEMS_KEY, BLACKLISTED_ITEMS_DEFAULT);

        return configProvider;
    }

    private static void assignConfigs() {
        APPEND_ITEM_TOOLTIPS = CONFIG.getOrDefault(APPEND_ITEM_TOOLTIPS_KEY, APPEND_ITEM_TOOLTIPS_DEFAULT);
        APPEND_EXTRA_TOOLTIPS = CONFIG.getOrDefault(APPEND_EXTRA_TOOLTIPS_KEY, APPEND_EXTRA_TOOLTIPS_DEFAULT);
        ENTITY_ROTATION_ANGLE = MathHelper.clamp(
                CONFIG.getOrDefault(ENTITY_ROTATION_ANGLE_KEY, ENTITY_ROTATION_ANGLE_DEFAULT), 1, 90
        );
        BLACKLISTED_ITEMS = configArrayToList(
                CONFIG.getOrDefault(BLACKLISTED_ITEMS_KEY, BLACKLISTED_ITEMS_DEFAULT)
        );
    }

    private static ArrayList<String> configArrayToList(String s) {
        ArrayList<String> list = new ArrayList<>();

        int searchPos = 0;
        while (searchPos < s.length() && s.indexOf("'", searchPos) != -1) {
            int startPos = s.indexOf("'", searchPos) + 1;
            int endPos = s.indexOf("'", startPos);

            if (endPos == -1) {
                break;
            }

            list.add(s.substring(startPos, endPos));
            searchPos = endPos + 1;
        }

        return list;
    }
}