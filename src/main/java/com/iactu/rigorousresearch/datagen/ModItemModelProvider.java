package com.iactu.rigorousresearch.datagen;

import com.iactu.rigorousresearch.world.item.ModItems;
import com.iactu.rigorousresearch.world.item.ResearchVial;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, "rigorousresearch", existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(DeferredItem<ResearchVial> researchVialDeferredItem : ModItems.RESEARCH_VIALS) {
            withExistingParent(
                    researchVialDeferredItem.getRegisteredName(),
                    mcLoc("item/potion")
            );
        }
    }
}
