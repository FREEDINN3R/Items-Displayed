package net.freedinner.items_displayed.event;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.freedinner.items_displayed.ItemsDisplayed;

public class ModEventHandlers {
    public static void registerServerEventHandlers() {
        ItemsDisplayed.LOGGER.info("Registering server event handlers");

        UseBlockCallback.EVENT.register(new UseBlockEvent());
        ServerWorldEvents.LOAD.register(new LoadServerWorldEvent());
        ServerPlayConnectionEvents.JOIN.register(new ClientJoinServerEvent());
    }

    public static void registerClientEventHandlers() {
        ItemsDisplayed.LOGGER.info("Registering client event handlers");

        ItemTooltipCallback.EVENT.register(new RenderTooltipEvent());
    }
}
