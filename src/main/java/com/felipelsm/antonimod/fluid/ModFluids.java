package com.felipelsm.antonimod.fluid;

import com.felipelsm.antonimod.AntoniMod;
import com.felipelsm.antonimod.fluid.custom.AntoniFuelFluid;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static net.minecraft.item.Items.BUCKET;

public class ModFluids
{

    public static final FlowableFluid ANTONI_FUEL = registerFluid("antoni_fuel", new AntoniFuelFluid.Still());
    public static final FlowableFluid FLOWING_ANTONI_FUEL = registerFluid("flowing_antoni_fuel", new AntoniFuelFluid.Flowing());

    private static FlowableFluid registerFluid(String name, FlowableFluid fuel)
    {
        return Registry.register(Registries.FLUID, Identifier.of(AntoniMod.MOD_ID, name), fuel);
    }

    public static void registerModFluids()
    {
        AntoniMod.LOGGER.info("Registering Mod Fluids for " + AntoniMod.MOD_ID);

        AntoniMod.LOGGER.info("Finished Registering Mod Fluids for " + AntoniMod.MOD_ID);
    }
}
