package com.felipelsm.antonimod.datagen;

import com.felipelsm.antonimod.item.ModItems;
import com.felipelsm.antonimod.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup)
    {
        getOrCreateTagBuilder(ModTags.Items.TRANSFORMABLE_ANTONI_ITEM)
                .add(ModItems.ANTONI_ORE);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.ANTONI_SWORD);
        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.ANTONI_SHOVEL);
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.ANTONI_PICKAXE)
                .add(ModItems.ANTONI_HAMMER)
                .add(ModItems.ANTONI_SUPER_HAMMER);
        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.ANTONI_AXE);
        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItems.ANTONI_HOE);
    }
}
