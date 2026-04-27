package com.felipelsm.antonimod.item;

import com.felipelsm.antonimod.AntoniMod;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

//public enum ModArmorMaterials implements ArmorMaterial {
//    ANTONI("antoni", 40, new int[]{5, 10, 12, 5}, 25,
//            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.0f, 0.2f, () -> Ingredient.ofItems(ModItems.ANTONI_ORE));
//
//    // The 'protectionAmounts' array above (5, 10, 12, 5) adds up to 32 armor points.
//    // Without the change in Step 1, this would be capped at 30.
//    // Without Step 2, the player would only see 10 icons.
//}

public class ModArmorMaterials
{
    public static RegistryEntry<ArmorMaterial> ANTONI_ARMOR_MATERIAL = registerArmorMaterial(
            "antoni_ore",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 5);
                map.put(ArmorItem.Type.LEGGINGS, 8);
                map.put(ArmorItem.Type.CHESTPLATE, 10);
                map.put(ArmorItem.Type.HELMET, 5);
                map.put(ArmorItem.Type.BODY, 15);
            }),
                    30, // enchantability
                    SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
                    () -> Ingredient.ofItems(ModItems.ANTONI_ORE),
                    List.of(new ArmorMaterial.Layer(Identifier.of(AntoniMod.MOD_ID, "antoni_ore"))),
                    3.0F, // toughness
                    1.0F // knockback resistance
            )
    );

    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material)
    {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(AntoniMod.MOD_ID, name), material.get());
    }
}
