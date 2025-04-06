package com.iactu.stackcraft.mixin;

import com.iactu.stackcraft.Config;
import net.minecraft.world.item.BundleItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BundleItem.class)
public abstract class BundleItemMixin {
    @ModifyArg(
            method = "appendHoverText(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/List;Lnet/minecraft/world/item/TooltipFlag;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/Mth;mulAndTruncate(Lorg/apache/commons/lang3/math/Fraction;I)I"
            ),
            index = 1,
            order = 1100
    )
    private static int injectedMultiplier(int value) {
        return Config.defaultStackSize;
    }

    @ModifyArg(
            method = "appendHoverText(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/List;Lnet/minecraft/world/item/TooltipFlag;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/network/chat/Component;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;"
            ),
            index = 1,
            order = 1100
    )
    private static Object[] injectedTotal(Object[] args) {
        args[1] = Config.defaultStackSize;
        return args;
    }
}
