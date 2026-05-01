package com.gildedgames.the_aether.api.player.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IAccessoryInventory extends IInventory
{
	EntityPlayer getPlayer();

	void setPlayer(EntityPlayer player);

	boolean isItemValidForSlot(int slot, ItemStack stack, EntityPlayer player);

	void setStackInSlot(int slot, ItemStack stack);

	ItemStack getStackInSlot(int slot);

	int getSlots();
}
