package net.freedinner.items_displayed.util;

import net.minecraft.state.property.IntProperty;

public class ModProperties {
    public static final IntProperty INGOTS = IntProperty.of("ingots", 1, 3);
    public static final IntProperty GEMSTONES = IntProperty.of("gemstones", 1, 4);
    public static final IntProperty FRAGMENTS = IntProperty.of("fragments", 1, 9);
}
