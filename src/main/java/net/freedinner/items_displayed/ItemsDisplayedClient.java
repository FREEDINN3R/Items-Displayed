package net.freedinner.items_displayed;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.freedinner.items_displayed.entity.ModEntities;
import net.freedinner.items_displayed.entity.custom.ItemDisplayEntityModel;
import net.freedinner.items_displayed.entity.custom.ItemDisplayEntityRenderer;
import net.freedinner.items_displayed.event.ModEventHandlers;
import net.freedinner.items_displayed.networking.S2CLoadMapsPacket;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ItemsDisplayedClient implements ClientModInitializer {
	public static final EntityModelLayer ITEM_DISPLAY_MODEL_LAYER = new EntityModelLayer(
			ItemsDisplayed.id("item_display"),
			"item_display_model_layer"
	);

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ModEntities.ITEM_DISPLAY, ItemDisplayEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ITEM_DISPLAY_MODEL_LAYER, ItemDisplayEntityModel::getTexturedModelData);

		ModEventHandlers.registerClientEventHandlers();
		ClientPlayNetworking.registerGlobalReceiver(S2CLoadMapsPacket.ID, S2CLoadMapsPacket::receive);
	}
}