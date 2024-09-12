package com.iactu.stackcraft.mixin;

import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Container.class)
public interface ContainerMixin {
    /**
     * @author IACTU
     * @reason Allows for stacks of more than 99 items.
     */
    @Overwrite
    default int getMaxStackSize() {
        return Integer.MAX_VALUE;
    }
}
