package com.felipelsm.antonimod.item;

import com.felipelsm.antonimod.AntoniMod;
import com.felipelsm.antonimod.fluid.ModFluids;
import com.felipelsm.antonimod.item.custom.ChiselItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

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
    public static final Item ANTONI_ORE = registerItem("antoni_ore", new Item(new Item.Settings()));
    public static final Item RAW_ANTONI_ORE = registerItem("raw_antoni_ore", new Item(new Item.Settings()));

    public static final Item ANTONI_CHISEL = registerItem("antoni_chisel", new ChiselItem(new Item.Settings().maxDamage(64)));

    public static final Item ANTONI_FOOD = registerItem("antoni_food", new Item(new Item.Settings().food(ModFoodComponents.ANTONI_FOOD)));

    public static final Item ANTONI_FUEL_BUCKET = registerItem("antoni_fuel_bucket", new BucketItem(ModFluids.ANTONI_FUEL, new Item.Settings().recipeRemainder(BUCKET).maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(AntoniMod.MOD_ID, name), item);
    }

    public static void registerModItems()
    {
        AntoniMod.LOGGER.info("Registering Mod Items for " + AntoniMod.MOD_ID);

//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
//            entries.add(ANTONI_ORE);
//            entries.add(RAW_ANTONI_ORE);
//        });

        AntoniMod.LOGGER.info("Finished Registering Mod Items for " + AntoniMod.MOD_ID);
    }
}
