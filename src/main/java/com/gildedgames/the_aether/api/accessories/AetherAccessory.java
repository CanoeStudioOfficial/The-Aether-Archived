package com.gildedgames.the_aether.api.accessories;

import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.AetherRegistryEntry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AetherAccessory extends AetherRegistryEntry<AetherAccessory>
{
    private ItemStack accessoryStack;
    private final AccessoryType type;

    public AetherAccessory(Block item, AccessoryType type)
    {
        this(new ItemStack(item), type);
    }

    public AetherAccessory(Item item, AccessoryType type)
    {
        this(new ItemStack(item), type);
    }

    public AetherAccessory(ItemStack stack, AccessoryType type)
    {
        this.type = type;
        this.accessoryStack = stack;
        this.setRegistryName(stack.getItem().getRegistryName().toString() + "_meta_" + (stack.isItemStackDamageable() ? 0 : stack.getMetadata()));
    }

    public AccessoryType getAccessoryType()
    {
        return this.type;
    }

    public ItemStack getAccessoryStack()
    {
        return this.accessoryStack;
    }

    public Item getItem()
    {
        return this.accessoryStack.getItem();
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
            if (!stack.isEmpty() && stack.getItem() == this.accessoryStack.getItem())
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
            if (!stack.isEmpty() && stack.getItem() == this.accessoryStack.getItem())
            {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}
