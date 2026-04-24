package com.felipelsm.antonimod.util;

import com.felipelsm.antonimod.AntoniMod;
import com.felipelsm.antonimod.component.ModDataComponentTypes;
import com.felipelsm.antonimod.item.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ModModelPredicates
{
    public static void registerModelPredicates()
    {
        AntoniMod.LOGGER.info("Registering Mod Model Predicates for " + AntoniMod.MOD_ID);

        ModelPredicateProviderRegistry.register(ModItems.ANTONI_CHISEL, Identifier.of(AntoniMod.MOD_ID, "used"),
                (stack, world, entity, seed) ->
                    stack.get(ModDataComponentTypes.COORDINATES) != null ? 1.0F : 0.0F
                );

        AntoniMod.LOGGER.info("Finished Registering Mod Model Predicates for " + AntoniMod.MOD_ID);
    }
}
