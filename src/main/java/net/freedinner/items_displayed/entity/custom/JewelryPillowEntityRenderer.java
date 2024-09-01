package net.freedinner.items_displayed.entity.custom;

import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.ItemsDisplayedClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class JewelryPillowEntityRenderer extends LivingEntityRenderer<JewelryPillowEntity, JewelryPillowEntityModel> {

    public JewelryPillowEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new JewelryPillowEntityModel(context.getPart(ItemsDisplayedClient.JEWELRY_PILLOW_MODEL_LAYER)), 0.0f);
        addFeature(new JewelryPillowItemRenderer(this, context.getHeldItemRenderer()));
        addFeature(new JewelryPillowColorRenderer(this, context.getModelLoader()));
    }

    @Override
    public Identifier getTexture(JewelryPillowEntity entity) {
        return ItemsDisplayed.id( "textures/entity/jewelry_pillow.png");
    }

    @Override
    protected void setupTransforms(JewelryPillowEntity entity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta, float scale) {
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - entity.getPillowRotation()));

        float i = (float)(entity.getWorld().getTime() - entity.lastHitTime) + tickDelta;
        if (i < 5.0f) {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.sin(i / 1.5f * (float)Math.PI) * 3.0f));
        }
    }

    @Override
    protected boolean hasLabel(JewelryPillowEntity livingEntity) {
        return false;
    }
}
