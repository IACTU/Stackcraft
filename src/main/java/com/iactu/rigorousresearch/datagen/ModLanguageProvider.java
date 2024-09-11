package com.iactu.rigorousresearch.datagen;

import com.iactu.rigorousresearch.RigorousResearchMod;
import com.iactu.rigorousresearch.world.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.WordUtils;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(
                // Provided by the GatherDataEvent.
                output,
                // Your mod id.
                RigorousResearchMod.MOD_ID,
                // The locale to use. You may use multiple language providers for different locales.
                "en_us"
        );
    }

    @Override
    protected void addTranslations() {
        ModItems.RESEARCH_VIALS.forEach(researchVialDeferredItem -> {
            String path = researchVialDeferredItem.getId().getPath();
            String[] parts = path.split("_");
            String nameEnding = parts[0] + " " + parts[1];
            StringBuilder nameStart = new StringBuilder();
            for(int i = 2; i < parts.length; i++) {
                nameStart.append(parts[i]);
                nameStart.append(" ");
            }
            nameStart.append(nameEnding);

            String name = WordUtils.capitalize(nameStart.toString());

            addItem(researchVialDeferredItem, name);
        });

        add("itemGroup.rigorousresearch", "Rigorous Research");
    }
}
