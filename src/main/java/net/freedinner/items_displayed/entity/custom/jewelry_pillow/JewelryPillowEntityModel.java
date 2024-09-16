package net.freedinner.items_displayed.entity.custom.jewelry_pillow;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;

public class JewelryPillowEntityModel extends SinglePartEntityModel<JewelryPillowEntity> implements ModelWithArms {
	private final ModelPart root;

	public JewelryPillowEntityModel(ModelPart root) {
		this.root = root;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 13).cuboid(-4.0F, -1.001F, 2.75F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		main.addChild("support1", ModelPartBuilder.create().uv(4, 13).cuboid(-2.999F, -1.0F, -1.001F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.5F, -3.0F, 3.75F, 1.5708F, 0.0F, -1.5708F));
		main.addChild("support2", ModelPartBuilder.create().uv(4, 13).cuboid(-2.999F, -1.0F, -0.999F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.5F, -3.0F, 3.75F, 1.5708F, 0.0F, -1.5708F));
		main.addChild("support3", ModelPartBuilder.create().uv(0, 13).cuboid(-7.0F, -1.0F, -1.0F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.5F, 0.0F, 3.25F, 0.0F, -1.5708F, 0.0F));
		main.addChild("support4", ModelPartBuilder.create().uv(0, 13).cuboid(-4.0F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -0.5F, 0.25F, 0.0F, -1.5708F, 3.1416F));
		main.addChild("pillow", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -2.9727F, -0.027F, 10.0F, 3.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -4.3F, 0.3927F, 0.0F, 0.0F));

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public ModelPart getPart() {
		return root;
	}

	@Override
	public void setAngles(JewelryPillowEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}

	@Override
	public void setArmAngle(Arm arm, MatrixStack matrices) {
	}
}