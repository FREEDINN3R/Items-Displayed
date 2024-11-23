package net.freedinner.items_displayed.util;

import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.block.custom.ArmorTrimBlock;
import net.freedinner.items_displayed.block.custom.MusicDiskBlock;
import net.freedinner.items_displayed.block.custom.NetheriteUpgradeBlock;
import net.freedinner.items_displayed.block.custom.SherdBlock;
import net.freedinner.items_displayed.block.custom.stackable.DiscFragmentItemBlock;
import net.freedinner.items_displayed.block.custom.stackable.IngotItemBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModTemplates {
    public static Block defaultSherdBlock(String name) {
        return new SherdBlock(defaultSettings(name)
                .mapColor(MapColor.TERRACOTTA_RED)
                .sounds(BlockSoundGroup.DECORATED_POT));
    }

    public static Block defaultUpgradeBlock(String name) {
        return new NetheriteUpgradeBlock(defaultSettings(name)
                .mapColor(MapColor.DARK_RED)
                .sounds(BlockSoundGroup.NETHERRACK));
    }

    public static Block defaultArmorTrimBlock(BlockSoundGroup soundGroup, String name) {
        return new ArmorTrimBlock(defaultSettings(name)
                .mapColor(MapColor.STONE_GRAY)
                .sounds(soundGroup));
    }

    public static Block defaultIngotBlock(MapColor mapColor, boolean metal, String name) {
        BlockSoundGroup soundGroup = metal ? BlockSoundGroup.NETHERITE : BlockSoundGroup.STONE;
        return new IngotItemBlock(defaultSettings(name)
                .mapColor(mapColor)
                .sounds(soundGroup));
    }

    public static Block defaultDiscBlock(BlockSoundGroup soundGroup, String name) {
        return new MusicDiskBlock(defaultSettings(name)
                .mapColor(MapColor.BLACK)
                .sounds(soundGroup));
    }

    public static Block defaultDiscFragmentBlock(String name) {
        return new DiscFragmentItemBlock(defaultSettings(name)
                .mapColor(MapColor.BLACK)
                .sounds(BlockSoundGroup.BAMBOO_WOOD));
    }

    public static AbstractBlock.Settings defaultGemstoneSettings(MapColor mapColor, String name) {
        return defaultSettings(name)
                .mapColor(mapColor)
                .sounds(BlockSoundGroup.METAL);
    }

    private static AbstractBlock.Settings defaultSettings(String name) {
        return AbstractBlock.Settings
                .create()
                .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(ItemsDisplayed.MOD_ID, name)))
                .breakInstantly()
                .noBlockBreakParticles()
                .nonOpaque()
                .pistonBehavior(PistonBehavior.DESTROY);
    }
}
