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

public class AmethystShardItemBlock extends AbstractStackableItemBlock {
    public static final VoxelShape NORTH_SOUTH_SHAPE_1 =
            Block.createCuboidShape(5.0, 0.0, 4.0, 11.0, 3.0, 12.0);
    public static final VoxelShape EAST_WEST_SHAPE_1 =
            Block.createCuboidShape(4.0, 0.0, 5.0, 12.0, 3.0, 11.0);
    public static final VoxelShape NORTH_SOUTH_SHAPE_2 =
            Block.createCuboidShape(3.0, 0.0, 1.5, 13.0, 3.0, 14.5);
    public static final VoxelShape EAST_WEST_SHAPE_2 =
            Block.createCuboidShape(1.5, 0.0, 3.0, 14.5, 3.0, 13.0);
    public static final VoxelShape NORTH_SOUTH_SHAPE_3 =
            Block.createCuboidShape(3.0, 0.0, 1.5, 13.0, 6.0, 14.5);
    public static final VoxelShape EAST_WEST_SHAPE_3 =
            Block.createCuboidShape(1.5, 0.0, 3.0, 14.5, 6.0, 13.0);
    public static final VoxelShape NORTH_SOUTH_SHAPE_4 =
            Block.createCuboidShape(3.0, 0.0, 1.5, 13.0, 6.0, 14.5);
    public static final VoxelShape EAST_WEST_SHAPE_4 =
            Block.createCuboidShape(1.5, 0.0, 3.0, 14.5, 6.0, 13.0);

    private static final MapCodec<? extends HorizontalFacingBlock> CODEC = AbstractItemBlock.createCodec(AmethystShardItemBlock::new);

    public AmethystShardItemBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected IntProperty getItemProperty() {
        return ModProperties.GEMSTONES;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int currItemCount = state.get(getItemProperty());

        return switch (state.get(FACING)) {
            case NORTH, SOUTH -> switch (currItemCount) {
                case 1 -> NORTH_SOUTH_SHAPE_1;
                case 2 -> NORTH_SOUTH_SHAPE_2;
                case 3 -> NORTH_SOUTH_SHAPE_3;
                case 4 -> NORTH_SOUTH_SHAPE_4;
                default -> throw new IllegalStateException("Unexpected value: " + currItemCount);
            };
            case EAST, WEST -> switch (currItemCount) {
                case 1 -> EAST_WEST_SHAPE_1;
                case 2 -> EAST_WEST_SHAPE_2;
                case 3 -> EAST_WEST_SHAPE_3;
                case 4 -> EAST_WEST_SHAPE_4;
                default -> throw new IllegalStateException("Unexpected value: " + currItemCount);
            };
            default -> throw new IllegalStateException("Unexpected value: " + state.get(FACING));
        };
    }
}
