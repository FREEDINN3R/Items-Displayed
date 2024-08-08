package net.freedinner.items_displayed.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.freedinner.items_displayed.config.ModConfigs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

public class BlockItemMapper {
    private static BiMap<Block, Item> blockItemMap = HashBiMap.create();

    public static BiMap<Block, Item> getBlockItemMap() {
        return blockItemMap;
    }

    public static void setBlockItemMap(BiMap<Block, Item> blockItemMap) {
        BlockItemMapper.blockItemMap = blockItemMap;
    }

    public static void addEntry(Block block, Item item) {
        blockItemMap.put(block, item);
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
}