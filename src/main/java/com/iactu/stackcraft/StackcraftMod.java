package com.iactu.stackcraft;

import com.mojang.logging.LogUtils;
import net.minecraft.core.component.DataComponents;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(StackcraftMod.MOD_ID)
public class StackcraftMod { // TODO: remove instances of Example Mod, examplemod, example, ...
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "stackcraft";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public StackcraftMod(IEventBus modEventBus, ModContainer modContainer) {
    // TODO: add visual change to show stack size as somethign like 1K
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (StackcraftMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        //NeoForge.EVENT_BUS.register(this);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class CommonModEvents {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onModifyDefaultComponentsEvent(ModifyDefaultComponentsEvent event) {
            event.getAllItems().filter(item -> item.getDefaultMaxStackSize() > 1)
                    .forEach(item -> {
                        int newDefaultMaxStackSize;
                        if (Config.respectSmallStackSizes) {
                            newDefaultMaxStackSize = Math.max((item.getDefaultMaxStackSize() * Config.defaultStackSize) / 64, 1);
                        } else {
                            newDefaultMaxStackSize = Config.defaultStackSize;
                        }

                        event.modify(item, (builder -> builder.set(DataComponents.MAX_STACK_SIZE, newDefaultMaxStackSize)));
                    });
        }
    }
}

