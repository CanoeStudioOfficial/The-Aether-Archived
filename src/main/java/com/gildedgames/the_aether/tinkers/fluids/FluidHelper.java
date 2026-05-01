package com.gildedgames.the_aether.tinkers.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.smeltery.block.BlockMolten;

public class FluidHelper {
    public static FluidMolten createFluid(Material material, int temperature) {
        String fluidName = "molten_" + material.identifier;
        if (FluidRegistry.isFluidRegistered(fluidName)) {
            Fluid existing = FluidRegistry.getFluid(fluidName);
            if (existing instanceof FluidMolten) {
                return (FluidMolten) existing;
            }
        }

        FluidMolten fluid = new FluidMolten(material.identifier, material.materialTextColor);
        fluid.setTemperature(temperature);
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
        return fluid;
    }

    public static void registerFluidBlocks() {
        for (Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
            if (fluid instanceof FluidMolten && fluid.getName().startsWith("molten_")) {
                if (fluid.getBlock() == null) {
                    BlockMolten blockFluid = new BlockMolten(fluid);
                    blockFluid.setTranslationKey(fluid.getName());
                    blockFluid.setRegistryName(fluid.getName());
                    ForgeRegistries.BLOCKS.register(blockFluid);
                    fluid.setBlock(blockFluid);
                }
            }
        }
    }
}
