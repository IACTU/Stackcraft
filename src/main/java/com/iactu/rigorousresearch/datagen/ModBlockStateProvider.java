package com.iactu.rigorousresearch.datagen;

import com.iactu.rigorousresearch.RigorousResearchMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RigorousResearchMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
//        fenceBlockWithItem(ModBlocks.OAK_LOG_FENCE.get(), mcLoc("block/oak_log"));
//        fenceBlockWithItem(ModBlocks.OBSIDIAN_FENCE.get(), mcLoc("block/obsidian"));
//
//        ModBlocks.derivedBlocktoBaseBlockMap.forEach((derivedBlock, baseBlock) -> {
//            fenceBlockWithItem((FenceBlock) derivedBlock.get(), blockTexture(baseBlock.get()));
//        });
    }

    protected ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    protected void fenceBlockWithItem(FenceBlock block, ResourceLocation texture) {
        fenceBlock(block, texture);

        String baseName = key(block).toString();
        ModelFile model = models().withExistingParent(baseName + "_inventory", "minecraft:block/fence_inventory")
                .texture("texture", texture);
        simpleBlockItem(block, model);
    }
}
