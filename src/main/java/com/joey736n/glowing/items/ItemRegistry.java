package com.joey736n.glowing.items;

import com.joey736n.glowing.Glowing;
import com.joey736n.glowing.blocks.BlockRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Glowing.MODID);

    public static final RegistryObject<StandingAndWallBlockItem> GLOWSQUID_TORCH_ITEM = ITEMS.register("glowsquid_torch", () -> new StandingAndWallBlockItem(BlockRegistry.GLOWSQUID_TORCH_BLOCK.get(), BlockRegistry.WALL_GLOWSQUID_TORCH_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
}
