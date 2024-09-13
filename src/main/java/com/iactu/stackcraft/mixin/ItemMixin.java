package com.iactu.stackcraft.mixin;

import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class ItemMixin implements FeatureElement, ItemLike, net.neoforged.neoforge.common.extensions.IItemExtension {
    @Shadow
    public static final int ABSOLUTE_MAX_STACK_SIZE = Integer.MAX_VALUE;    // TODO: not working!
}
