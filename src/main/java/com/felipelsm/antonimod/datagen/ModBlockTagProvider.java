package com.felipelsm.antonimod.datagen;

import com.felipelsm.antonimod.block.ModBlocks;
import com.felipelsm.antonimod.util.ModTags;
import com.jcraft.jorbis.Block;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider
{
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup)
    {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.ANTONI_DEEPSLATE_ORE_BLOCK)
                .add(ModBlocks.ANTONI_ORE_BLOCK)
                .add(ModBlocks.ANTONI_BLOCK)
                .add(ModBlocks.CHISELED_ANTONI_BLOCK);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.ANTONI_BLOCK);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.ANTONI_BLOCK);

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(ModBlocks.ANTONI_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ANTONI_DEEPSLATE_ORE_BLOCK)
                .add(ModBlocks.ANTONI_ORE_BLOCK)
                .add(ModBlocks.ANTONI_BLOCK)
                .add(ModBlocks.CHISELED_ANTONI_BLOCK)
                .add(ModBlocks.ANTONI_LAMP);
        //getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL);
        //getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL);

        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_ANTONI_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);


        getOrCreateTagBuilder(BlockTags.FENCES).add(ModBlocks.ANTONI_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(ModBlocks.ANTONI_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.ANTONI_WALL);
    }
}
