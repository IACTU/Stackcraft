package com.iactu.rigorousresearch.world.item;

import com.iactu.rigorousresearch.RigorousResearchMod;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    // Create a Deferred Register to hold Items which will all be registered under the "rigorousresearch" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RigorousResearchMod.MOD_ID);
    public static final List<DeferredItem<ResearchVial>> RESEARCH_VIALS = new ArrayList<>();

    static {
        for (DyeColor dyeColor : DyeColor.values()) {
            DeferredItem<ResearchVial> researchVialDeferredItem = ITEMS.registerItem(
                    "research_vial_" + dyeColor.getName(),
                    pProperties -> new ResearchVial(dyeColor, pProperties),
                    new Item.Properties()
            );
            RESEARCH_VIALS.add(researchVialDeferredItem);
        }
    }

}