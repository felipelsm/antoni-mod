package com.felipelsm.antonimod.datagen;

import com.felipelsm.antonimod.block.ModBlocks;
import com.felipelsm.antonimod.block.custom.AntoniLampBlock;
import com.felipelsm.antonimod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator BSModelGen)
    {
        // Define texture pool
        BlockStateModelGenerator.BlockTexturePool antoniBlockPool = BSModelGen.registerCubeAllModelTexturePool(ModBlocks.ANTONI_BLOCK);

        // Register Block Models
        antoniBlockPool.stairs(ModBlocks.ANTONI_STAIRS);
        antoniBlockPool.slab(ModBlocks.ANTONI_SLAB);
        antoniBlockPool.fence(ModBlocks.ANTONI_FENCE);
        antoniBlockPool.fenceGate(ModBlocks.ANTONI_FENCE_GATE);
        antoniBlockPool.wall(ModBlocks.ANTONI_WALL);
        antoniBlockPool.button(ModBlocks.ANTONI_BUTTON);
        antoniBlockPool.pressurePlate(ModBlocks.ANTONI_PRESSURE_PLATE);
        BSModelGen.registerDoor(ModBlocks.ANTONI_DOOR);
        BSModelGen.registerTrapdoor(ModBlocks.ANTONI_TRAPDOOR);

        List<Block> blockList = List.of(
                ModBlocks.ANTONI_DEEPSLATE_ORE_BLOCK,
                ModBlocks.ANTONI_ORE_BLOCK,
                ModBlocks.CHISELED_ANTONI_BLOCK,
                ModBlocks.MAGIC_ANTONI_BLOCK
        );
        for (Block block : blockList) {
            generateBlockAndItemModels(BSModelGen, block);
        }

        generateLampBlockModel(BSModelGen, ModBlocks.ANTONI_LAMP, AntoniLampBlock.ON);

        // Fix Inventory Models (Manually register items that are not generating automatically)
        generateBlockItemModel(BSModelGen, ModBlocks.ANTONI_BLOCK);
        generateBlockItemModel(BSModelGen, ModBlocks.ANTONI_FENCE_GATE);
        generateBlockItemModel(BSModelGen, ModBlocks.ANTONI_PRESSURE_PLATE);
    }

    /* Helper Methods for Block Model Generation */
    private void generateBlockAndItemModels(BlockStateModelGenerator BSModelGen, Block block)
    {
        BSModelGen.registerSimpleCubeAll(block);
        generateBlockItemModel(BSModelGen, block);
    }
    private void generateBlockItemModel(BlockStateModelGenerator BSModelGen, Block block)
    {
        BSModelGen.registerParentedItemModel(block, ModelIds.getBlockModelId(block));
    }
    private void generateLampBlockModel(BlockStateModelGenerator BSModelGen, Block lampBlock, BooleanProperty booleanProperty)
    {
        // OFF texture
        Identifier offModelId = Models.CUBE_ALL.upload(lampBlock, TextureMap.all(lampBlock), BSModelGen.modelCollector);

        // ON texture
        Identifier onTextureId = TextureMap.getSubId(lampBlock, "_on");
        Identifier onModelId = Models.CUBE_ALL.upload(lampBlock, "_on", TextureMap.all(onTextureId), BSModelGen.modelCollector);
//        Identifier onModelId = TexturedModel.makeFactory(block -> TextureMap.all(TextureMap.getSubId(block, "_on")), Models.CUBE_ALL)
//                .get(lampBlock)
//                .upload(lampBlock, "_on", BSModelGen.modelCollector);

        // Generate Model Variants
        BSModelGen.blockStateCollector.accept(VariantsBlockStateSupplier.create(lampBlock)
                .coordinate(BlockStateVariantMap.create(booleanProperty)
                        .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, offModelId))
                        .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, onModelId)))
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator iModelGen)
    {
        List<Item> itemList = List.of(
                ModItems.ANTONI_ORE,
                ModItems.RAW_ANTONI_ORE,
                ModItems.ANTONI_FOOD,
                // ModItems.ANTONI_CHISEL, // Need to make json by hand due to Model Predicates
                ModItems.ANTONI_FUEL_BUCKET
        );
        for (Item item : itemList) {
            iModelGen.register(item, Models.GENERATED);
        }

        List<Item> handheldItemList = List.of(
                ModItems.ANTONI_SWORD,
                ModItems.ANTONI_SHOVEL,
                ModItems.ANTONI_PICKAXE,
                ModItems.ANTONI_AXE,
                ModItems.ANTONI_HOE,
                ModItems.ANTONI_HAMMER,
                ModItems.ANTONI_SUPER_HAMMER
        );
        for (Item item : handheldItemList) {
            iModelGen.register(item, Models.HANDHELD);
        }
    }
}
