package net.freedinner.items_displayed.entity.custom.jewelry_pillow;

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

public class JewelryPillowEntityRenderer extends LivingEntityRenderer<JewelryPillowEntity, JewelryPillowEntityRenderState, JewelryPillowEntityModel> {

    public JewelryPillowEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new JewelryPillowEntityModel(context.getPart(ItemsDisplayedClient.JEWELRY_PILLOW_MODEL_LAYER)), 0.0f);
        addFeature(new JewelryPillowItemRenderer(this, new HeldItemRenderer(MinecraftClient.getInstance(), context.getRenderDispatcher(), context.getItemRenderer())));
    }

    @Override
    public JewelryPillowEntityRenderState createRenderState() {
        return new JewelryPillowEntityRenderState();
    }

    @Override
    public void updateRenderState(JewelryPillowEntity livingEntity, JewelryPillowEntityRenderState livingEntityRenderState, float f) {
        livingEntityRenderState.color = livingEntity.getColor();
        livingEntityRenderState.rotation = livingEntity.getEntityRotation();
        livingEntityRenderState.time = livingEntity.getWorld().getTime();
        livingEntityRenderState.lastHitTime = livingEntity.lastHitTime;
        livingEntityRenderState.tickDelta = f;
        livingEntityRenderState.mainHandStack = livingEntity.getMainHandStack();
        livingEntityRenderState.entity = livingEntity;
    }

    @Override
    public Identifier getTexture(JewelryPillowEntityRenderState state) {
        String color = state.color.getName();
        return ItemsDisplayed.id( "textures/entity/jewelry_pillow/" + color + "_jewelry_pillow.png");
    }

    @Override
    protected void setupTransforms(JewelryPillowEntityRenderState state, MatrixStack matrices, float animationProgress, float bodyYaw) {
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - state.rotation));

        float i = (float)(state.time - state.lastHitTime) + state.tickDelta;
        if (i < 5.0f) {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.sin(i / 1.5f * (float)Math.PI) * 3.0f));
        }
    }

    @Override
    protected boolean hasLabel(JewelryPillowEntity livingEntity, double d) {
        return false;
    }
}
