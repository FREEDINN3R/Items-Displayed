package net.freedinner.items_displayed.entity.custom;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SheepWoolEntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class JewelryPillowColorEntityModel extends SinglePartEntityModel<JewelryPillowEntity> {
	private final ModelPart bb_main;
	public JewelryPillowColorEntityModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData pillow_r1 = bb_main.addChild("pillow_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -2.9727F, -0.027F, 10.0F, 3.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -4.3F, 0.3927F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public ModelPart getPart() {
		return bb_main;
	}

	@Override
	public void setAngles(JewelryPillowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}