package net.freedinner.items_displayed.item;

import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.item.custom.ItemDisplayItem;
import net.freedinner.items_displayed.item.custom.JewelryPillowItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item ITEM_DISPLAY = register("item_display",
            new ItemDisplayItem(defaultSettings("item_display")));

    public static final Item WHITE_JEWELRY_PILLOW = register("white_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("white_jewelry_pillow"), DyeColor.WHITE));
    public static final Item LIGHT_GRAY_JEWELRY_PILLOW = register("light_gray_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("light_gray_jewelry_pillow"), DyeColor.LIGHT_GRAY));
    public static final Item GRAY_JEWELRY_PILLOW = register("gray_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("gray_jewelry_pillow"), DyeColor.GRAY));
    public static final Item BLACK_JEWELRY_PILLOW = register("black_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("black_jewelry_pillow"), DyeColor.BLACK));
    public static final Item RED_JEWELRY_PILLOW = register("red_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("red_jewelry_pillow"), DyeColor.RED));
    public static final Item ORANGE_JEWELRY_PILLOW = register("orange_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("orange_jewelry_pillow"), DyeColor.ORANGE));
    public static final Item YELLOW_JEWELRY_PILLOW = register("yellow_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("yellow_jewelry_pillow"), DyeColor.YELLOW));
    public static final Item LIME_JEWELRY_PILLOW = register("lime_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("lime_jewelry_pillow"), DyeColor.LIME));
    public static final Item GREEN_JEWELRY_PILLOW = register("green_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("green_jewelry_pillow"), DyeColor.GREEN));
    public static final Item LIGHT_BLUE_JEWELRY_PILLOW = register("light_blue_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("light_blue_jewelry_pillow"), DyeColor.LIGHT_BLUE));
    public static final Item CYAN_JEWELRY_PILLOW = register("cyan_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("cyan_jewelry_pillow"), DyeColor.CYAN));
    public static final Item BLUE_JEWELRY_PILLOW = register("blue_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("blue_jewelry_pillow"), DyeColor.BLUE));
    public static final Item PURPLE_JEWELRY_PILLOW = register("purple_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("purple_jewelry_pillow"), DyeColor.PURPLE));
    public static final Item MAGENTA_JEWELRY_PILLOW = register("magenta_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("magenta_jewelry_pillow"), DyeColor.MAGENTA));
    public static final Item PINK_JEWELRY_PILLOW = register("pink_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("pink_jewelry_pillow"), DyeColor.PINK));
    public static final Item BROWN_JEWELRY_PILLOW = register("brown_jewelry_pillow",
            new JewelryPillowItem(defaultSettings("brown_jewelry_pillow"), DyeColor.BROWN));

    private static Item.Settings defaultSettings(String name) {
        return new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ItemsDisplayed.MOD_ID, name)));
    }

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, ItemsDisplayed.id(name), item);
    }

    public static void registerItems() {
        ItemsDisplayed.LOGGER.info("Registering items");

        FuelRegistryEvents.BUILD.register((builder, context) -> builder.add(ITEM_DISPLAY, 250));
    }
}
