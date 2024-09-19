package net.freedinner.items_displayed.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class MusicDiskBlock extends AbstractItemBlock implements Waterloggable {
    public static final VoxelShape SHAPE =
            Block.createCuboidShape(2, 0, 2, 14, 1, 14);

    public MusicDiskBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
