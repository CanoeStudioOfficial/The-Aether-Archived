package com.gildedgames.the_aether.tinkers.blocks;

import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import com.gildedgames.the_aether.tinkers.TinkersIntegration;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class TABlock extends Block {

    private boolean beaconBase = false;

    public TABlock(String name, Material material) {
        super(material);
        this.setTranslationKey(name);
        this.setRegistryName(TinkersIntegration.MODID, name);
        this.setCreativeTab(AetherCreativeTabs.aether);
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon) {
        return beaconBase;
    }

    public TABlock setBeaconBase() {
        this.beaconBase = true;
        return this;
    }
}
