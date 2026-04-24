package com.felipelsm.antonimod.item;

import com.felipelsm.antonimod.AntoniMod;
import com.felipelsm.antonimod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups
{
    public static final ItemGroup ANTONI_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(AntoniMod.MOD_ID, "antoni_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.ANTONI_ORE))
                    .displayName(Text.translatable("itemgroup.antonimod.antoni_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.ANTONI_ORE);
                        entries.add(ModItems.RAW_ANTONI_ORE);
                        entries.add(ModItems.ANTONI_CHISEL);
                        entries.add(ModItems.ANTONI_FOOD);
                        entries.add(ModItems.ANTONI_FUEL_BUCKET);
                    })
                    .build());

    public static final ItemGroup ANTONI_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(AntoniMod.MOD_ID, "antoni_blocks"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.ANTONI_ORE_BLOCK))
                    .displayName(Text.translatable("itemgroup.antonimod.antoni_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.ANTONI_ORE_BLOCK);
                        entries.add(ModBlocks.ANTONI_DEEPSLATE_ORE_BLOCK);
                        entries.add(ModBlocks.ANTONI_BLOCK);
                        entries.add(ModBlocks.CHISELED_ANTONI_BLOCK);
                        entries.add(ModBlocks.MAGIC_ANTONI_BLOCK);
                    })
                    .build());

    public static void registerItemGroups()
    {
        AntoniMod.LOGGER.info("Registered Item Groups for " + AntoniMod.MOD_ID);
    }
}
