package com.joey736n.glowing.entities;

import com.joey736n.glowing.blocks.BlockRegistry;
import com.joey736n.glowing.effects.EffectsRegistry;
import com.joey736n.glowing.items.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class GlowsquidArrow extends AbstractArrow {
    private static final float WATER_INERTIA = 0.99F;
    private static final int EFFECT_DURATION = 600;
    private boolean hasInk = true;

    public GlowsquidArrow(EntityType<? extends GlowsquidArrow> entityType, Level level) {
        super(entityType, level);
    }

    public GlowsquidArrow(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.GLOWSQUID_ARROW.get(), x, y, z, level);
    }

    public GlowsquidArrow(Level level, LivingEntity livingEntity) {
        super(EntityTypeRegistry.GLOWSQUID_ARROW.get(), livingEntity, level);
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        super.onHitBlock(result);
        Player player = null;
        Entity placer = this.getOwner();
        if (placer instanceof Player) { player = (Player) placer; }

        BlockPlaceContext context = new BlockPlaceContext(this.level(), player, InteractionHand.MAIN_HAND, new ItemStack(BlockRegistry.GLOWING_SPLATTER_BLOCK.get()), result);
        Direction hitFace = result.getDirection();
        BlockPos placingPosition = result.getBlockPos().relative(hitFace);

        BlockState splatterState = BlockRegistry.GLOWING_SPLATTER_BLOCK.get().getStateForPlacement(context);
        if (hasInk && context.canPlace() && splatterState != null && splatterState.canSurvive(this.level(), placingPosition)) {
            this.level().setBlockAndUpdate(placingPosition, splatterState);
            hasInk = false;
            return;
        }
        // If the splatter was not placed, the arrow will continue making the slime sound until it gets placed.
        super.setSoundEvent(SoundEvents.SLIME_BLOCK_PLACE);
    }

    @Override
    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.SLIME_BLOCK_PLACE;
    }

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity target) {
        super.doPostHurtEffects(target);
        MobEffectInstance effect = new MobEffectInstance(EffectsRegistry.GLOWING.get(), EFFECT_DURATION);
        if (hasInk) { target.addEffect(effect); }
    }
    @Override
    protected float getWaterInertia() {
        return WATER_INERTIA;
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return hasInk ? new ItemStack(ItemRegistry.GLOWSQUID_ARROW_ITEM.get()) : new ItemStack(Items.ARROW);
    }

    public boolean getHasInk() {
        return hasInk;
    }
}
