package net.freedinner.items_displayed.block;

import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.block.custom.stackable.CrystalItemBlock;
import net.freedinner.items_displayed.block.custom.stackable.DiamondItemBlock;
import net.freedinner.items_displayed.block.custom.stackable.EmeraldItemBlock;
import net.freedinner.items_displayed.block.custom.stackable.LapisLazuliItemBlock;
import net.freedinner.items_displayed.item.custom.DebugBlockItem;
import net.freedinner.items_displayed.util.ModTemplates;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public class ModBlocks {
    public static final Block ANGLER_POTTERY_SHERD = registerBlock("angler_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block ARCHER_POTTERY_SHERD = registerBlock("archer_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block ARMS_UP_POTTERY_SHERD = registerBlock("arms_up_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block BLADE_POTTERY_SHERD = registerBlock("blade_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block BREWER_POTTERY_SHERD = registerBlock("brewer_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block BURN_POTTERY_SHERD = registerBlock("burn_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block DANGER_POTTERY_SHERD = registerBlock("danger_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block EXPLORER_POTTERY_SHERD = registerBlock("explorer_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block FLOW_POTTERY_SHERD = registerBlock("flow_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block FRIEND_POTTERY_SHERD = registerBlock("friend_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block GUSTER_POTTERY_SHERD = registerBlock("guster_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block HEART_POTTERY_SHERD = registerBlock("heart_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block HEARTBREAK_POTTERY_SHERD = registerBlock("heartbreak_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block HOWL_POTTERY_SHERD = registerBlock("howl_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block MINER_POTTERY_SHERD = registerBlock("miner_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block MOURNER_POTTERY_SHERD = registerBlock("mourner_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block PLENTY_POTTERY_SHERD = registerBlock("plenty_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block PRIZE_POTTERY_SHERD = registerBlock("prize_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block SCRAPE_POTTERY_SHERD = registerBlock("scrape_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block SHEAF_POTTERY_SHERD = registerBlock("sheaf_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block SHELTER_POTTERY_SHERD = registerBlock("shelter_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block SKULL_POTTERY_SHERD = registerBlock("skull_pottery_sherd",
            ModTemplates.defaultSherdBlock());
    public static final Block SNORT_POTTERY_SHERD = registerBlock("snort_pottery_sherd",
            ModTemplates.defaultSherdBlock());

    public static final Block NETHERITE_UPGRADE = registerBlock("netherite_upgrade",
            ModTemplates.defaultUpgradeBlock());
    public static final Block BOLT_ARMOR_TRIM = registerBlock("bolt_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.COPPER));
    public static final Block COAST_ARMOR_TRIM = registerBlock("coast_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.STONE));
    public static final Block DUNE_ARMOR_TRIM = registerBlock("dune_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.DRIPSTONE_BLOCK));
    public static final Block EYE_ARMOR_TRIM = registerBlock("eye_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.CALCITE));
    public static final Block FLOW_ARMOR_TRIM = registerBlock("flow_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.DEEPSLATE_TILES));
    public static final Block HOST_ARMOR_TRIM = registerBlock("host_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.GRAVEL));
    public static final Block RAISER_ARMOR_TRIM = registerBlock("raiser_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.GRAVEL));
    public static final Block RIB_ARMOR_TRIM = registerBlock("rib_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.NETHER_BRICKS));
    public static final Block SENTRY_ARMOR_TRIM = registerBlock("sentry_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.STONE));
    public static final Block SHAPER_ARMOR_TRIM = registerBlock("shaper_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.GRAVEL));
    public static final Block SILENCE_ARMOR_TRIM = registerBlock("silence_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.DEEPSLATE));
    public static final Block SNOUT_ARMOR_TRIM = registerBlock("snout_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.POLISHED_DEEPSLATE));
    public static final Block SPIRE_ARMOR_TRIM = registerBlock("spire_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.DEEPSLATE_BRICKS));
    public static final Block TIDE_ARMOR_TRIM = registerBlock("tide_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.DEEPSLATE_BRICKS));
    public static final Block VEX_ARMOR_TRIM = registerBlock("vex_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.STONE));
    public static final Block WARD_ARMOR_TRIM = registerBlock("ward_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.DEEPSLATE));
    public static final Block WAYFINDER_ARMOR_TRIM = registerBlock("wayfinder_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.GRAVEL));
    public static final Block WILD_ARMOR_TRIM = registerBlock("wild_armor_trim",
            ModTemplates.defaultArmorTrimBlock(BlockSoundGroup.STONE));

    public static final Block BRICK = registerBlock("brick",
            ModTemplates.defaultIngotBlock(MapColor.RED, false));
    public static final Block NETHER_BRICK = registerBlock("nether_brick",
            ModTemplates.defaultIngotBlock(MapColor.DARK_RED, false));
    public static final Block IRON_INGOT = registerBlock("iron_ingot",
            ModTemplates.defaultIngotBlock(MapColor.IRON_GRAY, true));
    public static final Block COPPER_INGOT = registerBlock("copper_ingot",
            ModTemplates.defaultIngotBlock(MapColor.ORANGE, true));
    public static final Block GOLD_INGOT = registerBlock("gold_ingot",
            ModTemplates.defaultIngotBlock(MapColor.GOLD, true));
    public static final Block NETHERITE_INGOT = registerBlock("netherite_ingot",
            ModTemplates.defaultIngotBlock(MapColor.BLACK, true));

    public static final Block LAPIS_LAZULI = registerBlock("lapis_lazuli",
            new LapisLazuliItemBlock(ModTemplates.defaultGemstoneSettings(MapColor.LAPIS_BLUE)));
    public static final Block AMETHYST_SHARD = registerBlock("amethyst_shard",
            new CrystalItemBlock(ModTemplates.defaultGemstoneSettings(MapColor.PURPLE)));
    public static final Block DIAMOND = registerBlock("diamond",
            new DiamondItemBlock(ModTemplates.defaultGemstoneSettings(MapColor.DIAMOND_BLUE)));
    public static final Block EMERALD = registerBlock("emerald",
            new EmeraldItemBlock(ModTemplates.defaultGemstoneSettings(MapColor.EMERALD_GREEN)));
    public static final Block ECHO_SHARD = registerBlock("echo_shard",
            new CrystalItemBlock(ModTemplates.defaultGemstoneSettings(MapColor.BLACK).sounds(BlockSoundGroup.SCULK_SHRIEKER)));

    private static Block registerBlock(String name, Block block) {
        Block registeredBlock = Registry.register(Registries.BLOCK, ItemsDisplayed.id(name), block);
        registerDebugItem(name, registeredBlock);

        return registeredBlock;
    }

    private static void registerDebugItem(String name, Block block) {
        DebugBlockItem debugBlockItem = new DebugBlockItem(block, new Item.Settings());
        Registry.register(Registries.ITEM, ItemsDisplayed.id(name), debugBlockItem);
    }

    public static void registerBlocks() {
        ItemsDisplayed.LOGGER.info("Registering blocks");
    }
}
