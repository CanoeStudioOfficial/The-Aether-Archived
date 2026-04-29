package com.gildedgames.the_aether.api.accessories;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BaublesHelper
{

	public static IBaublesItemHandler getBaublesHandler(EntityPlayer player)
	{
		return BaublesApi.getBaublesHandler(player);
	}

	public static boolean wearingAccessory(EntityPlayer player, ItemStack stack)
	{
		IBaublesItemHandler handler = getBaublesHandler(player);

		for (int i = 0; i < handler.getSlots(); i++)
		{
			ItemStack slotStack = handler.getStackInSlot(i);

			if (!slotStack.isEmpty() && slotStack.getItem() == stack.getItem())
			{
				return true;
			}
		}

		return false;
	}

	public static boolean wearingAccessory(EntityPlayer player, Item item)
	{
		return wearingAccessory(player, new ItemStack(item));
	}

	public static ItemStack getWornStack(EntityPlayer player, Item item)
	{
		IBaublesItemHandler handler = getBaublesHandler(player);

		for (int i = 0; i < handler.getSlots(); i++)
		{
			ItemStack slotStack = handler.getStackInSlot(i);

			if (!slotStack.isEmpty() && slotStack.getItem() == item)
			{
				return slotStack;
			}
		}

		return ItemStack.EMPTY;
	}

	public static ItemStack getWornStackByType(EntityPlayer player, AccessoryType type)
	{
		IBaublesItemHandler handler = getBaublesHandler(player);

		for (int i = 0; i < handler.getSlots(); i++)
		{
			ItemStack slotStack = handler.getStackInSlot(i);

			if (!slotStack.isEmpty() && slotStack.getItem() instanceof ItemAccessory)
			{
				ItemAccessory accessory = (ItemAccessory) slotStack.getItem();

				if (accessory.getType() == type)
				{
					return slotStack;
				}
			}
		}

		return ItemStack.EMPTY;
	}

	public static void damageWornStack(EntityPlayer player, int damage, ItemStack stack)
	{
		ItemStack wornStack = getWornStack(player, stack.getItem());

		if (!wornStack.isEmpty() && !player.capabilities.isCreativeMode)
		{
			wornStack.damageItem(damage, player);

			if (wornStack.getItemDamage() >= wornStack.getMaxDamage())
			{
				breakItem(player, wornStack.getItem());
			}
		}
	}

	public static void damageWornStack(EntityPlayer player, int damage, Item item)
	{
		damageWornStack(player, damage, new ItemStack(item));
	}

	public static int breakItem(EntityPlayer player, Item item)
	{
		IBaublesItemHandler handler = getBaublesHandler(player);
		int count = 0;

		for (int i = 0; i < handler.getSlots(); i++)
		{
			ItemStack slotStack = handler.getStackInSlot(i);

			if (!slotStack.isEmpty() && slotStack.getItem() == item)
			{
				if (slotStack.getTagCompound() != null && slotStack.getTagCompound().getBoolean("Unbreakable"))
				{
					return count;
				}

				count += slotStack.getCount();
				handler.setStackInSlot(i, ItemStack.EMPTY);
			}
		}

		return count;
	}

	public static boolean wearingArmor(EntityPlayer player, ItemStack stack)
	{
		for (int i = 0; i < 4; i++)
		{
			if (player.inventory.armorInventory.get(i).getItem() == stack.getItem())
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isWearingZaniteSet(EntityPlayer player)
	{
		return wearingArmor(player, new ItemStack(ItemsAether.zanite_helmet)) && wearingArmor(player, new ItemStack(ItemsAether.zanite_chestplate)) && wearingArmor(player, new ItemStack(ItemsAether.zanite_leggings)) && wearingArmor(player, new ItemStack(ItemsAether.zanite_boots)) && wearingAccessory(player, ItemsAether.zanite_gloves);
	}

	public static boolean isWearingGravititeSet(EntityPlayer player)
	{
		return wearingArmor(player, new ItemStack(ItemsAether.gravitite_helmet)) && wearingArmor(player, new ItemStack(ItemsAether.gravitite_chestplate)) && wearingArmor(player, new ItemStack(ItemsAether.gravitite_leggings)) && wearingArmor(player, new ItemStack(ItemsAether.gravitite_boots)) && wearingAccessory(player, ItemsAether.gravitite_gloves);
	}

	public static boolean isWearingNeptuneSet(EntityPlayer player)
	{
		return wearingArmor(player, new ItemStack(ItemsAether.neptune_helmet)) && wearingArmor(player, new ItemStack(ItemsAether.neptune_chestplate)) && wearingArmor(player, new ItemStack(ItemsAether.neptune_leggings)) && wearingArmor(player, new ItemStack(ItemsAether.neptune_boots)) && wearingAccessory(player, ItemsAether.neptune_gloves);
	}

	public static boolean isWearingPhoenixSet(EntityPlayer player)
	{
		return wearingArmor(player, new ItemStack(ItemsAether.phoenix_helmet)) && wearingArmor(player, new ItemStack(ItemsAether.phoenix_chestplate)) && wearingArmor(player, new ItemStack(ItemsAether.phoenix_leggings)) && wearingArmor(player, new ItemStack(ItemsAether.phoenix_boots)) && wearingAccessory(player, ItemsAether.phoenix_gloves);
	}

	public static boolean isWearingValkyrieSet(EntityPlayer player)
	{
		return wearingArmor(player, new ItemStack(ItemsAether.valkyrie_helmet)) && wearingArmor(player, new ItemStack(ItemsAether.valkyrie_chestplate)) && wearingArmor(player, new ItemStack(ItemsAether.valkyrie_leggings)) && wearingArmor(player, new ItemStack(ItemsAether.valkyrie_boots)) && wearingAccessory(player, ItemsAether.valkyrie_gloves);
	}

	public static boolean isWearingObsidianSet(EntityPlayer player)
	{
		return wearingArmor(player, new ItemStack(ItemsAether.obsidian_helmet)) && wearingArmor(player, new ItemStack(ItemsAether.obsidian_chestplate)) && wearingArmor(player, new ItemStack(ItemsAether.obsidian_leggings)) && wearingArmor(player, new ItemStack(ItemsAether.obsidian_boots)) && wearingAccessory(player, ItemsAether.obsidian_gloves);
	}
}
