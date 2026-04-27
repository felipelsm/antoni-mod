package com.felipelsm.antonimod.item;

import com.felipelsm.antonimod.AntoniMod;
import com.felipelsm.antonimod.fluid.ModFluids;
import com.felipelsm.antonimod.item.custom.AntoniChiselItem;
import com.felipelsm.antonimod.item.custom.AntoniHammerItem;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

import static net.minecraft.item.Items.BUCKET;

/**
 * Custom ModItems need to be addressed in
 *
 * @1- resources.assets.modid.lang
 * @2- resources.assets.modid.models.item
 * @3- resources.assets.modid.textures.item
 * @4- resources.data.modid.recipe
 * @5- resources.data.modid.loot_table.blocks
 * @6- java..modid.item.ModItemGroups
 * @7- java..modid.item.ModItems.registerModItems
 */
public class ModItems
{
    public static final Item ANTONI_ORE = registerItem(
            "antoni_ore",
            new Item(new Item.Settings())
    );
    public static final Item RAW_ANTONI_ORE = registerItem(
            "raw_antoni_ore",
            new Item(new Item.Settings())
    );

    public static final Item ANTONI_SWORD = registerItem(
            "antoni_sword",
            new SwordItem(ModToolMaterials.ANTONI_ORE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ANTONI_ORE, 3,-2.4F))
            )
    );
    public static final Item ANTONI_SHOVEL = registerItem(
            "antoni_shovel",
            new ShovelItem(ModToolMaterials.ANTONI_ORE, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.ANTONI_ORE, 1.5F, -3.0F))
            )
    );
    public static final Item ANTONI_PICKAXE = registerItem(
            "antoni_pickaxe",
            new PickaxeItem(ModToolMaterials.ANTONI_ORE, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.ANTONI_ORE, 1.0F, -2.8F))
            )
    );
    public static final Item ANTONI_AXE = registerItem(
            "antoni_axe",
            new AxeItem(ModToolMaterials.ANTONI_ORE, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.ANTONI_ORE, 5.0F, -3.0F))
            )
    );
    public static final Item ANTONI_HOE = registerItem(
            "antoni_hoe",
            new HoeItem(ModToolMaterials.ANTONI_ORE, new Item.Settings()
                    .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.ANTONI_ORE, -4.0F, 0.0F))
            )
    );
    public static final Item ANTONI_HAMMER = registerItem(
            "antoni_hammer",
            new AntoniHammerItem(20, 1, ModToolMaterials.ANTONI_ORE, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.ANTONI_ORE, 5.5F, -2.5F))
            )
    );
    public static final Item ANTONI_SUPER_HAMMER = registerItem(
            "antoni_super_hammer",
            new AntoniHammerItem(30, 3, ModToolMaterials.ANTONI_ORE, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.ANTONI_ORE, 10.0F, -2.0F))
            )
    );

    public static final Item ANTONI_CHISEL = registerItem(
            "antoni_chisel",
            new AntoniChiselItem(new Item.Settings()
                    .maxDamage(64)
            )
    );

    public static final Item ANTONI_FOOD = registerItem(
            "antoni_food",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.ANTONI_FOOD)
            ) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.antonimod.antoni_food"));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });

    public static final Item ANTONI_FUEL_BUCKET = registerItem(
            "antoni_fuel_bucket",
            new BucketItem(ModFluids.ANTONI_FUEL, new Item.Settings()
                    .recipeRemainder(BUCKET)
                    .maxCount(1)
            ) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.antonimod.antoni_fuel_bucket"));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(AntoniMod.MOD_ID, name), item);
    }

    public static void registerModItems()
    {
        AntoniMod.LOGGER.info("Registering Mod Items for " + AntoniMod.MOD_ID);

        /* Register Mod Fuels */
        FuelRegistry.INSTANCE.add(ModItems.ANTONI_FUEL_BUCKET, 32767); // Max cooktime value allowed

//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> { entries.add(ANTONI_ORE); });

        AntoniMod.LOGGER.info("Finished Registering Mod Items for " + AntoniMod.MOD_ID);
    }
}
