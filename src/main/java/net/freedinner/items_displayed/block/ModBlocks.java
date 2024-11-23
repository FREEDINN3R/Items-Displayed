package net.freedinner.items_displayed.block;

import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.block.custom.stackable.*;
import net.freedinner.items_displayed.item.custom.DebugBlockItem;
import net.freedinner.items_displayed.util.ModTemplates;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block ANGLER_POTTERY_SHERD = registerBlock("angler_pottery_sherd",
            ModTemplates.defaultSherdBlock("angler_pottery_sherd"));
    public static final Block ARCHER_POTTERY_SHERD = registerBlock("archer_pottery_sherd",
            ModTemplates.defaultSherdBlock("archer_pottery_sherd"));
    public static final Block ARMS_UP_POTTERY_SHERD = registerBlock("arms_up_pottery_sherd",
            ModTemplates.defaultSherdBlock("arms_up_pottery_sherd"));
    public static final Block BLADE_POTTERY_SHERD = registerBlock("blade_pottery_sherd",
            ModTemplates.defaultSherdBlock("blade_pottery_sherd"));
    public static final Block BREWER_POTTERY_SHERD = registerBlock("brewer_pottery_sherd",
            ModTemplates.defaultSherdBlock("brewer_pottery_sherd"));
    public static final Block BURN_POTTERY_SHERD = registerBlock("burn_pottery_sherd",
            ModTemplates.defaultSherdBlock("burn_pottery_sherd"));
    public static final Block DANGER_POTTERY_SHERD = registerBlock("danger_pottery_sherd",
            ModTemplates.defaultSherdBlock("danger_pottery_sherd"));
    public static final Block EXPLORER_POTTERY_SHERD = registerBlock("explorer_pottery_sherd",
            ModTemplates.defaultSherdBlock("explorer_pottery_sherd"));
    public static final Block FLOW_POTTERY_SHERD = registerBlock("flow_pottery_sherd",
            ModTemplates.defaultSherdBlock("flow_pottery_sherd"));
    public static final Block FRIEND_POTTERY_SHERD = registerBlock("friend_pottery_sherd",
            ModTemplates.defaultSherdBlock("friend_pottery_sherd"));
    public static final Block GUSTER_POTTERY_SHERD = registerBlock("guster_pottery_sherd",
            ModTemplates.defaultSherdBlock("guster_pottery_sherd"));
    public static final Block HEART_POTTERY_SHERD = registerBlock("heart_pottery_sherd",
            ModTemplates.defaultSherdBlock("heart_pottery_sherd"));
    public static final Block HEARTBREAK_POTTERY_SHERD = registerBlock("heartbreak_pottery_sherd",
            ModTemplates.defaultSherdBlock("heartbreak_pottery_sherd"));
    public static final Block HOWL_POTTERY_SHERD = registerBlock("howl_pottery_sherd",
            ModTemplates.defaultSherdBlock("howl_pottery_sherd"));
    public static final Block MINER_POTTERY_SHERD = registerBlock("miner_pottery_sherd",
            ModTemplates.defaultSherdBlock("miner_pottery_sherd"));
    public static final Block MOURNER_POTTERY_SHERD = registerBlock("mourner_pottery_sherd",
            ModTemplates.defaultSherdBlock("mourner_pottery_sherd"));
    public static final Block PLENTY_POTTERY_SHERD = registerBlock("plenty_pottery_sherd",
            ModTemplates.defaultSherdBlock("plenty_pottery_sherd"));
    public static final Block PRIZE_POTTERY_SHERD = registerBlock("prize_pottery_sherd",
            ModTemplates.defaultSherdBlock("prize_pottery_sherd"));
    public static final Block SCRAPE_POTTERY_SHERD = registerBlock("scrape_pottery_sherd",
            ModTemplates.defaultSherdBlock("scrape_pottery_sherd"));
    public static final Block SHEAF_POTTERY_SHERD = registerBlock("sheaf_pottery_sherd",
            ModTemplates.defaultSherdBlock("sheaf_pottery_sherd"));
    public static final Block SHELTER_POTTERY_SHERD = registerBlock("shelter_pottery_sherd",
            ModTemplates.defaultSherdBlock("shelter_pottery_sherd"));
    public static final Block SKULL_POTTERY_SHERD = registerBlock("skull_pottery_sherd",
            ModTemplates.defaultSherdBlock("skull_pottery_sherd"));
    public static final Block SNORT_POTTERY_SHERD = registerBlock("snort_pottery_sherd",
            ModTemplates.defaultSherdBlock("snort_pottery_sherd"));

    public static final Block NETHERITE_UPGRADE = registerBlock("netherite_upgrade",
            ModTemplates.defaultUpgradeBlock("netherite_upgrade"));
    public static final Block BOLT_ARMOR_TRIM = registerBlock("bolt_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.COPPER, "bolt_armor_trim"));
    public static final Block COAST_ARMOR_TRIM = registerBlock("coast_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.STONE, "coast_armor_trim"));
    public static final Block DUNE_ARMOR_TRIM = registerBlock("dune_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.DRIPSTONE_BLOCK, "dune_armor_trim"));
    public static final Block EYE_ARMOR_TRIM = registerBlock("eye_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.CALCITE, "eye_armor_trim"));
    public static final Block FLOW_ARMOR_TRIM = registerBlock("flow_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.DEEPSLATE_TILES, "flow_armor_trim"));
    public static final Block HOST_ARMOR_TRIM = registerBlock("host_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.GRAVEL, "host_armor_trim"));
    public static final Block RAISER_ARMOR_TRIM = registerBlock("raiser_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.GRAVEL, "raiser_armor_trim"));
    public static final Block RIB_ARMOR_TRIM = registerBlock("rib_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.NETHER_BRICKS, "rib_armor_trim"));
    public static final Block SENTRY_ARMOR_TRIM = registerBlock("sentry_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.STONE, "sentry_armor_trim"));
    public static final Block SHAPER_ARMOR_TRIM = registerBlock("shaper_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.GRAVEL, "shaper_armor_trim"));
    public static final Block SILENCE_ARMOR_TRIM = registerBlock("silence_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.DEEPSLATE, "silence_armor_trim"));
    public static final Block SNOUT_ARMOR_TRIM = registerBlock("snout_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.POLISHED_DEEPSLATE, "snout_armor_trim"));
    public static final Block SPIRE_ARMOR_TRIM = registerBlock("spire_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.DEEPSLATE_BRICKS, "spire_armor_trim"));
    public static final Block TIDE_ARMOR_TRIM = registerBlock("tide_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.DEEPSLATE_BRICKS, "tide_armor_trim"));
    public static final Block VEX_ARMOR_TRIM = registerBlock("vex_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.STONE, "vex_armor_trim"));
    public static final Block WARD_ARMOR_TRIM = registerBlock("ward_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.DEEPSLATE, "ward_armor_trim"));
    public static final Block WAYFINDER_ARMOR_TRIM = registerBlock("wayfinder_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.GRAVEL, "wayfinder_armor_trim"));
    public static final Block WILD_ARMOR_TRIM = registerBlock("wild_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.STONE, "wild_armor_trim"));

    public static final Block BRICK = registerBlock("brick",
            ModTemplates.defaultIngotBlock(MapColor.RED, false, "brick"));
    public static final Block NETHER_BRICK = registerBlock("nether_brick",
            ModTemplates.defaultIngotBlock(MapColor.DARK_RED, false, "nether_brick"));
    public static final Block IRON_INGOT = registerBlock("iron_ingot",
            ModTemplates.defaultIngotBlock(MapColor.IRON_GRAY, true, "iron_ingot"));
    public static final Block COPPER_INGOT = registerBlock("copper_ingot",
            ModTemplates.defaultIngotBlock(MapColor.ORANGE, true, "copper_ingot"));
    public static final Block GOLD_INGOT = registerBlock("gold_ingot",
            ModTemplates.defaultIngotBlock(MapColor.GOLD, true, "gold_ingot"));
    public static final Block NETHERITE_INGOT = registerBlock("netherite_ingot",
            ModTemplates.defaultIngotBlock(MapColor.BLACK, true, "netherite_ingot"));

    public static final Block COAL = registerBlock("coal",
            new DiamondItemBlock(ModTemplates.defaultGemstoneSettings(MapColor.BLACK, "coal")));
    public static final Block CHARCOAL = registerBlock("charcoal",
            new DiamondItemBlock(ModTemplates.defaultGemstoneSettings(MapColor.BLACK, "charcoal")));
    public static final Block LAPIS_LAZULI = registerBlock("lapis_lazuli",
            new LapisLazuliItemBlock(ModTemplates.defaultGemstoneSettings(MapColor.LAPIS_BLUE, "lapis_lazuli")));
    public static final Block AMETHYST_SHARD = registerBlock("amethyst_shard",
            new CrystalItemBlock(ModTemplates.defaultGemstoneSettings(MapColor.PURPLE, "amethyst_shard")));
    public static final Block DIAMOND = registerBlock("diamond",
            new DiamondItemBlock(ModTemplates.defaultGemstoneSettings(MapColor.DIAMOND_BLUE, "diamond")));
    public static final Block EMERALD = registerBlock("emerald",
            new EmeraldItemBlock(ModTemplates.defaultGemstoneSettings(MapColor.EMERALD_GREEN, "emerald")));
    public static final Block ECHO_SHARD = registerBlock("echo_shard",
            new CrystalItemBlock(ModTemplates.defaultGemstoneSettings(MapColor.BLACK, "echo_shard").sounds(BlockSoundGroup.SCULK_SHRIEKER)));

    public static final Block MUSIC_DISC_13 = registerBlock("music_disc_13",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_13"));
    public static final Block MUSIC_DISC_CAT = registerBlock("music_disc_cat",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_cat"));
    public static final Block MUSIC_DISC_BLOCKS = registerBlock("music_disc_blocks",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_blocks"));
    public static final Block MUSIC_DISC_CHIRP = registerBlock("music_disc_chirp",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_chirp"));
    public static final Block MUSIC_DISC_FAR = registerBlock("music_disc_far",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_far"));
    public static final Block MUSIC_DISC_MALL = registerBlock("music_disc_mall",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_mall"));
    public static final Block MUSIC_DISC_MELLOHI = registerBlock("music_disc_mellohi",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_mellohi"));
    public static final Block MUSIC_DISC_STAL = registerBlock("music_disc_stal",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_stal"));
    public static final Block MUSIC_DISC_STRAD = registerBlock("music_disc_strad",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_strad"));
    public static final Block MUSIC_DISC_WARD = registerBlock("music_disc_ward",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_ward"));
    public static final Block MUSIC_DISC_11 = registerBlock("music_disc_11",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_11"));
    public static final Block MUSIC_DISC_WAIT = registerBlock("music_disc_wait",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_wait"));
    public static final Block MUSIC_DISC_PIGSTEP = registerBlock("music_disc_pigstep",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_pigstep"));
    public static final Block MUSIC_DISC_OTHERSIDE = registerBlock("music_disc_otherside",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_otherside"));
    public static final Block MUSIC_DISC_5 = registerBlock("music_disc_5",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.BAMBOO_WOOD, "music_disc_5"));
    public static final Block MUSIC_DISC_RELIC = registerBlock("music_disc_relic",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.DECORATED_POT, "music_disc_relic"));
    public static final Block MUSIC_DISC_CREATOR = registerBlock("music_disc_creator",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.COPPER, "music_disc_creator"));
    public static final Block MUSIC_DISC_CREATOR_MUSIC_BOX = registerBlock("music_disc_creator_music_box",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.COPPER, "music_disc_creator_music_box"));
    public static final Block MUSIC_DISC_PRECIPICE = registerBlock("music_disc_precipice",
            ModTemplates.defaultDiscBlock(BlockSoundGroup.STONE, "music_disc_precipice"));
    public static final Block DISC_FRAGMENT_5 = registerBlock("disc_fragment_5",
            ModTemplates.defaultDiscFragmentBlock("disc_fragment_5"));

    private static Block registerBlock(String name, Block block) {
        Block registeredBlock = Registry.register(Registries.BLOCK, ItemsDisplayed.id(name), block);
        registerDebugItem(name, registeredBlock);

        return registeredBlock;
    }

    private static void registerDebugItem(String name, Block block) {
        DebugBlockItem debugBlockItem = new DebugBlockItem(block, new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ItemsDisplayed.MOD_ID, name))));
        Registry.register(Registries.ITEM, ItemsDisplayed.id(name), debugBlockItem);
    }

    public static void registerBlocks() {
        ItemsDisplayed.LOGGER.info("Registering blocks");
    }
}
