package com.joey736n.glowing.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectsRegistry {
    public static final DeferredRegister<MobEffect> MINECRAFT_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "minecraft");

    public static final RegistryObject<MobEffect> GLOWING = MINECRAFT_EFFECTS.register("glowing", () -> new GlowingEffect(MobEffectCategory.NEUTRAL, Integer.parseInt("4cd6f5", 16)));
}
