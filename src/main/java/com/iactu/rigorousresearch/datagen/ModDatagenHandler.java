package com.iactu.rigorousresearch.datagen;

import com.iactu.rigorousresearch.RigorousResearchMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = RigorousResearchMod.MOD_ID)
public class ModDatagenHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(
                event.includeClient(),
                new ModItemModelProvider(output, existingFileHelper)
        );

        generator.addProvider(
                event.includeClient(),
                new ModLanguageProvider(output)
        );

//        generator.addProvider(
//                event.includeClient(),
//                new ModBlockStateProvider(output, existingFileHelper)
//        );

        generator.addProvider(
                event.includeServer(),
                new ModRecipeProvider(output, event.getLookupProvider())
        );
    }
}