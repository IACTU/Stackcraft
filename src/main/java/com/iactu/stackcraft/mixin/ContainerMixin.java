package com.iactu.stackcraft.mixin;

import com.iactu.stackcraft.StackcraftMod;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Container.class)
public interface ContainerMixin {
    @ModifyReturnValue(
            method = "Lnet/minecraft/world/Container;getMaxStackSize()I",
            at = @At("RETURN")
    )
    private int maximizeStackSize(int original) {
        return StackcraftMod.ABSOLUTE_MAXIMUM_STACK_SIZE;
    }
}
