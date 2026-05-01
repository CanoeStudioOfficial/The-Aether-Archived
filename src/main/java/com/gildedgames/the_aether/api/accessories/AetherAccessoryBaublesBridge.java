package com.gildedgames.the_aether.api.accessories;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AetherAccessoryBaublesBridge implements ICapabilityProvider, IBauble
{
    private final AccessoryType type;

    public AetherAccessoryBaublesBridge(AccessoryType type)
    {
        this.type = type;
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack)
    {
        return this.type.getBaubleType();
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == BaublesCapabilities.CAPABILITY_ITEM_BAUBLE;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == BaublesCapabilities.CAPABILITY_ITEM_BAUBLE)
        {
            return BaublesCapabilities.CAPABILITY_ITEM_BAUBLE.cast(this);
        }
        return null;
    }
}
