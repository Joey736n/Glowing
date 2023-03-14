package com.joey736n.glowing.potions;

import com.joey736n.glowing.Glowing;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PotionRegistry {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Glowing.MODID);
    public static final RegistryObject<Potion> GLOWING_POTION = POTIONS.register("glowing", () -> new Potion(new MobEffectInstance(MobEffects.GLOWING, 3600, 0)));
    public static final RegistryObject<Potion> LONG_GLOWING_POTION = POTIONS.register("long_glowing", () -> new Potion(new MobEffectInstance(MobEffects.GLOWING, 9600, 0)));

}
