package net.freedinner.items_displayed.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.config.ModConfigs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;

public class BlockItemMapper {
    private static BiMap<Block, Item> blockItemMap = HashBiMap.create();

    public static void addEntry(Block block, Item item) {
        blockItemMap.put(block, item);
    }

    public static void writeDataToPacket(PacketByteBuf packet) {
        MapWriter.writeMapToPacket(blockItemMap, packet);
    }

    public static void loadDataFromPacket(PacketByteBuf packet) {
        blockItemMap = MapWriter.readMapFromPacket(packet);
    }

    public static Item getItemOrNull(Block block) {
        return getItemOrNull(block, false);
    }

    public static Item getItemOrNull(Block block, boolean includeBlacklisted) {
        Item foundItem = blockItemMap.getOrDefault(block, null);

        if (isItemBlacklisted(foundItem) && !includeBlacklisted) {
            return null;
        }

        return foundItem;
    }

    public static Block getBlockOrNull(Item item) {
        return getBlockOrNull(item, false);
    }

    public static Block getBlockOrNull(Item item, boolean includeBlacklisted) {
        if (isItemBlacklisted(item) && !includeBlacklisted) {
            return null;
        }

        return blockItemMap.inverse().getOrDefault(item, null);
    }

    public static boolean isItemBlacklisted(Item item) {
        String itemId = Registries.ITEM.getId(item).toString();
        return ModConfigs.BLACKLISTED_ITEMS.contains(itemId);
    }

    private static class MapWriter {
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