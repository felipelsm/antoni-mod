package com.felipelsm.antonimod.datagen;

import com.felipelsm.antonimod.block.ModBlocks;
import com.felipelsm.antonimod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

import java.util.List;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
    {
        // Define texture pool
        BlockStateModelGenerator.BlockTexturePool antoniBlockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.ANTONI_BLOCK);

        // Register Block Models
        antoniBlockPool.stairs(ModBlocks.ANTONI_STAIRS);
        antoniBlockPool.slab(ModBlocks.ANTONI_SLAB);
        antoniBlockPool.fence(ModBlocks.ANTONI_FENCE);
        antoniBlockPool.fenceGate(ModBlocks.ANTONI_FENCE_GATE);
        antoniBlockPool.wall(ModBlocks.ANTONI_WALL);
        antoniBlockPool.button(ModBlocks.ANTONI_BUTTON);
        antoniBlockPool.pressurePlate(ModBlocks.ANTONI_PRESSURE_PLATE);
        blockStateModelGenerator.registerDoor(ModBlocks.ANTONI_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.ANTONI_TRAPDOOR);

        List<Block> blockList = List.of(
                ModBlocks.ANTONI_DEEPSLATE_ORE_BLOCK,
                ModBlocks.ANTONI_ORE_BLOCK,
                ModBlocks.CHISELED_ANTONI_BLOCK,
                ModBlocks.MAGIC_ANTONI_BLOCK
        );
        for (Block block : blockList) {
            generateBlockAndItemModels(blockStateModelGenerator, block);
        }

        // Fix Inventory Models (Manually register items that are not generating automatically)
        generateBlockItemModel(blockStateModelGenerator, ModBlocks.ANTONI_BLOCK);
        generateBlockItemModel(blockStateModelGenerator, ModBlocks.ANTONI_FENCE_GATE);
        generateBlockItemModel(blockStateModelGenerator, ModBlocks.ANTONI_PRESSURE_PLATE);
    }

    private void generateBlockAndItemModels(BlockStateModelGenerator blockStateModelGenerator, Block block)
    {
        blockStateModelGenerator.registerSimpleCubeAll(block);
        generateBlockItemModel(blockStateModelGenerator, block);
    }
    private void generateBlockItemModel(BlockStateModelGenerator blockStateModelGenerator, Block block)
    {
        blockStateModelGenerator.registerParentedItemModel(block, ModelIds.getBlockModelId(block));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator)
    {
        List<Item> itemList = List.of(
                ModItems.ANTONI_ORE,
                ModItems.RAW_ANTONI_ORE,
                ModItems.ANTONI_FOOD,
                ModItems.ANTONI_CHISEL,
                ModItems.ANTONI_FUEL_BUCKET
        );

        for (Item item : itemList) {
            itemModelGenerator.register(item, Models.GENERATED);
        }
    }
}
