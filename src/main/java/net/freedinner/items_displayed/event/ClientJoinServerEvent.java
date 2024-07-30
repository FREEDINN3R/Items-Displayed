package net.freedinner.items_displayed.event;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.freedinner.items_displayed.networking.S2CLoadMapsPacket;
import net.freedinner.items_displayed.util.BlockItemMapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

public class ClientJoinServerEvent implements ServerPlayConnectionEvents.Join {
    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        S2CLoadMapsPacket.send(handler.getPlayer(), BlockItemMapper.getBlockItemMap());
    }
}
