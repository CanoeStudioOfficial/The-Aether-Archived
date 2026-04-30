package com.gildedgames.the_aether.tinkers.compat;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GauntletBaublesCompat
{
    private static final String MORETCON_MODID = "moretcon";
    private static final String GAUNTLET_CLASS_NAME = "com.existingeevee.moretcon.item.tooltypes.Gauntlet";

    private static boolean registered = false;
    private static Class<? extends Item> gauntletClass = null;

    public static void register()
    {
        if (registered) return;
        if (!Loader.isModLoaded(MORETCON_MODID)) return;

        try
        {
            Class<?> raw = Class.forName(GAUNTLET_CLASS_NAME);
            if (Item.class.isAssignableFrom(raw))
            {
                gauntletClass = raw.asSubclass(Item.class);
            }
        }
        catch (ClassNotFoundException e)
        {
            return;
        }

        if (gauntletClass != null)
        {
            MinecraftForge.EVENT_BUS.register(new GauntletBaublesCompat());
            registered = true;
        }
    }

    private GauntletBaublesCompat() {}

    @SubscribeEvent
    public void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event)
    {
        ItemStack stack = event.getObject();
        if (stack.isEmpty()) return;
        if (gauntletClass == null) return;

        if (gauntletClass.isInstance(stack.getItem()))
        {
            event.addCapability(new ResourceLocation("aether_legacy", "gauntlet_bauble"),
                new GauntletBaubleProvider());
        }
    }

    public static class GauntletBaubleProvider implements ICapabilityProvider, IBauble
    {
        @Override
        public BaubleType getBaubleType(ItemStack itemstack)
        {
            return BaubleType.TRINKET;
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
}
