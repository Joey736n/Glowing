package com.joey736n.glowing.renderers;

import com.joey736n.glowing.entities.GlowsquidArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class GlowsquidArrowRenderer extends ArrowRenderer<GlowsquidArrow> {
    public static final ResourceLocation GLOWSQUID_ARROW_LOCATION = new ResourceLocation("glowing", "textures/entity/projectiles/glowsquid_arrow.png");
    public static final ResourceLocation ARROW_LOCATION = new ResourceLocation("textures/entity/projectiles/arrow.png");

    public GlowsquidArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }
    @Override
    public @NotNull ResourceLocation getTextureLocation(GlowsquidArrow arrow) {
        if (arrow.getHasInk()) { return GLOWSQUID_ARROW_LOCATION; }
        return ARROW_LOCATION;
    }
}
