package net.freedinner.items_displayed.entity.custom;

import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.ItemsDisplayedClient;
import net.freedinner.items_displayed.item.ModTags;
import net.freedinner.items_displayed.util.BlockItemMapper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.SheepWoolEntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class JewelryPillowColorRenderer extends FeatureRenderer<JewelryPillowEntity, JewelryPillowEntityModel> {
    private final JewelryPillowColorEntityModel model;

    public JewelryPillowColorRenderer(FeatureRendererContext<JewelryPillowEntity, JewelryPillowEntityModel> context, EntityModelLoader loader) {
        super(context);
        this.model = new JewelryPillowColorEntityModel(loader.getModelPart(ItemsDisplayedClient.JEWELRY_PILLOW_COLOR_MODEL_LAYER));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, JewelryPillowEntity jewelryPillow, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        String color = jewelryPillow.getColor().getName();
        Identifier skinId = ItemsDisplayed.id("textures/entity/jewelry_pillow/" + color + "_jewelry_pillow.png");
        render(this.getContextModel(), this.model, skinId, matrices, vertexConsumers, light, jewelryPillow, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, 16383998);
    }
}
