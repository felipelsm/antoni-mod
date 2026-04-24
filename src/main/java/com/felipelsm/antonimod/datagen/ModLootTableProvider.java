package com.felipelsm.antonimod.datagen;

import com.felipelsm.antonimod.block.ModBlocks;
import com.felipelsm.antonimod.item.ModItems;
import com.felipelsm.antonimod.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.data.DataWriter;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModLootTableProvider extends FabricBlockLootTableProvider
{
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup)
    {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate()
    {
        addDrop(ModBlocks.ANTONI_BLOCK);
        addDrop(ModBlocks.CHISELED_ANTONI_BLOCK);
        addDrop(ModBlocks.MAGIC_ANTONI_BLOCK);

        addDrop(ModBlocks.ANTONI_ORE_BLOCK, oreDrops(ModBlocks.ANTONI_ORE_BLOCK, ModItems.RAW_ANTONI_ORE));
        addDrop(ModBlocks.ANTONI_DEEPSLATE_ORE_BLOCK, multipleOreDrops(ModBlocks.ANTONI_ORE_BLOCK, ModItems.RAW_ANTONI_ORE, 2.0F, 4.0F));

        addDrop(ModBlocks.ANTONI_STAIRS);
        addDrop(ModBlocks.ANTONI_SLAB, slabDrops(ModBlocks.ANTONI_SLAB));
        addDrop(ModBlocks.ANTONI_FENCE);
        addDrop(ModBlocks.ANTONI_FENCE_GATE);
        addDrop(ModBlocks.ANTONI_WALL);
        addDrop(ModBlocks.ANTONI_BUTTON);
        addDrop(ModBlocks.ANTONI_PRESSURE_PLATE);
        addDrop(ModBlocks.ANTONI_DOOR, doorDrops(ModBlocks.ANTONI_DOOR));
        addDrop(ModBlocks.ANTONI_TRAPDOOR);
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDropsNum, float maxDropsNum) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(
                drop,
                (LootPoolEntry.Builder<?>)this.applyExplosionDecay(
                        drop,
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDropsNum, maxDropsNum)))
                                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }
}
