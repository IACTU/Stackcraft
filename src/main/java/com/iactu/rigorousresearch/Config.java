package com.iactu.rigorousresearch;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = RigorousResearchMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue PAINTING_DROPS_VARIANT = BUILDER
            .comment("Whether a painting drops its specific variant when broken. Set to false for vanilla behavior.")
            .define("paintingDropsVariant", true);  // TODO: move this to be a per-save config option rather than common

    private static final ModConfigSpec.BooleanValue PICKING_GIVES_VARIANT = BUILDER
            .comment("Whether 'picking' (middle-clicking in creative) a painting gives its specific variant. Set to false for vanilla behavior.")
            .define("pickingGivesVariant", true);  // TODO: move this to be a per-save config option rather than common

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean paintingDropsVariant;
    public static boolean pickingGivesVariant;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        paintingDropsVariant = PAINTING_DROPS_VARIANT.get();
        pickingGivesVariant = PICKING_GIVES_VARIANT.get();
    }
}
