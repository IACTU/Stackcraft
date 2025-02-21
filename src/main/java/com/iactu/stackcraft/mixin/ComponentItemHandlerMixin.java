package com.iactu.stackcraft.mixin;

import com.iactu.stackcraft.StackcraftMod;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.Container;
import net.neoforged.neoforge.items.ComponentItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ComponentItemHandler.class)
public abstract class ComponentItemHandlerMixin {
    @ModifyReturnValue(
            method = "Lnet/neoforged/neoforge/items/ComponentItemHandler;getSlotLimit(I)I",
            at = @At("RETURN")
    )
    private int maximizeStackSize(int original) {
        return StackcraftMod.ABSOLUTE_MAXIMUM_STACK_SIZE;
    }
}
