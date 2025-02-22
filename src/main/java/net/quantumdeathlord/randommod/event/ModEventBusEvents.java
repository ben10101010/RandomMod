package net.quantumdeathlord.randommod.event;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.quantumdeathlord.randommod.RandomMod;
import net.quantumdeathlord.randommod.entity.ModEntities;
import net.quantumdeathlord.randommod.entity.client.NightReaperModel;
import net.quantumdeathlord.randommod.entity.custom.NightReaperEntity;

@Mod.EventBusSubscriber(modid = RandomMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {



    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.NIGHT_REAPER.get(), NightReaperEntity.createAttributes().build());
    }
}
