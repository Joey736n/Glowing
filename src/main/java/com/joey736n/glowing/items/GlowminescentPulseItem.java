package com.joey736n.glowing.items;

import com.joey736n.glowing.effects.EffectsRegistry;
import com.joey736n.glowing.util.ParticleUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

// 2pi / node count = angle of first node
// multiply angle up until the node count for all of the angles.
// for each angle, move in that direction by a certain distance.
// BAM. circle.
public class GlowminescentPulseItem extends Item implements Vanishable {
    private static final int RADIUS = 15;
    private static final int EFFECT_DURATION = 1200;
    private static final int USE_DURATION = 32;
    public GlowminescentPulseItem(Item.Properties properties) { super(properties); }

    @Override
    public ItemStack finishUsingItem(ItemStack gmp, Level level, LivingEntity user) {
        double playerX = user.getX();
        double playerY = user.getY();
        double playerZ = user.getZ();
        AABB boundingBox = new AABB(playerX - RADIUS, playerY - RADIUS, playerZ - RADIUS, playerX + RADIUS, playerY + RADIUS, playerZ + RADIUS);
        MobEffectInstance effect = new MobEffectInstance(EffectsRegistry.GLOWING.get(), EFFECT_DURATION);
        List<Mob> selectedMobs = level.getNearbyEntities(Mob.class, TargetingConditions.forNonCombat(), user, boundingBox);
        for (Mob mob: selectedMobs) {
            mob.addEffect(effect, user);
        }
        gmp.hurtAndBreak(1, user, (p) -> {p.broadcastBreakEvent(user.getUsedItemHand());});
        ParticleUtil.generateConcentricRings(user.position(), level, 1.0f, 0.5f, 8.0f, 10, 10, ParticleTypes.GLOW);
        return gmp;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack gmp = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.success(gmp);
    }

    @Override
    public int getUseDuration(ItemStack gmp) { return USE_DURATION; }

    @Override
    public UseAnim getUseAnimation(ItemStack gmp) { return UseAnim.BOW; }

}
