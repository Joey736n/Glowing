package com.joey736n.glowing.items;

import com.joey736n.glowing.Glowing;
import com.joey736n.glowing.blocks.BlockRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Glowing.MODID);

    public static final RegistryObject<StandingAndWallBlockItem> GLOWSQUID_TORCH_ITEM = ITEMS.register("glowsquid_torch", () -> new StandingAndWallBlockItem(BlockRegistry.GLOWSQUID_TORCH_BLOCK.get(), BlockRegistry.WALL_GLOWSQUID_TORCH_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<GlowsquidArrowItem> GLOWSQUID_ARROW_ITEM = ITEMS.register("glowsquid_arrow", () -> new GlowsquidArrowItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistryObject<BlockItem> GLOWING_SPLATTER_ITEM = ITEMS.register("glowing_splatter", () -> new BlockItem(BlockRegistry.GLOWING_SPLATTER_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<GlowminescentPulseItem> GLOWMINESCENT_PULSE_ITEM = ITEMS.register("gmp", () -> new GlowminescentPulseItem(new Item.Properties().durability(5).tab(CreativeModeTab.TAB_TOOLS)));
}
