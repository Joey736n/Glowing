package com.joey736n.glowing;

import com.joey736n.glowing.blocks.BlockRegistry;
import com.joey736n.glowing.effects.EffectsRegistry;
import com.joey736n.glowing.entities.EntityTypeRegistry;
import com.joey736n.glowing.entities.GlowsquidArrowDispenseBehaviour;
import com.joey736n.glowing.items.ItemRegistry;
import com.joey736n.glowing.potions.PotionRegistry;
import com.joey736n.glowing.renderers.GlowsquidArrowRenderer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
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
        bus.addListener(this::registerRenderers);
        bus.addListener(this::creativeTabs);

        BlockRegistry.BLOCKS.register(bus);
        ItemRegistry.ITEMS.register(bus);
        PotionRegistry.POTIONS.register(bus);
        EffectsRegistry.MINECRAFT_EFFECTS.register(bus);
        EntityTypeRegistry.ENTITY_TYPES.register(bus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(FMLCommonSetupEvent event) {
        PotionBrewing.addMix(Potions.AWKWARD, Items.GLOW_INK_SAC, PotionRegistry.GLOWING_POTION.get());
        PotionBrewing.addMix(PotionRegistry.GLOWING_POTION.get(), Items.REDSTONE, PotionRegistry.LONG_GLOWING_POTION.get());
        DispenserBlock.registerBehavior(ItemRegistry.GLOWSQUID_ARROW_ITEM.get(), new GlowsquidArrowDispenseBehaviour());
    }
    public void doClientStuff(FMLClientSetupEvent event) {

    }

    public void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeRegistry.GLOWSQUID_ARROW.get(), GlowsquidArrowRenderer::new);
    }

    private void creativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ItemRegistry.GLOWSQUID_ARROW_ITEM);
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ItemRegistry.GLOWING_SPLATTER_ITEM);
            event.accept(ItemRegistry.GLOWSQUID_TORCH_ITEM);
        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ItemRegistry.GLOWMINESCENT_PULSE_ITEM);
        }
    }
}
