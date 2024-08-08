package net.freedinner.items_displayed.entity.custom;

import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.ItemsDisplayedClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class ItemDisplayEntityRenderer extends LivingEntityRenderer<ItemDisplayEntity, ItemDisplayEntityModel> {

    public ItemDisplayEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ItemDisplayEntityModel(context.getPart(ItemsDisplayedClient.ITEM_DISPLAY_MODEL_LAYER)), 0.0f);
        addFeature(new DisplayedItemFeatureRenderer(this, context.getHeldItemRenderer()));
    }

    @Override
    public Identifier getTexture(ItemDisplayEntity entity) {
        return ItemsDisplayed.id( "textures/entity/item_display.png");
    }

    @Override
    protected void setupTransforms(ItemDisplayEntity entity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta, float scale) {
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - entity.getDisplayRotation()));

        float i = (float)(entity.getWorld().getTime() - entity.lastHitTime) + tickDelta;
        if (i < 5.0f) {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.sin(i / 1.5f * (float)Math.PI) * 3.0f));
        }
    }

    @Override
    protected boolean hasLabel(ItemDisplayEntity livingEntity) {
        return false;
    }
}
