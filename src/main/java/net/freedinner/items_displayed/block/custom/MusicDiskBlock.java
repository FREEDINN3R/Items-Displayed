package net.freedinner.items_displayed.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class MusicDiskBlock extends AbstractItemBlock implements Waterloggable {
    public static final VoxelShape SHAPE =
            Block.createCuboidShape(2, 0, 2, 14, 1, 14);

    private static final MapCodec<? extends HorizontalFacingBlock> CODEC = AbstractItemBlock.createCodec(MusicDiskBlock::new);

    public MusicDiskBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
