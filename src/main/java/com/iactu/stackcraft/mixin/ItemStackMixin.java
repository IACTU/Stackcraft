package com.iactu.stackcraft.mixin;

import com.iactu.stackcraft.StackcraftMod;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements DataComponentHolder, net.neoforged.neoforge.common.extensions.IItemStackExtension, net.neoforged.neoforge.common.MutableDataComponentHolder {
    @ModifyArg(
            method = "lambda$static$1",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/ExtraCodecs;intRange(II)Lcom/mojang/serialization/Codec;"
            ),
            index = 1,
            order = 1100
    )
    private static int injected(int value) {
        return StackcraftMod.ABSOLUTE_MAXIMUM_STACK_SIZE;
    }
}
