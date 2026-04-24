package com.felipelsm.antonimod.datagen;

import com.felipelsm.antonimod.AntoniMod;
import com.felipelsm.antonimod.block.ModBlocks;
import com.felipelsm.antonimod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider
{
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter)
    {
        /* Smelting Tables */
        List<ItemConvertible> ANTONI_ORE_SMELTABLES =
                List.of(
                        ModItems.RAW_ANTONI_ORE,
                        ModBlocks.ANTONI_ORE_BLOCK,
                        ModBlocks.ANTONI_DEEPSLATE_ORE_BLOCK
                );
        offerSmelting(exporter,
                ANTONI_ORE_SMELTABLES, RecipeCategory.MISC, ModItems.ANTONI_ORE,
                0.3F, 200, "antoni_ore");
        offerBlasting(exporter,
                ANTONI_ORE_SMELTABLES, RecipeCategory.MISC, ModItems.ANTONI_ORE,
                0.6F, 100, "antoni_ore");

        List<ItemConvertible> ANTONI_FOOD_SMELTABLES =
                List.of(ModItems.ANTONI_ORE);
        offerSmelting(exporter,
                ANTONI_FOOD_SMELTABLES, RecipeCategory.FOOD, ModItems.ANTONI_ORE,
                0.3F, 200, "antoni_ore");
        offerBlasting(exporter,
                ANTONI_FOOD_SMELTABLES, RecipeCategory.FOOD, ModItems.ANTONI_ORE,
                0.6F, 100, "antoni_ore");

        /* Non-block Blocks */
        createStairsRecipe(ModBlocks.ANTONI_STAIRS, Ingredient.ofItems(ModItems.ANTONI_ORE))
                .criterion(hasItem(ModItems.RAW_ANTONI_ORE), conditionsFromItem(ModItems.RAW_ANTONI_ORE))
                .offerTo(exporter);
        offerSlabRecipe(exporter,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.ANTONI_SLAB, ModItems.ANTONI_ORE);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.ANTONI_BUTTON, 1)
                .input(ModItems.ANTONI_ORE)
                .criterion(hasItem(ModItems.RAW_ANTONI_ORE), conditionsFromItem(ModItems.RAW_ANTONI_ORE))
                .offerTo(exporter);
        offerPressurePlateRecipe(exporter,
                ModBlocks.ANTONI_PRESSURE_PLATE, ModItems.ANTONI_ORE);
        createFenceRecipe(ModBlocks.ANTONI_FENCE, Ingredient.ofItems(ModItems.ANTONI_ORE))
                .criterion(hasItem(ModItems.RAW_ANTONI_ORE), conditionsFromItem(ModItems.RAW_ANTONI_ORE))
                .offerTo(exporter);
        createFenceGateRecipe(ModBlocks.ANTONI_FENCE_GATE, Ingredient.ofItems(ModItems.ANTONI_ORE))
                .criterion(hasItem(ModItems.RAW_ANTONI_ORE), conditionsFromItem(ModItems.RAW_ANTONI_ORE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ANTONI_WALL, 6)
                .pattern("###")
                .pattern("###")
                .pattern("   ")
                .input('#', ModItems.ANTONI_ORE)
                .criterion(hasItem(ModItems.RAW_ANTONI_ORE), conditionsFromItem(ModItems.RAW_ANTONI_ORE))
                .offerTo(exporter);
        createDoorRecipe(ModBlocks.ANTONI_DOOR, Ingredient.ofItems(ModItems.ANTONI_ORE))
                .criterion(hasItem(ModItems.RAW_ANTONI_ORE), conditionsFromItem(ModItems.RAW_ANTONI_ORE))
                .offerTo(exporter);
        createTrapdoorRecipe(ModBlocks.ANTONI_TRAPDOOR, Ingredient.ofItems(ModItems.ANTONI_ORE))
                .criterion(hasItem(ModItems.RAW_ANTONI_ORE), conditionsFromItem(ModItems.RAW_ANTONI_ORE))
                .offerTo(exporter);

        /* Shapeless Recipes */
        offerReversibleCompactingRecipes(exporter,
                RecipeCategory.BUILDING_BLOCKS, ModItems.ANTONI_ORE,
                RecipeCategory.DECORATIONS, ModBlocks.ANTONI_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModItems.ANTONI_ORE, 9)
                .input(ModBlocks.CHISELED_ANTONI_BLOCK)
                .criterion(hasItem(ModItems.ANTONI_CHISEL), conditionsFromItem(ModItems.ANTONI_CHISEL))
                .offerTo(exporter, Identifier.of(AntoniMod.MOD_ID, "antoni_ore_from_chiseled_antoni_block"));

        /* Shaped Recipes */
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.ANTONI_CHISEL, 1)
                .pattern(" D#")
                .pattern(" ID")
                .pattern("I  ")
                .input('#', ModItems.ANTONI_ORE)
                .input('I', Items.STICK)
                .input('D', Items.DIAMOND)
                .criterion(hasItem(ModItems.RAW_ANTONI_ORE), conditionsFromItem(ModItems.RAW_ANTONI_ORE))
                .offerTo(exporter, Identifier.of(AntoniMod.MOD_ID, "antoni_chisel"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.MAGIC_ANTONI_BLOCK, 1)
                .pattern(" ##")
                .pattern(" ##")
                .pattern("   ")
                .input('#', ModBlocks.CHISELED_ANTONI_BLOCK)
                .criterion(hasItem(ModItems.ANTONI_CHISEL), conditionsFromItem(ModItems.ANTONI_CHISEL))
                .offerTo(exporter, Identifier.of(AntoniMod.MOD_ID, "magic_antoni_block"));
    }
}
