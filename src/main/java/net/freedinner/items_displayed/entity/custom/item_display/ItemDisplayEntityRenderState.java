package net.freedinner.items_displayed.entity.custom.item_display;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class ItemDisplayEntityRenderState extends LivingEntityRenderState {
    public float rotation;
    public long time;
    public long lastHitTime;
    public float  tickDelta;
    public ItemStack mainHandStack;
    public ItemDisplayEntity entity;
}
