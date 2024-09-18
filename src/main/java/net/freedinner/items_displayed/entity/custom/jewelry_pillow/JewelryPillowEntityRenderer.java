package net.freedinner.items_displayed.entity.custom.jewelry_pillow;

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
    }

    @Override
    public Identifier getTexture(JewelryPillowEntity entity) {
        String color = entity.getColor().getName();
        return ItemsDisplayed.id( "textures/entity/jewelry_pillow/" + color + "_jewelry_pillow.png");
    }

    @Override
    protected void setupTransforms(JewelryPillowEntity entity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta) {
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - entity.getEntityRotation()));

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
