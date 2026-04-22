package com.felipelsm.antonimod.item;

import com.felipelsm.antonimod.AntoniMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems
{
    public static final Item ANTONI_ORE = registerItem("antoni_ore", new Item(new Item.Settings()));
    public static final Item RAW_ANTONI_ORE = registerItem("raw_antoni_ore", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(AntoniMod.MOD_ID, name), item);
    }

    public static void registerModItems()
    {
        AntoniMod.LOGGER.info("Registering Mod Items for " + AntoniMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(ANTONI_ORE);
            entries.add(RAW_ANTONI_ORE);
        });

        AntoniMod.LOGGER.info("Finished Registering Mod Items for " + AntoniMod.MOD_ID);
    }
}
