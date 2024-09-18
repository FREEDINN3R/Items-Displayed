package net.freedinner.items_displayed.block.custom.stackable;

import net.freedinner.items_displayed.block.custom.AbstractItemBlock;
import net.freedinner.items_displayed.util.BlockItemMapper;
import net.freedinner.items_displayed.util.BlockPlacer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractStackableItemBlock extends AbstractItemBlock {
    public AbstractStackableItemBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState()
                .with(getItemProperty(), 1));
    }
    
    protected abstract IntProperty getItemProperty();

    private int getMaxItemCount() {
        return getItemProperty().getValues().size();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.shouldCancelInteraction() && shouldAddItem(player.getStackInHand(hand), state)) {
            return BlockPlacer.place(state.getBlock(), player, hand, hit);
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        if (!context.shouldCancelInteraction() && shouldAddItem(context.getStack(), state)) {
            return true;
        }

        return super.canReplace(state, context);
    }

    private boolean shouldAddItem(ItemStack heldItemStack, BlockState state) {
        boolean sameItem = heldItemStack.isOf(BlockItemMapper.getItemOrNull(state.getBlock()));
        return sameItem && state.get(getItemProperty()) < getMaxItemCount();
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (blockState.isOf(this)) {
            return blockState.cycle(getItemProperty());
        }

        return super.getPlacementState(ctx);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(getItemProperty());
    }
}
