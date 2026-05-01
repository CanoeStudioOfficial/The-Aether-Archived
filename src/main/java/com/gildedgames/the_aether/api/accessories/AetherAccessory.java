package com.gildedgames.the_aether.api.accessories;

import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.AetherRegistryEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AetherAccessory extends AetherRegistryEntry<AetherAccessory>
{
    private final Item item;
    private final AccessoryType type;

    public AetherAccessory(Item item, AccessoryType type)
    {
        this.item = item;
        this.type = type;
        this.setRegistryName(item.getRegistryName());
    }

    public Item getItem()
    {
        return this.item;
    }

    public AccessoryType getAccessoryType()
    {
        return this.type;
    }

    public boolean isEquippedBy(EntityPlayer player)
    {
        if (player == null) return false;
        
        com.gildedgames.the_aether.api.player.IPlayerAether aetherPlayer = 
            player.getCapability(AetherAPI.AETHER_PLAYER, null);
        
        if (aetherPlayer == null) return false;
        
        com.gildedgames.the_aether.api.player.util.IAccessoryInventory inv = 
            aetherPlayer.getAccessoryInventory();
        
        if (inv == null) return false;
        
        for (int i = 0; i < inv.getSlots(); i++)
        {
            ItemStack stack = inv.getStackInSlot(i);
            if (!stack.isEmpty() && stack.getItem() == this.item)
            {
                return true;
            }
        }
        return false;
    }

    public ItemStack getEquippedStack(EntityPlayer player)
    {
        if (player == null) return ItemStack.EMPTY;
        
        com.gildedgames.the_aether.api.player.IPlayerAether aetherPlayer = 
            player.getCapability(AetherAPI.AETHER_PLAYER, null);
        
        if (aetherPlayer == null) return ItemStack.EMPTY;
        
        com.gildedgames.the_aether.api.player.util.IAccessoryInventory inv = 
            aetherPlayer.getAccessoryInventory();
        
        if (inv == null) return ItemStack.EMPTY;
        
        for (int i = 0; i < inv.getSlots(); i++)
        {
            ItemStack stack = inv.getStackInSlot(i);
            if (!stack.isEmpty() && stack.getItem() == this.item)
            {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}
