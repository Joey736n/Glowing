package com.joey736n.glowing.items;

import com.joey736n.glowing.entities.GlowsquidArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class GlowsquidArrowItem extends ArrowItem {
    public GlowsquidArrowItem(Item.Properties properties) { super(properties); }

    @Override
    public @NotNull AbstractArrow createArrow(@NotNull Level level, @NotNull ItemStack itemStack, @NotNull LivingEntity livingEntity) {
        return new GlowsquidArrow(level, livingEntity);
    }
}
