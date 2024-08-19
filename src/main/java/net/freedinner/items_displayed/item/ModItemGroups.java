package net.freedinner.items_displayed.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.freedinner.items_displayed.ItemsDisplayed;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class ModItemGroups {
    public static ItemGroup ITEMS_DISPLAYED;

    static {
        ITEMS_DISPLAYED = Registry.register(
                Registries.ITEM_GROUP,
                ItemsDisplayed.id("item_group"),
                FabricItemGroup.builder()
                        .displayName(Text.translatable("item.items_displayed.item_group_name"))
                        .icon(() -> new ItemStack(ModItems.ITEM_DISPLAY))
                        .entries((displayContext, entries) -> {
                            entries.add(ModItems.ITEM_DISPLAY);

                            entries.add(Items.ANGLER_POTTERY_SHERD);
                            entries.add(Items.ARCHER_POTTERY_SHERD);
                            entries.add(Items.ARMS_UP_POTTERY_SHERD);
                            entries.add(Items.BLADE_POTTERY_SHERD);
                            entries.add(Items.BREWER_POTTERY_SHERD);
                            entries.add(Items.BURN_POTTERY_SHERD);
                            entries.add(Items.DANGER_POTTERY_SHERD);
                            entries.add(Items.EXPLORER_POTTERY_SHERD);
                            entries.add(Items.FLOW_POTTERY_SHERD);
                            entries.add(Items.FRIEND_POTTERY_SHERD);
                            entries.add(Items.GUSTER_POTTERY_SHERD);
                            entries.add(Items.HEART_POTTERY_SHERD);
                            entries.add(Items.HEARTBREAK_POTTERY_SHERD);
                            entries.add(Items.HOWL_POTTERY_SHERD);
                            entries.add(Items.MINER_POTTERY_SHERD);
                            entries.add(Items.MOURNER_POTTERY_SHERD);
                            entries.add(Items.PLENTY_POTTERY_SHERD);
                            entries.add(Items.PRIZE_POTTERY_SHERD);
                            entries.add(Items.SCRAPE_POTTERY_SHERD);
                            entries.add(Items.SHEAF_POTTERY_SHERD);
                            entries.add(Items.SHELTER_POTTERY_SHERD);
                            entries.add(Items.SKULL_POTTERY_SHERD);
                            entries.add(Items.SNORT_POTTERY_SHERD);

                            entries.add(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
                            entries.add(Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE);
                            entries.add(Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE);

                            entries.add(Items.BRICK);
                            entries.add(Items.NETHER_BRICK);
                            entries.add(Items.IRON_INGOT);
                            entries.add(Items.COPPER_INGOT);
                            entries.add(Items.GOLD_INGOT);
                            entries.add(Items.NETHERITE_INGOT);

                            entries.add(Items.DIAMOND);
                            entries.add(Items.EMERALD);
                            entries.add(Items.LAPIS_LAZULI);
                        })
                        .build()
        );
    }

    public static void registerItemGroups() {
        ItemsDisplayed.LOGGER.info("Registering item groups");
    }
}
