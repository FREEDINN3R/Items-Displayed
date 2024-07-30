package net.freedinner.items_displayed.util;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;

public class BlockPlacer {
    public static ActionResult place(Block block, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        if (player.getWorld().isClient() || !player.getAbilities().allowModifyWorld) {
            return ActionResult.PASS;
        }

        BlockItem blockItem = (BlockItem) block.asItem();
        ItemPlacementContext placementContext = new ItemPlacementContext(player, hand, player.getStackInHand(hand), hitResult);

        ActionResult result = blockItem.place(placementContext);

        if (result.isAccepted()) {
            player.swingHand(hand, true);

            SoundEvent sound = block.getDefaultState().getSoundGroup().getBreakSound();
            float pitch = player.getWorld().getRandom().nextFloat() * 0.1f + 0.9f;
            player.getWorld().playSound(null, placementContext.getBlockPos(), sound, SoundCategory.BLOCKS, 1.0f, pitch);
        }

        return result;
    }
}
