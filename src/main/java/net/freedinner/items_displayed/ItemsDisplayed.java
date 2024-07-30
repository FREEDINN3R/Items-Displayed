package net.freedinner.items_displayed;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.freedinner.items_displayed.block.ModBlocks;
import net.freedinner.items_displayed.config.ModConfigs;
import net.freedinner.items_displayed.entity.ModEntities;
import net.freedinner.items_displayed.event.ModEventHandlers;
import net.freedinner.items_displayed.item.ModItemGroups;
import net.freedinner.items_displayed.item.ModItems;
import net.freedinner.items_displayed.item.ModTags;
import net.freedinner.items_displayed.networking.S2CLoadMapsPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemsDisplayed implements ModInitializer {
	public static final String MOD_ID = "items_displayed";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModConfigs.registerConfigs();

		ModBlocks.registerBlocks();
		ModItems.registerItems();
		ModItemGroups.registerItemGroups();

		ModEntities.registerEntities();

		ModTags.registerTags();

		ModEventHandlers.registerEventHandlers();

		PayloadTypeRegistry.playS2C().register(S2CLoadMapsPacket.ID, S2CLoadMapsPacket.CODEC);
	}
}