package com.felipelsm.antonimod;

import com.felipelsm.antonimod.fluid.ModFluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class AntoniModClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        // Register Antoni Fuel Rendering
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.ANTONI_FUEL, ModFluids.FLOWING_ANTONI_FUEL,
                new SimpleFluidRenderHandler(
                        Identifier.of(AntoniMod.MOD_ID, "block/antoni_fuel_still"),
                        Identifier.of(AntoniMod.MOD_ID, "block/antoni_fuel_flow"),
                        0xA1E038D0
                ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.ANTONI_FUEL, ModFluids.FLOWING_ANTONI_FUEL);
    }
}
