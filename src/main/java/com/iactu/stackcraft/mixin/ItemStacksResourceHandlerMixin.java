package com.iactu.stackcraft.mixin;

import com.iactu.stackcraft.StackcraftMod;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemStacksResourceHandler.class)
public abstract class ItemStacksResourceHandlerMixin {
    @ModifyConstant(
            method = "Lnet/neoforged/neoforge/transfer/item/ItemStacksResourceHandler;getCapacity(ILnet/neoforged/neoforge/transfer/item/ItemResource;)I",
            constant = @Constant(intValue = Item.ABSOLUTE_MAX_STACK_SIZE)
    )
    private int maximizeStackSize() {
        return StackcraftMod.ABSOLUTE_MAXIMUM_STACK_SIZE;
    }
}
