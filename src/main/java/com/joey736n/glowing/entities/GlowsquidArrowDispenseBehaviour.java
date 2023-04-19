package com.joey736n.glowing.entities;

import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class GlowsquidArrowDispenseBehaviour extends AbstractProjectileDispenseBehavior {

    public GlowsquidArrowDispenseBehaviour() {
        super();
    }

    @Override
    protected @NotNull Projectile getProjectile(@NotNull Level level, Position position, @NotNull ItemStack p_123362_) {
        GlowsquidArrow arrow = new GlowsquidArrow(level, position.x(), position.y(), position.z());
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }
}
