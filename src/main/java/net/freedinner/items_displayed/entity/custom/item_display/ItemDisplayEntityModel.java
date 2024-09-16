package net.freedinner.items_displayed.entity.custom.item_display;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;

public class ItemDisplayEntityModel extends SinglePartEntityModel<ItemDisplayEntity> implements ModelWithArms {
	private final ModelPart root;

	public ItemDisplayEntityModel(ModelPart root) {
		this.root = root;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		main.addChild("stick1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -0.5F, -6.0F, 1.0F, 1.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(2.3285F, -5.1408F, 0.9266F, -0.3491F, -1.0472F, 1.5708F));
		main.addChild("stick2", ModelPartBuilder.create().uv(0, 0).cuboid(-2.25F, -1.0F, -8.0F, 1.0F, 1.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.0F, 2.0F, 1.0737F, 0.1719F, 0.3053F));
		main.addChild("stick3", ModelPartBuilder.create().uv(0, 13).cuboid(-5.0F, -0.75F, -6.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.5F, 2.0F, 1.0472F, 0.0F, 0.0F));
		main.addChild("stick4", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, -9.4F, 3.0F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 0.1745F, 0.0F, 0.0F));

		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public ModelPart getPart() {
		return root;
	}

	@Override
	public void setAngles(ItemDisplayEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}

	@Override
	public void setArmAngle(Arm arm, MatrixStack matrices) {
	}
}