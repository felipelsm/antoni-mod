package com.felipelsm.antonimod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents
{
    public static final FoodComponent ANTONI_FOOD = new FoodComponent.Builder()
            .nutrition(1)
            .saturationModifier(0.1F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 60), 0.5F)
            .build();
}
