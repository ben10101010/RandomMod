package net.quantumdeathlord.randommod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.quantumdeathlord.randommod.RandomMod;
import net.quantumdeathlord.randommod.client.Keybindings;
import net.quantumdeathlord.randommod.effect.ModEffects;
import net.quantumdeathlord.randommod.entity.custom.NightReaperEntity;
import net.quantumdeathlord.randommod.sound.ModSounds;

@Mod.EventBusSubscriber(modid = RandomMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    static boolean distanceCheck = false;
    static float countValue = 0;
    static double changeAbleCountValue = 200;


    @SubscribeEvent
    public static void onCloseToEntity(LivingEvent event){

        if(event.getEntity() instanceof NightReaperEntity) {
            Player player = Minecraft.getInstance().player;
            double num = event.getEntity().distanceToSqr(player);
            double value = Math.round(Math.sqrt(0.04 * (Math.pow(num, 2))) + 83); //92 is the min value, 250 max
            double soundValue = Math.round(((-1 * Math.sqrt(0.003 * num)) + 6)); //6 is the max value, 1 min

            if (num <= 800) {
                distanceCheck = true;
                countValue++;
                changeAbleCountValue = value;
                if (distanceCheck && countValue >= changeAbleCountValue) {
                    player.playSound(ModSounds.HEART_BEAT.get(), (float) soundValue, 1f);
                    //System.out.println(value);
                    countValue = 0;
                }

            } else {
                distanceCheck = false;
            }
        }
    }

}
