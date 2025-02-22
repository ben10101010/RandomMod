package net.quantumdeathlord.randommod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.quantumdeathlord.randommod.RandomMod;
import net.quantumdeathlord.randommod.entity.custom.ChairEntity;
import net.quantumdeathlord.randommod.entity.custom.NightReaperEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RandomMod.MOD_ID);

    public static final RegistryObject<EntityType<ChairEntity>> CHAIR =
            ENTITY_TYPES.register("chair_entity", () -> EntityType.Builder.of(ChairEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("chair_entity"));

    public static final RegistryObject<EntityType<NightReaperEntity>> NIGHT_REAPER =
            ENTITY_TYPES.register("night_reaper", () -> EntityType.Builder.of(NightReaperEntity::new, MobCategory.MONSTER)
                    .sized(1.5f, 4f).fireImmune().build("night_reaper"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
