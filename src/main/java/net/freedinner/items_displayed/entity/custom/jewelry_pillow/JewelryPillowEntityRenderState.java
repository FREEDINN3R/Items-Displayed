package net.freedinner.items_displayed.entity.custom.jewelry_pillow;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;

public class JewelryPillowEntityRenderState extends LivingEntityRenderState {
    public DyeColor color;
    public float rotation;
    public long time;
    public long lastHitTime;
    public float  tickDelta;
    public ItemStack mainHandStack;
    public JewelryPillowEntity entity;
}
