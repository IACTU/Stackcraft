package com.iactu.stackcraft.mixin;

import com.iactu.stackcraft.StackcraftMod;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.Capabilities.ItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStackHandler.class)
public abstract class ItemStackHandlerMixin {
    @ModifyReturnValue(
            method = "Lnet/neoforged/neoforge/items/ItemStackHandler;getSlotLimit(I)I",
            at = @At("RETURN")
    )
    private int maximizeStackSize(int original) {
        return StackcraftMod.ABSOLUTE_MAXIMUM_STACK_SIZE;
    }
}
