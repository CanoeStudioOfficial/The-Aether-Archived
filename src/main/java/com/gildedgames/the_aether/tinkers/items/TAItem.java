package com.gildedgames.the_aether.tinkers.items;

import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import com.gildedgames.the_aether.Aether;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TAItem extends Item {

    private boolean beaconPayment = false;

    public TAItem(String name) {
        super();
        this.setTranslationKey(name);
        this.setRegistryName(Aether.modid, name);
        this.setCreativeTab(AetherCreativeTabs.aether);
    }

    @Override
    public boolean isBeaconPayment(ItemStack stack) {
        return beaconPayment;
    }

    public TAItem setBeaconPayment() {
        this.beaconPayment = true;
        return this;
    }
}
