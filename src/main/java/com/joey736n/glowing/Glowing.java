package com.joey736n.glowing;

import com.joey736n.glowing.blocks.BlockRegistry;
import com.joey736n.glowing.effects.EffectsRegistry;
import com.joey736n.glowing.items.ItemRegistry;
import com.joey736n.glowing.potions.PotionRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("glowing")
public class Glowing
{
    public static final String MODID = "glowing";

    public Glowing()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);

        BlockRegistry.BLOCKS.register(bus);
        ItemRegistry.ITEMS.register(bus);
        PotionRegistry.POTIONS.register(bus);
        EffectsRegistry.MINECRAFT_EFFECTS.register(bus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(FMLCommonSetupEvent event) {
        PotionBrewing.addMix(Potions.AWKWARD, Items.GLOW_INK_SAC, PotionRegistry.GLOWING_POTION.get());
        PotionBrewing.addMix(PotionRegistry.GLOWING_POTION.get(), Items.REDSTONE, PotionRegistry.LONG_GLOWING_POTION.get());
    }
    public void doClientStuff(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.GLOWSQUID_TORCH_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.WALL_GLOWSQUID_TORCH_BLOCK.get(), RenderType.cutout());
    }
}
