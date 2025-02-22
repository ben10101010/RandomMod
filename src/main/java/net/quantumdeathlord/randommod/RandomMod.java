package net.quantumdeathlord.randommod;

import com.mojang.brigadier.ParseResults;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.quantumdeathlord.randommod.block.ModBlocks;
import net.quantumdeathlord.randommod.CreativeTabs.ModCreativeModTabs;
import net.quantumdeathlord.randommod.client.Keybindings;
import net.quantumdeathlord.randommod.effect.ModEffects;
import net.quantumdeathlord.randommod.entity.ModEntities;
import net.quantumdeathlord.randommod.entity.client.ChairRenderer;
import net.quantumdeathlord.randommod.entity.client.NightReaperRenderer;
import net.quantumdeathlord.randommod.item.ModItems;
import net.quantumdeathlord.randommod.sound.ModSounds;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RandomMod.MOD_ID)
public class RandomMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "random_mod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    public RandomMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModEffects.register(modEventBus);
        ModSounds.register(modEventBus);



        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::registerBindings);
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.ALEXANDRITE);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.ALEXANDRITE_BLOCK);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.CHAIR.get(), ChairRenderer::new);

            EntityRenderers.register(ModEntities.NIGHT_REAPER.get(), NightReaperRenderer::new);
        }
    }

    @SubscribeEvent
    public void registerBindings(RegisterKeyMappingsEvent event){
        event.register(Keybindings.HEART_RIP.get());
    }

    private static final Component HAHA = Component.translatable("MWAHAHAHA");
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent.Post event) {

        Player player = Minecraft.getInstance().player;
        if (event.phase == TickEvent.Phase.END) {
            if (Keybindings.HEART_RIP.get().isDown() && player != null) {
                Keybindings.HEART_RIP.get().consumeClick();
                player.addEffect(new MobEffectInstance(ModEffects.HEART_BURST.getHolder().get(), 800, 0, true, true));
                player.displayClientMessage(HAHA, true);
               // ClientCommandHandler.runCommand("/effect give " + player.getName() + " random_mod:heartburst");
               // MinecraftServer source = player.getServer();
                //source.getCommands().performCommand(source.createCommandSourceStack(), "/effect give " + player.getName() + " random_mod:heartburst");
            }
        }
    }

}
