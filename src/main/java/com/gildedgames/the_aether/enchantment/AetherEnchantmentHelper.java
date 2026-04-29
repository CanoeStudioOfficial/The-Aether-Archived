package com.gildedgames.the_aether.enchantment;

import java.util.List;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Lists;

public class AetherEnchantmentHelper
{

	public static ItemStack getEnchantedAccessory(Enchantment enchantment, EntityPlayer player)
	{
		if (player == null)
		{
			return ItemStack.EMPTY;
		}

		List<ItemStack> list1 = Lists.<ItemStack> newArrayList();

		IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);

		for (int i = 0; i < handler.getSlots(); ++i)
		{
			ItemStack accessory = handler.getStackInSlot(i);

			if (EnchantmentHelper.getEnchantmentLevel(enchantment, accessory) > 0)
			{
				list1.add(accessory);
			}
		}

		return list1.isEmpty() ? ItemStack.EMPTY : (ItemStack) list1.get(player.getRNG().nextInt(list1.size()));
	}

}
