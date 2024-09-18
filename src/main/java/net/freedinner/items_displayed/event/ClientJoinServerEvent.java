package net.freedinner.items_displayed.event;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.freedinner.items_displayed.networking.NetworkingConstants;
import net.freedinner.items_displayed.util.BlockItemMapper;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

public class ClientJoinServerEvent implements ServerPlayConnectionEvents.Join {
    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        PacketByteBuf buf = PacketByteBufs.create();
        BlockItemMapper.writeDataToPacket(buf);

        ServerPlayNetworking.send(handler.getPlayer(), NetworkingConstants.CLIENT_LOAD_MAPS_ID, buf);
    }
}
