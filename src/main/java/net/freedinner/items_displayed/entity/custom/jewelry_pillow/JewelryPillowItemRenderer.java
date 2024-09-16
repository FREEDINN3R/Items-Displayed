package net.freedinner.items_displayed.entity.custom.jewelry_pillow;

import net.freedinner.items_displayed.util.BlockItemMapper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.RotationAxis;

public class JewelryPillowItemRenderer extends FeatureRenderer<JewelryPillowEntity, JewelryPillowEntityModel> {
    private final HeldItemRenderer heldItemRenderer;

    public JewelryPillowItemRenderer(FeatureRendererContext<JewelryPillowEntity, JewelryPillowEntityModel> context, HeldItemRenderer heldItemRenderer) {
        super(context);
        this.heldItemRenderer = heldItemRenderer;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, JewelryPillowEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemStack itemStack = entity.getMainHandStack();

        if (itemStack.isEmpty()) {
            return;
        }

        renderItem(entity, itemStack, matrices, vertexConsumers, light);
    }

    protected void renderItem(LivingEntity entity, ItemStack itemStack, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        ((ModelWithArms)getContextModel()).setArmAngle(Arm.RIGHT, matrices);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-157.5f));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f));

        matrices.translate(0f, -0.62f, -0.51f);

        Block block = BlockItemMapper.getBlockOrNull(itemStack.getItem(), true);
        ItemStack blockItemStack = new ItemStack(block == null ? Blocks.AIR : block.asItem());
        this.heldItemRenderer.renderItem(entity, blockItemStack, ModelTransformationMode.THIRD_PERSON_RIGHT_HAND, false, matrices, vertexConsumers, light);

        matrices.pop();
    }
}
