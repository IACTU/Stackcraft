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

@Mod(StackcraftMod.MOD_ID)
public class StackcraftMod {
    public static final String MOD_ID = "stackcraft";

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final int ABSOLUTE_MAXIMUM_STACK_SIZE = Integer.MAX_VALUE / 2;

    public StackcraftMod(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @EventBusSubscriber(modid = MOD_ID)
    public static class CommonModEvents {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onModifyDefaultComponentsEvent(ModifyDefaultComponentsEvent event) {
            event.getAllItems().filter(item ->
                                    !item.components().has(DataComponents.DAMAGE)
                                            && !item.components().has(DataComponents.MAX_DAMAGE)
                                            && item.getDefaultMaxStackSize() >= Config.minimumDefaultStackSizeThreshold
                    )
                    .forEach(item -> {
                        int newDefaultMaxStackSize;
                        if (Config.respectSmallStackSizes) {
                            newDefaultMaxStackSize = Math.clamp(
                                    (((long) item.getDefaultMaxStackSize()) * Config.defaultStackSize) / 64,
                                    1,
                                    ABSOLUTE_MAXIMUM_STACK_SIZE
                            );
                        } else {
                            newDefaultMaxStackSize = Config.defaultStackSize;
                        }

                        event.modify(item, (builder -> builder.set(DataComponents.MAX_STACK_SIZE, newDefaultMaxStackSize)));
                    });
        }
    }
}

