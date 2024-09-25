package com.iactu.stackcraft.mixin;

import com.iactu.stackcraft.StackcraftMod;
import net.minecraft.core.component.DataComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(DataComponents.class)
public abstract class DataComponentsMixin {
    @ModifyConstant(method = "lambda$static$1", constant = @Constant(intValue = 99))
    private static int injected(int value) {
        return StackcraftMod.ABSOLUTE_MAXIMUM_STACK_SIZE;
    }
}
