package com.joey736n.glowing.blocks;

import com.joey736n.glowing.Glowing;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Glowing.MODID);

    public static final RegistryObject<TorchBlock> GLOWSQUID_TORCH_BLOCK = BLOCKS.register("glowsquid_torch", () -> new GlowSquidTorchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).noCollission().instabreak().sound(SoundType.WOOD).lightLevel((light) -> 10)));
    public static final RegistryObject<WallTorchBlock> WALL_GLOWSQUID_TORCH_BLOCK = BLOCKS.register("wall_glowsquid_torch", () -> new WallGlowSquidTorchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).noCollission().instabreak().sound(SoundType.WOOD).lightLevel((light) -> 10)));
    public static final RegistryObject<GlowingSplatterBlock> GLOWING_SPLATTER_BLOCK = BLOCKS.register("glowing_splatter", () -> new GlowingSplatterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).noCollission().instabreak().sound(SoundType.SLIME_BLOCK).lightLevel((light) -> 10)));

}
