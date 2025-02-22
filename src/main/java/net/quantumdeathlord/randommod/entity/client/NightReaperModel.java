package net.quantumdeathlord.randommod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.quantumdeathlord.randommod.RandomMod;
import net.quantumdeathlord.randommod.entity.custom.NightReaperEntity;
import software.bernie.geckolib.model.GeoModel;

public class NightReaperModel extends GeoModel<NightReaperEntity> {

    @Override
    public ResourceLocation getModelResource(NightReaperEntity nightReaperEntity) {
        return ResourceLocation.fromNamespaceAndPath(RandomMod.MOD_ID, "geo/night_reaper.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NightReaperEntity nightReaperEntity) {
        return ResourceLocation.fromNamespaceAndPath(RandomMod.MOD_ID, "textures/entity/night_reaper/night_reaper.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NightReaperEntity nightReaperEntity) {
        return ResourceLocation.fromNamespaceAndPath(RandomMod.MOD_ID, "animations/night_reaper.animation.json");
    }
}

