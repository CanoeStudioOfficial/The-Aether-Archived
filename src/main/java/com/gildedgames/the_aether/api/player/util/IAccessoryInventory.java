package com.gildedgames.the_aether.api.player.util;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

public interface IAccessoryInventory extends IInventory
{
	EntityPlayer getPlayer();

	void setPlayer(EntityPlayer player);

	boolean isItemValidForSlot(int slot, ItemStack stack, EntityPlayer player);

	void setStackInSlot(int slot, ItemStack stack);

	ItemStack getStackInSlot(int slot);

	int getSlots();

	void dropAccessories();

	void damageWornStack(int damage, ItemStack stack);

	boolean setAccessorySlot(ItemStack stack);

	boolean wearingAccessory(ItemStack stack);

	boolean wearingArmor(ItemStack stack);

	void writeToNBT(NBTTagCompound compound);

	void readFromNBT(NBTTagCompound list);

	void writeData(ByteBuf buf);

	void readData(ByteBuf buf);

	boolean isWearingZaniteSet();

	boolean isWearingGravititeSet();

	boolean isWearingNeptuneSet();

	boolean isWearingPhoenixSet();

	boolean isWearingObsidianSet();

	boolean isWearingValkyrieSet();

	NonNullList<ItemStack> getAccessories();

	int getAccessoryCount(ItemStack stack);
}
