package com.joey736n.glowing.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class WallGlowSquidTorchBlock extends WallTorchBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public WallGlowSquidTorchBlock(BlockBehaviour.Properties properties) {
        super(properties, ParticleTypes.GLOW);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> definition) {
        definition.add(FACING);
        definition.add(WATERLOGGED);
    }

    @Override
    @NotNull
    public BlockState updateShape(BlockState state, @NotNull Direction direction, @NotNull BlockState state1, @NotNull LevelAccessor accessor, @NotNull BlockPos position, @NotNull BlockPos position1) {
        if (state.getValue(WATERLOGGED)) {
            accessor.scheduleTick(position, Fluids.WATER, Fluids.WATER.getTickDelay(accessor));
        }
        return direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(accessor, position) ? Blocks.AIR.defaultBlockState() : state;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(blockpos);
        LevelReader levelreader = context.getLevel();
        BlockState blockstate = this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        Direction direction = context.getClickedFace();

        if (direction.getAxis().isHorizontal()) {
            blockstate = blockstate.setValue(FACING, direction);
            if (blockstate.canSurvive(levelreader, blockpos)) {
                return blockstate;
            }
        }
        return null;
    }

    @Override
    @NotNull
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos position, @NotNull RandomSource random) {
        Direction direction = state.getValue(FACING);
        double d0 = (double)position.getX() + 0.5D;
        double d1 = (double)position.getY() + 0.7D;
        double d2 = (double)position.getZ() + 0.5D;
        Direction direction1 = direction.getOpposite();
        level.addParticle(this.flameParticle, d0 + 0.27D * (double)direction1.getStepX(), d1 + 0.14D, d2 + 0.27D * (double)direction1.getStepZ(), 0.0D, -5.0D, 0.0D);
    }
}
