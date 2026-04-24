package com.felipelsm.antonimod.component;

import com.felipelsm.antonimod.AntoniMod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes
{
    public static final ComponentType<BlockPos> COORDINATES = register("coordinates",
            builder -> builder.codec(BlockPos.CODEC));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator)
    {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(AntoniMod.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }
    public static void registerDataComponentTypes()
    {
        AntoniMod.LOGGER.info("Registering Mod Data Components for " + AntoniMod.MOD_ID);

        AntoniMod.LOGGER.info("Finished Registering Data Components for " + AntoniMod.MOD_ID);
    }
}
