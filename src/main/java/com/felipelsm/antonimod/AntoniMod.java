package com.felipelsm.antonimod;

import com.felipelsm.antonimod.block.ModBlocks;
import com.felipelsm.antonimod.component.ModDataComponentTypes;
import com.felipelsm.antonimod.fluid.ModFluids;
import com.felipelsm.antonimod.item.ModItemGroups;
import com.felipelsm.antonimod.item.ModItems;
import com.felipelsm.antonimod.util.ModHammerUsageEvent;
import com.felipelsm.antonimod.util.ModModelPredicates;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.world.gen.feature.util.CaveSurface;
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
		// Load ModFluids before ModBlocks and ModItems because some elements depend on fluid class
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();

		ModDataComponentTypes.registerDataComponentTypes();
		ModModelPredicates.registerModelPredicates();

		PlayerBlockBreakEvents.BEFORE.register(new ModHammerUsageEvent());

		LOGGER.info("Hello Fabric world!");
	}
}