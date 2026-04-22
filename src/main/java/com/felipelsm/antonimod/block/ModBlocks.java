package com.felipelsm.antonimod.block;

import com.felipelsm.antonimod.AntoniMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks
{
    public static final Block ANTONI_BLOCK = registerBlock(
            "antoni_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DIRT_BROWN)
                            .strength(5.0F, 6.0F)
                            .sounds(BlockSoundGroup.MUD)
                            .instrument(NoteBlockInstrument.BASS)
                            .requiresTool()
            ));
    public static final Block ANTONI_ORE_BLOCK = registerBlock(
            "antoni_ore_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DIRT_BROWN)
                            .strength(3.0F, 3.0F)
                            .sounds(BlockSoundGroup.MUD)
                            .instrument(NoteBlockInstrument.BASS)
                            .requiresTool()
            ));

    private static Block registerBlock(String name, Block block)
    {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(AntoniMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block)
    {
        Registry.register(Registries.ITEM, Identifier.of(AntoniMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks()
    {
        AntoniMod.LOGGER.info("Registering Mod Blocks for " + AntoniMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ANTONI_BLOCK);
            entries.add(ANTONI_ORE_BLOCK);
        });

        AntoniMod.LOGGER.info("Finished Registering Mod Blocks for " + AntoniMod.MOD_ID);
    }
}
