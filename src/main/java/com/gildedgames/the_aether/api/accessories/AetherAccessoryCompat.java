package com.gildedgames.the_aether.api.accessories;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AetherAccessoryCompat
{
    public static List<ItemStack> getEquippedAccessories(EntityPlayer player)
    {
        List<ItemStack> list = new ArrayList<>();
        IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
        for (int i = 0; i < handler.getSlots(); i++)
        {
            ItemStack stack = handler.getStackInSlot(i);
            if (!stack.isEmpty())
            {
                list.add(stack);
            }
        }
        return list;
    }

    public static List<ItemStack> getEquippedAccessories(EntityPlayer player, Predicate<ItemStack> filter)
    {
        List<ItemStack> list = new ArrayList<>();
        IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
        for (int i = 0; i < handler.getSlots(); i++)
        {
            ItemStack stack = handler.getStackInSlot(i);
            if (!stack.isEmpty() && filter.test(stack))
            {
                list.add(stack);
            }
        }
        return list;
    }

    public static List<ItemStack> getEquippedAccessoriesByType(EntityPlayer player, AccessoryType type)
    {
        return getEquippedAccessories(player, stack -> {
            if (stack.getItem() instanceof IBauble)
            {
                return ((IBauble) stack.getItem()).getBaubleType(stack) == type.getBaubleType();
            }
            return false;
        });
    }

    public static List<ItemStack> getEquippedAccessoriesByBaubleType(EntityPlayer player, BaubleType type)
    {
        return getEquippedAccessories(player, stack -> {
            if (stack.getItem() instanceof IBauble)
            {
                return ((IBauble) stack.getItem()).getBaubleType(stack) == type;
            }
            return false;
        });
    }

    public static List<ItemStack> getEquippedAccessoriesByItem(EntityPlayer player, Item item)
    {
        return getEquippedAccessories(player, stack -> stack.getItem() == item);
    }

    public static List<ItemStack> getEquippedAccessoriesByClass(EntityPlayer player, Class<? extends Item> itemClass)
    {
        return getEquippedAccessories(player, stack -> itemClass.isInstance(stack.getItem()));
    }

    public static boolean isAccessoryEquipped(EntityPlayer player, Item item)
    {
        return !BaublesHelper.getWornStack(player, item).isEmpty();
    }

    public static ItemStack getEquippedAccessory(EntityPlayer player, Item item)
    {
        return BaublesHelper.getWornStack(player, item);
    }

    public static int getAccessorySlotCount(EntityPlayer player)
    {
        return BaublesApi.getBaublesHandler(player).getSlots();
    }

    public static ItemStack getStackInAccessorySlot(EntityPlayer player, int slot)
    {
        IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
        if (slot >= 0 && slot < handler.getSlots())
        {
            return handler.getStackInSlot(slot);
        }
        return ItemStack.EMPTY;
    }
}
