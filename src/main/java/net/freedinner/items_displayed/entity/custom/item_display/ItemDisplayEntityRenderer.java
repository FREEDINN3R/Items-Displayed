package net.freedinner.items_displayed.entity.custom.item_display;

import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.ItemsDisplayedClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class ItemDisplayEntityRenderer extends LivingEntityRenderer<ItemDisplayEntity, ItemDisplayEntityRenderState,ItemDisplayEntityModel> {

    public ItemDisplayEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ItemDisplayEntityModel(context.getPart(ItemsDisplayedClient.ITEM_DISPLAY_MODEL_LAYER)), 0.0f);
        addFeature(new ItemDisplayItemRenderer(this, new HeldItemRenderer(MinecraftClient.getInstance(), context.getRenderDispatcher(), context.getItemRenderer())));
    }

    @Override
    public ItemDisplayEntityRenderState createRenderState() {
        return new ItemDisplayEntityRenderState();
    }

    @Override
    public void updateRenderState(ItemDisplayEntity livingEntity, ItemDisplayEntityRenderState livingEntityRenderState, float f) {
        livingEntityRenderState.rotation = livingEntity.getEntityRotation();
        livingEntityRenderState.time = livingEntity.getWorld().getTime();
        livingEntityRenderState.lastHitTime = livingEntity.lastHitTime;
        livingEntityRenderState.tickDelta = f;
        livingEntityRenderState.mainHandStack = livingEntity.getMainHandStack();
        livingEntityRenderState.entity = livingEntity;
    }

    @Override
    protected void setupTransforms(ItemDisplayEntityRenderState state, MatrixStack matrices, float animationProgress, float bodyYaw) {
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - state.rotation));

        float i = (float)(state.time - state.lastHitTime) + state.tickDelta;
        if (i < 5.0f) {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.sin(i / 1.5f * (float)Math.PI) * 3.0f));
        }
    }

    @Override
    protected boolean hasLabel(ItemDisplayEntity livingEntity, double d) {
        return super.hasLabel(livingEntity, d);
    }

    @Override
    public Identifier getTexture(ItemDisplayEntityRenderState state) {
        return ItemsDisplayed.id( "textures/entity/item_display.png");
    }
}
