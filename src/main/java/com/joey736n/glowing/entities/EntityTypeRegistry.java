package com.joey736n.glowing.entities;

import com.joey736n.glowing.Glowing;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Glowing.MODID);

    public static final RegistryObject<EntityType<GlowsquidArrow>> GLOWSQUID_ARROW = ENTITY_TYPES.register("glowsquid_arrow", () -> EntityType.Builder.<GlowsquidArrow>of(GlowsquidArrow::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(new ResourceLocation(Glowing.MODID, "glowsquid_arrow").toString()));
}
