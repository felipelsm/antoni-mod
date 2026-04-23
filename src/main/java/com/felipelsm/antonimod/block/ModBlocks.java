package com.felipelsm.antonimod.block;

import com.felipelsm.antonimod.AntoniMod;
import com.felipelsm.antonimod.block.custom.MagicAntoniBlock;
import com.felipelsm.antonimod.fluid.ModFluids;
import com.felipelsm.antonimod.item.ModItemGroups;
import com.felipelsm.antonimod.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

/**
 * Custom ModBlocks need to be addressed in
 *
 * @1- resources.assets.modid.blockstates
 * @2- resources.assets.modid.lang
 * @3- resources.assets.modid.models.block
 * @4- resources.assets.modid.models.item
 * @5- resources.assets.modid.textures.block
 * @6- resources.data.modid.loot_table.blocks
 * @7- resources.data.modid.recipe
 * @8- resources.data.minecraft.tags.block
 * @9- java..modid.item.ModItemGroups
 * @10- java..modid.block.ModBlocks.registerModBlocks
 */
public class ModBlocks
{
    public static final Block ANTONI_ORE_BLOCK = registerBlock(
            "antoni_ore_block",
            new ExperienceDroppingBlock(UniformIntProvider.create(1,3),
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DIRT_BROWN)
                            .strength(2.0F, 2.0F)
                            .sounds(BlockSoundGroup.MUD)
                            .requiresTool()
            ));
    public static final Block ANTONI_DEEPSLATE_ORE_BLOCK = registerBlock(
            "antoni_deepslate_ore_block",
            new ExperienceDroppingBlock(UniformIntProvider.create(2,5),
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DIRT_BROWN)
                            .strength(3.0F, 2.0F)
                            .sounds(BlockSoundGroup.MUD)
                            .requiresTool()
            ));
    public static final Block ANTONI_BLOCK = registerBlock(
            "antoni_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DIRT_BROWN)
                            .strength(2.0F, 2.0F)
                            .sounds(BlockSoundGroup.MUD)
                            .requiresTool()
            ));
    public static final Block CHISELED_ANTONI_BLOCK = registerBlock(
            "chiseled_antoni_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DIRT_BROWN)
                            .strength(2.0F, 2.0F)
                            .sounds(BlockSoundGroup.MUD)
                            .requiresTool()
            ));
    public static final Block MAGIC_ANTONI_BLOCK = registerBlock(
            "magic_antoni_block",
            new MagicAntoniBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DIRT_BROWN)
                            .strength(1.0F, 1.0F)
                            .sounds(BlockSoundGroup.ANVIL)
            ));
    public static final Block ANTONI_FUEL = registerBlock(
            "antoni_fuel",
            new FluidBlock(
                    ModFluids.ANTONI_FUEL,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.WATER_BLUE)
                            .replaceable()
                            .noCollision()
                            .strength(100.0F)
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .dropsNothing()
                            .liquid()
                            .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)
            )
    );


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

//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
//            entries.add(ANTONI_BLOCK);
//            entries.add(CHISELED_ANTONI_BLOCK);
//            entries.add(ANTONI_ORE_BLOCK);
//            entries.add(ANTONI_DEEPSLATE_ORE_BLOCK);
//        });

        AntoniMod.LOGGER.info("Finished Registering Mod Blocks for " + AntoniMod.MOD_ID);
    }
}
