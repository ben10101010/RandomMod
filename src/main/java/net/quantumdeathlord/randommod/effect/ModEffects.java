package net.quantumdeathlord.randommod.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.quantumdeathlord.randommod.RandomMod;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, RandomMod.MOD_ID);

    public static final RegistryObject<MobEffect> HEART_BURST = MOB_EFFECTS.register("heartburst",
            () -> new HeartBurst(MobEffectCategory.HARMFUL, 0x36ebab));


    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
