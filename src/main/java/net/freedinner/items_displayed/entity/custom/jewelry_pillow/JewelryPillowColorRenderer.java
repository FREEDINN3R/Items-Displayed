package net.freedinner.items_displayed.entity.custom.jewelry_pillow;

import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.ItemsDisplayedClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

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
