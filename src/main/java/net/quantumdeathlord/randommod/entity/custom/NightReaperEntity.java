package net.quantumdeathlord.randommod.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Random;

public class NightReaperEntity extends Monster implements GeoEntity {
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.night_reaper.idle");
    protected static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.night_reaper.walk");
    protected static final RawAnimation RUN_ANIM = RawAnimation.begin().thenLoop("animation.night_reaper.run");
    protected static final RawAnimation ATTACK_ANIM = RawAnimation.begin().thenPlay("animation.night_reaper.attack");

    private final AnimatableInstanceCache m_animCache = GeckoLibUtil.createInstanceCache(this);

    public NightReaperEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new FloatGoal(this));

        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers(NightReaperEntity.class));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 4.0, true));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 700.0d)
                .add(Attributes.FOLLOW_RANGE, 128.0d)
                .add(Attributes.ATTACK_DAMAGE, 16.0d)
                .add(Attributes.MOVEMENT_SPEED, .21d)
                .add(Attributes.KNOCKBACK_RESISTANCE, .8d)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0d);
    }


    @Override
    public void tick() {
        super.tick();

      ///  if(this.level().isClientSide()){
       // }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "attackController", 0, this::attackPredicate));
    }

    protected <E extends GeoAnimatable> PlayState predicate(final AnimationState<E> event){
        if (event.isMoving()){
            if(isInWater()){
                event.getController().setAnimation(WALK_ANIM);
            }else{
                event.getController().setAnimation(RUN_ANIM);
            }
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(IDLE_ANIM);
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationState event){
        if(this.swinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)){
            event.getController().forceAnimationReset();
            event.getController().setAnimation(ATTACK_ANIM);
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return m_animCache;
    }
}
