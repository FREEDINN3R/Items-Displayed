package net.freedinner.items_displayed.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.util.BlockItemMapper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class LoadServerWorldEvent implements ServerWorldEvents.Load {
    @Override
    public void onWorldLoad(MinecraftServer server, ServerWorld world) {
        Registries.BLOCK.stream().filter(this::fromThisMod).forEach(
                (Block block) -> BlockItemMapper.addEntry(block, getDroppedItem(block, world))
        );
    }

    private boolean fromThisMod(Block block) {
        return Registries.BLOCK.getId(block).getNamespace().equals(ItemsDisplayed.MOD_ID);
    }

    private Item getDroppedItem(Block block, ServerWorld world) {
        return Block.getDroppedStacks(block.getDefaultState(), world, BlockPos.ORIGIN, null).get(0).getItem();
    }
}
