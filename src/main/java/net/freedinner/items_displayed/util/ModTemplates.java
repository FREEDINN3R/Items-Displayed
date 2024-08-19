package net.freedinner.items_displayed.util;

import net.freedinner.items_displayed.block.custom.ArmorTrimBlock;
import net.freedinner.items_displayed.block.custom.NetheriteUpgradeBlock;
import net.freedinner.items_displayed.block.custom.SherdBlock;
import net.freedinner.items_displayed.block.custom.stackable.IngotBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;

public class ModTemplates {
    public static Block defaultSherdBlock() {
        return new SherdBlock(defaultSettings()
                .mapColor(MapColor.TERRACOTTA_RED)
                .sounds(BlockSoundGroup.DECORATED_POT));
    }

    public static Block defaultUpgradeBlock() {
        return new NetheriteUpgradeBlock(defaultSettings()
                .mapColor(MapColor.DARK_RED)
                .sounds(BlockSoundGroup.NETHERRACK));
    }

    public static Block defaultArmorTrimBlock(BlockSoundGroup soundGroup) {
        return new ArmorTrimBlock(defaultSettings()
                .mapColor(MapColor.STONE_GRAY)
                .sounds(soundGroup));
    }

    public static Block defaultIngotBlock(MapColor mapColor, boolean metal) {
        BlockSoundGroup soundGroup = metal ? BlockSoundGroup.NETHERITE : BlockSoundGroup.STONE;

        return new IngotBlock(defaultSettings()
                .mapColor(mapColor)
                .sounds(soundGroup));
    }

    public static AbstractBlock.Settings defaultGemstoneSettings(MapColor mapColor) {
        return defaultSettings()
                .mapColor(mapColor)
                .sounds(BlockSoundGroup.METAL);
    }

    private static AbstractBlock.Settings defaultSettings() {
        return AbstractBlock.Settings
                .create()
                .breakInstantly()
                .noBlockBreakParticles()
                .nonOpaque()
                .pistonBehavior(PistonBehavior.DESTROY);
    }
}
