package com.felipelsm.antonimod;

import com.felipelsm.antonimod.block.ModBlocks;
import com.felipelsm.antonimod.fluid.ModFluids;
import com.felipelsm.antonimod.item.ModItemGroups;
import com.felipelsm.antonimod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AntoniMod implements ModInitializer
{
	public static final String MOD_ID = "antonimod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.

		ModFluids.registerModFluids();
		// Load ModFluids before ModBlocks and ModItems because they depend on the fluid class
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();

		LOGGER.info("Hello Fabric world!");
	}
}