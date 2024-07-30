package net.freedinner.items_displayed.networking;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.util.BlockItemMapper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public record S2CLoadMapsPacket(BiMap<Block, Item> blockItemMap) implements CustomPayload {
    public static final CustomPayload.Id<S2CLoadMapsPacket> ID = new CustomPayload.Id<>(Identifier.of(ItemsDisplayed.MOD_ID, "client_load_maps"));
    public static final PacketCodec<RegistryByteBuf, S2CLoadMapsPacket> CODEC = CustomPayload.codecOf(
            S2CLoadMapsPacket::write,
            S2CLoadMapsPacket::new
    );

    public S2CLoadMapsPacket(RegistryByteBuf buf){
        this(MapWriter.readMapFromPacket(buf));
    }

    public void write(RegistryByteBuf buf){
        MapWriter.writeMapToPacket(blockItemMap, buf);
    }

    public static void send(ServerPlayerEntity player, BiMap<Block, Item> blockItemMap) {
        ServerPlayNetworking.send(player, new S2CLoadMapsPacket(blockItemMap));
    }

    public static void receive(S2CLoadMapsPacket packet, ClientPlayNetworking.Context context) {
        context.client().executeTask(() -> {
            BlockItemMapper.setBlockItemMap(packet.blockItemMap);
        });
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static class MapWriter {
        public static void writeMapToPacket(BiMap<Block, Item> map, PacketByteBuf packet) {
            packet.writeInt(map.size());

            map.forEach((key, value) -> {
                Identifier blockId = Registries.BLOCK.getId(key);
                packet.writeIdentifier(blockId);

                Identifier itemId = Registries.ITEM.getId(value);
                packet.writeIdentifier(itemId);
            });
        }

        public static BiMap<Block, Item> readMapFromPacket(PacketByteBuf packet) {
            BiMap<Block, Item> map = HashBiMap.create();
            int size = packet.readInt();

            for (int i = 0; i < size; i++) {
                Identifier blockId = packet.readIdentifier();
                Block block = Registries.BLOCK.get(blockId);

                Identifier itemId = packet.readIdentifier();
                Item item = Registries.ITEM.get(itemId);

                map.put(block, item);
            }

            return map;
        }
    }
}
