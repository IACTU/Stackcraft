package com.iactu.rigorousresearch;

import com.iactu.rigorousresearch.world.item.ModItems;
import com.iactu.rigorousresearch.world.item.ResearchVial;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.ItemLike;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(RigorousResearchMod.MOD_ID)
public class RigorousResearchMod { // TODO: remove instances of Example Mod, examplemod, example, ...
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "rigorous_research";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register(MOD_ID + "_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.rigorousresearch")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.ITEMS.getEntries().stream().toList().get(14).get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                ModItems.ITEMS.getEntries().forEach(entry -> output.accept(entry.get()));
            }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public RigorousResearchMod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (RigorousResearchMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) { // TODO consider what we really need here
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) { // TODO consider what we really need here
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) { // TODO consider what we really need here
        }

        @SubscribeEvent
        public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Item event) {
            ItemLike[] researchVials = new ItemLike[ModItems.RESEARCH_VIALS.size()];
            for(int i = 0; i < ModItems.RESEARCH_VIALS.size(); i++) {
                researchVials[i] = ModItems.RESEARCH_VIALS.get(i);
            }

            // Parameters are the item stack and the tint index.
            event.register(
                    // Like above, replace with your own calculation. Vanilla values are in the ItemColors class.
                    // Also like above, tint index -1 means no tint and should use a default value instead.
                    (pStack, pTintIndex) -> pTintIndex > 0 ? -1 : FastColor.ARGB32.opaque(((ResearchVial) pStack.getItem()).getColor().getTextureDiffuseColor()),
                    // A varargs of items to apply the tinting to
                    researchVials
            );
        }
    }
}

