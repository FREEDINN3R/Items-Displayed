package net.freedinner.items_displayed.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class ArmorTrimBlock extends AbstractItemBlock implements Waterloggable {
    private static final VoxelShape NORTH_SOUTH_SHAPE =
            Block.createCuboidShape(4, 0, 2, 12, 4, 14);
    private static final VoxelShape EAST_WEST_SHAPE =
            Block.createCuboidShape(2, 0, 4, 14, 4, 12);

    public ArmorTrimBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH, SOUTH -> NORTH_SOUTH_SHAPE;
            case EAST, WEST -> EAST_WEST_SHAPE;
            default -> throw new IllegalStateException("Unexpected value: " + state.get(FACING));
        };
    }
}
