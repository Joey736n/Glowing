package com.joey736n.glowing.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;

public class GlowingSplatterBlock extends DirectionalBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final Map<Direction, VoxelShape> SHAPE = Maps.newEnumMap(ImmutableMap.of(
            Direction.UP, Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0),
            Direction.DOWN, Block.box(0.0, 15.0, 0.0, 16.0, 16.0, 16.0),
            Direction.NORTH, Block.box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0),
            Direction.EAST, Block.box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0),
            Direction.SOUTH, Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0),
            Direction.WEST, Block.box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0)));

    public GlowingSplatterBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(WATERLOGGED, false).setValue(FACING, Direction.UP));
    }

    @NotNull
    @Override
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos position, @NotNull CollisionContext collisionContext) {
        return getShape(state);
    }

    public static VoxelShape getShape(BlockState state) {
        return SHAPE.get(state.getValue(FACING));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos position) {
        Direction direction = state.getValue(FACING);
        BlockPos blockpos = position.relative(direction.getOpposite());
        BlockState blockstate = reader.getBlockState(blockpos);
        return blockstate.isFaceSturdy(reader, blockpos, direction);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(blockpos);
        LevelReader levelreader = context.getLevel();
        BlockState blockstate = this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        Direction direction = context.getClickedFace();

        blockstate = blockstate.setValue(FACING, direction);
        if (blockstate.canSurvive(levelreader, blockpos)) {
                return blockstate;
        }
        return null;
    }
    @Override
    @NotNull
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
    @Override
    @NotNull
    public BlockState updateShape(BlockState state, Direction direction, @NotNull BlockState state2, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos position, @NotNull BlockPos position2) {
        return direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(levelAccessor, position) ? Blocks.AIR.defaultBlockState() : state;
    }
    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(WATERLOGGED);
    }
}
