package com.felipelsm.antonimod.mixin;

import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(EntityAttributes.class)
public class EntityAttributesMixin {
//    @ModifyConstant(method = "<clinit>", constant = @Constant(doubleValue = 30.0))
//    private static double increaseArmorCap(double constant) {
//        System.out.println("ANTONI MOD: Breaking the armor cap! Set to 100.0");
//        return 100.0; // Your new max armor
//    }
//
//    @ModifyConstant(method = "<clinit>", constant = @Constant(doubleValue = 20.0))
//    private static double increaseToughnessCap(double constant) {
//        return 50.0; // Your new max toughness
//    }
    @ModifyArgs(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/ClampedEntityAttribute;<init>(Ljava/lang/String;DDD)V")
    )
    private static void antoniMod$increaseSpecificCaps(Args args) {
        String translationKey = args.get(0);

        // Only modify if the attribute being created is Armor
        if ("attribute.name.generic.armor".equals(translationKey)) {
            // Args for ClampedEntityAttribute are: (Key, Default, Min, Max)
            // Index 3 is the Max value (originally 30.0)
            args.set(3, 100.0);
        }

        // Only modify if the attribute being created is Toughness
        if ("attribute.name.generic.armor_toughness".equals(translationKey)) {
            // Originally 20.0
            args.set(3, 50.0);
        }
    }
}