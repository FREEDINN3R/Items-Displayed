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
                            // Custom items
                            entries.add(ModItems.ITEM_DISPLAY);
                            entries.add(ModItems.WHITE_JEWELRY_PILLOW);
                            entries.add(ModItems.LIGHT_GRAY_JEWELRY_PILLOW);
                            entries.add(ModItems.GRAY_JEWELRY_PILLOW);
                            entries.add(ModItems.BLACK_JEWELRY_PILLOW);
                            entries.add(ModItems.BROWN_JEWELRY_PILLOW);
                            entries.add(ModItems.RED_JEWELRY_PILLOW);
                            entries.add(ModItems.ORANGE_JEWELRY_PILLOW);
                            entries.add(ModItems.YELLOW_JEWELRY_PILLOW);
                            entries.add(ModItems.LIME_JEWELRY_PILLOW);
                            entries.add(ModItems.GREEN_JEWELRY_PILLOW);
                            entries.add(ModItems.LIGHT_BLUE_JEWELRY_PILLOW);
                            entries.add(ModItems.CYAN_JEWELRY_PILLOW);
                            entries.add(ModItems.BLUE_JEWELRY_PILLOW);
                            entries.add(ModItems.PURPLE_JEWELRY_PILLOW);
                            entries.add(ModItems.MAGENTA_JEWELRY_PILLOW);
                            entries.add(ModItems.PINK_JEWELRY_PILLOW);

                            // Gems & crystals
                            entries.add(Items.COAL);
                            entries.add(Items.CHARCOAL);
                            entries.add(Items.LAPIS_LAZULI);
                            entries.add(Items.AMETHYST_SHARD);
                            entries.add(Items.DIAMOND);
                            entries.add(Items.EMERALD);
                            entries.add(Items.ECHO_SHARD);

                            // Ingots & bricks
                            entries.add(Items.BRICK);
                            entries.add(Items.NETHER_BRICK);
                            entries.add(Items.IRON_INGOT);
                            entries.add(Items.COPPER_INGOT);
                            entries.add(Items.GOLD_INGOT);
                            entries.add(Items.NETHERITE_INGOT);

                            // Sherds
                            entries.add(Items.ANGLER_POTTERY_SHERD);
                            entries.add(Items.ARCHER_POTTERY_SHERD);
                            entries.add(Items.ARMS_UP_POTTERY_SHERD);
                            entries.add(Items.BLADE_POTTERY_SHERD);
                            entries.add(Items.BREWER_POTTERY_SHERD);
                            entries.add(Items.BURN_POTTERY_SHERD);
                            entries.add(Items.DANGER_POTTERY_SHERD);
                            entries.add(Items.EXPLORER_POTTERY_SHERD);
                            entries.add(Items.FRIEND_POTTERY_SHERD);
                            entries.add(Items.HEART_POTTERY_SHERD);
                            entries.add(Items.HEARTBREAK_POTTERY_SHERD);
                            entries.add(Items.HOWL_POTTERY_SHERD);
                            entries.add(Items.MINER_POTTERY_SHERD);
                            entries.add(Items.MOURNER_POTTERY_SHERD);
                            entries.add(Items.PLENTY_POTTERY_SHERD);
                            entries.add(Items.PRIZE_POTTERY_SHERD);
                            entries.add(Items.SHEAF_POTTERY_SHERD);
                            entries.add(Items.SHELTER_POTTERY_SHERD);
                            entries.add(Items.SKULL_POTTERY_SHERD);
                            entries.add(Items.SNORT_POTTERY_SHERD);

                            // Templates
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

                            // Discs
                            entries.add(Items.MUSIC_DISC_13);
                            entries.add(Items.MUSIC_DISC_CAT);
                            entries.add(Items.MUSIC_DISC_BLOCKS);
                            entries.add(Items.MUSIC_DISC_CHIRP);
                            entries.add(Items.MUSIC_DISC_FAR);
                            entries.add(Items.MUSIC_DISC_MALL);
                            entries.add(Items.MUSIC_DISC_MELLOHI);
                            entries.add(Items.MUSIC_DISC_STAL);
                            entries.add(Items.MUSIC_DISC_STRAD);
                            entries.add(Items.MUSIC_DISC_WARD);
                            entries.add(Items.MUSIC_DISC_11);
                            entries.add(Items.MUSIC_DISC_WAIT);
                            entries.add(Items.MUSIC_DISC_OTHERSIDE);
                            entries.add(Items.MUSIC_DISC_RELIC);
                            entries.add(Items.MUSIC_DISC_5);
                            entries.add(Items.MUSIC_DISC_PIGSTEP);
                            entries.add(Items.DISC_FRAGMENT_5);
                        })
                        .build()
        );
    }

    public static void registerItemGroups() {
        ItemsDisplayed.LOGGER.info("Registering item groups");
    }
}
