package com.gildedgames.the_aether.player;

import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class AccessoryInventory implements IAccessoryInventory
{
    private NonNullList<ItemStack> slots;
    private EntityPlayer player;

    public AccessoryInventory()
    {
        this.slots = NonNullList.withSize(8, ItemStack.EMPTY);
    }

    @Override
    public void setPlayer(EntityPlayer player)
    {
        this.player = player;
    }

    @Override
    public EntityPlayer getPlayer()
    {
        return this.player;
    }

    public void syncFromBaubles()
    {
        if (this.player == null) return;
        
        IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(this.player);
        for (int i = 0; i < 8 && i < baubles.getSlots(); i++)
        {
            this.slots.set(i, baubles.getStackInSlot(i).copy());
        }
    }

    @Override
    public int getSizeInventory()
    {
        return 8;
    }

    @Override
    public boolean isEmpty()
    {
        for (ItemStack stack : slots)
        {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        if (index < 0 || index >= slots.size()) return ItemStack.EMPTY;
        return slots.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(slots, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(slots, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        if (index < 0 || index >= slots.size()) return;
        slots.set(index, stack);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public void markDirty()
    {
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player)
    {
    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return stack.isEmpty() || stack.getItem() instanceof IBauble;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {
    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < slots.size(); i++)
        {
            slots.set(i, ItemStack.EMPTY);
        }
    }

    @Override
    public String getName()
    {
        return "aether_accessories";
    }

    @Override
    public boolean hasCustomName()
    {
        return false;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentString("Aether Accessories");
    }

    @Override
    public int getSlots()
    {
        return 8;
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack)
    {
        setInventorySlotContents(slot, stack);
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack, EntityPlayer player)
    {
        return isItemValidForSlot(slot, stack);
    }
}
