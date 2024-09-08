package net.freedinner.items_displayed.block.custom.stackable;

import com.mojang.serialization.MapCodec;
import net.freedinner.items_displayed.block.custom.AbstractItemBlock;
import net.freedinner.items_displayed.util.ModProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class DiscFragmentItemBlock extends AbstractStackableItemBlock {
    public static final VoxelShape SHAPE =
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 1.0, 14.0);

    private static final MapCodec<? extends HorizontalFacingBlock> CODEC = AbstractItemBlock.createCodec(DiscFragmentItemBlock::new);

    public DiscFragmentItemBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected IntProperty getItemProperty() {
        return ModProperties.FRAGMENTS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
