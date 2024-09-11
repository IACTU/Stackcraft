package com.iactu.rigorousresearch.world.item;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

public class ResearchVial extends Item {
    private final DyeColor color;

    public ResearchVial(DyeColor pDyeColor, Item.Properties pProperties) {
        super(pProperties);
        this.color = pDyeColor;
    }

    public DyeColor getColor() {
        return color;
    }
}
