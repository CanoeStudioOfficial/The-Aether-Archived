package com.gildedgames.the_aether.tinkers.fluids;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.smeltery.block.BlockMolten;

public class FluidHelper {
    public static FluidMolten createFluid(Material material, int temperature) {
        FluidMolten fluid = new FluidMolten(material.identifier, material.materialTextColor);
        fluid.setTemperature(temperature);
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
        BlockMolten blockFluid = new BlockMolten(fluid);
        blockFluid.setTranslationKey("molten_" + fluid.getName());
        blockFluid.setRegistryName("molten_" + fluid.getName());
        ForgeRegistries.BLOCKS.register(blockFluid);
        com.gildedgames.the_aether.tinkers.TinkersClientHelper.registerFluidModels(fluid);
        return fluid;
    }
}
