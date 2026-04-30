package com.gildedgames.the_aether.player;

import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.items.ItemHandlerHelper;

public class AccessoryInventory implements IAccessoryInventory
{
    private NonNullList<ItemStack> slots;
    private EntityPlayer player;
    private boolean blockEvents = false;

    public AccessoryInventory()
    {
        this.slots = NonNullList.withSize(8, ItemStack.EMPTY);
    }

    @Override
    public EntityPlayer getPlayer()
    {
        return player;
    }

    @Override
    public void setPlayer(EntityPlayer player)
    {
        this.player = player;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack, EntityPlayer player)
    {
        if (stack.isEmpty()) return false;
        if (!(stack.getItem() instanceof IBauble)) return false;
        
        IBauble bauble = (IBauble) stack.getItem();
        return bauble.getBaubleType(stack).hasSlot(slot);
    }

    @Override
    public int getSlots()
    {
        return 8;
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
    public ItemStack getStackInSlot(int slot)
    {
        if (slot < 0 || slot >= slots.size()) return ItemStack.EMPTY;
        return slots.get(slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        ItemStack stack = ItemStackHelper.getAndSplit(slots, slot, amount);
        if (!stack.isEmpty())
        {
            this.markDirty();
        }
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int slot)
    {
        return ItemStackHelper.getAndRemove(slots, slot);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        this.setStackInSlot(slot, stack);
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack)
    {
        if (slot < 0 || slot >= slots.size()) return;
        
        ItemStack oldStack = slots.get(slot);
        if (!oldStack.isEmpty() && oldStack.getItem() instanceof IBauble)
        {
            ((IBauble) oldStack.getItem()).onUnequipped(oldStack, player);
        }
        
        slots.set(slot, stack);
        
        if (!stack.isEmpty() && stack.getItem() instanceof IBauble)
        {
            ((IBauble) stack.getItem()).onEquipped(stack, player);
        }
        
        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public void markDirty()
    {
        syncToBaubles();
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return isItemValidForSlot(slot, stack, player);
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value) {}

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
        this.markDirty();
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

    public void syncFromBaubles()
    {
        if (player == null) return;
        if (blockEvents) return;
        
        blockEvents = true;
        
        try
        {
            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
            
            for (int i = 0; i < 8 && i < baubles.getSlots(); i++)
            {
                ItemStack baubleStack = baubles.getStackInSlot(i);
                ItemStack localStack = slots.get(i);
                
                if (!ItemStack.areItemStacksEqual(baubleStack, localStack))
                {
                    slots.set(i, baubleStack.copy());
                }
            }
        }
        finally
        {
            blockEvents = false;
        }
    }

    public void syncToBaubles()
    {
        if (player == null) return;
        if (blockEvents) return;
        
        blockEvents = true;
        
        try
        {
            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
            
            for (int i = 0; i < 8 && i < baubles.getSlots(); i++)
            {
                ItemStack localStack = slots.get(i);
                ItemStack baubleStack = baubles.getStackInSlot(i);
                
                if (!ItemStack.areItemStacksEqual(localStack, baubleStack))
                {
                    baubles.setStackInSlot(i, localStack.isEmpty() ? ItemStack.EMPTY : localStack.copy());
                }
            }
        }
        finally
        {
            blockEvents = false;
        }
    }

    public void readNBT(NBTTagCompound nbt)
    {
        ItemStackHelper.loadAllItems(nbt, slots);
    }

    public NBTTagCompound writeNBT(NBTTagCompound nbt)
    {
        return ItemStackHelper.saveAllItems(nbt, slots);
    }
}
