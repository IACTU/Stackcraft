package com.iactu.stackcraft.mixin;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import javax.annotation.Nullable;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin implements net.neoforged.neoforge.client.extensions.IGuiGraphicsExtension {
    @ModifyVariable(
            method = "renderItemCount(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
            at = @At("STORE"),
            name = {"s"}
    )
    private String injected(String s, Font pFont, ItemStack pStack, int pX, int pY, @Nullable String pText) {
        if (pStack.getCount() < 1000 || pText != null) {
            return s;
        }

        // if we got this far, we need to show a count of 1000 or more
        return (pStack.getCount() / 1000) + "K";
    }
}
