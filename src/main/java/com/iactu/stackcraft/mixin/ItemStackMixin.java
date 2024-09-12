package com.iactu.stackcraft.mixin;

import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements DataComponentHolder, net.neoforged.neoforge.common.extensions.IItemStackExtension, net.neoforged.neoforge.common.MutableDataComponentHolder {
    @ModifyConstant(method = "lambda$static$3", constant = @Constant(intValue = 99))
    private static int injected(int value) {
        return Integer.MAX_VALUE;
    }
}
