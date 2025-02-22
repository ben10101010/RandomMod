package net.quantumdeathlord.randommod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.quantumdeathlord.randommod.RandomMod;
import net.quantumdeathlord.randommod.entity.custom.NightReaperEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NightReaperRenderer extends GeoEntityRenderer<NightReaperEntity> {

    public NightReaperRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NightReaperModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(NightReaperEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(RandomMod.MOD_ID, "textures/entity/night_reaper/night_reaper.png");
    }

    @Override
    public @Nullable RenderType getRenderType(NightReaperEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}
