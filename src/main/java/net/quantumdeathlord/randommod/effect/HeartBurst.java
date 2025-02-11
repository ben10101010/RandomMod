package net.quantumdeathlord.randommod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HeartBurst extends MobEffect {
    public HeartBurst(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

        if(pLivingEntity.getHealth() > 2f){
            pLivingEntity.hurt(pLivingEntity.damageSources().magic(), 2f);
        }
        else {
            pLivingEntity.level().explode(pLivingEntity, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), 1f, Level.ExplosionInteraction.MOB);
            pLivingEntity.hurt(pLivingEntity.damageSources().explosion(pLivingEntity, null), 100f);
        }

        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }


}
