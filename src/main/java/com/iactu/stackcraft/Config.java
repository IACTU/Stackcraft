package com.iactu.stackcraft;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = StackcraftMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.IntValue DEFAULT_STACK_SIZE = BUILDER
            .comment("The new default stack size for items. Vanilla default stack size is 64. Default is 1000.")
            .defineInRange("defaultStackSize", 1000, 1, StackcraftMod.ABSOLUTE_MAXIMUM_STACK_SIZE);

    private static final ModConfigSpec.BooleanValue RESPECT_SMALL_STACK_SIZES = BUILDER
            .comment("Whether items with small stack sizes (ex: ender pearls) have a new stack size proportional to the new default stack size value. Setting this value to false will cause all items to have the same stack size. Default is true.")
            .define("respectSmallStackSizes", true);

    private static final ModConfigSpec.IntValue MINIMUM_DEFAULT_STACK_SIZE_THRESHOLD = BUILDER
            .comment("The minimum default stack size for any stack size adjustment to occur (ex: if this is set to 17 then ender pearls will keep their usual maximum stack size of 16).")
            .defineInRange("minimumDefaultStackSizeThreshold", 1, 0, Integer.MAX_VALUE);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static int defaultStackSize;

    public static boolean respectSmallStackSizes;

    public static int minimumDefaultStackSizeThreshold;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        defaultStackSize = DEFAULT_STACK_SIZE.get();
        respectSmallStackSizes = RESPECT_SMALL_STACK_SIZES.get();
        minimumDefaultStackSizeThreshold = MINIMUM_DEFAULT_STACK_SIZE_THRESHOLD.get();
    }
}