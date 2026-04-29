package com.gildedgames.the_aether.registry.creative_tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.gildedgames.the_aether.blocks.BlocksAether;

public class AetherCreativeTabs 
{

	public static AetherTab aether = new AetherTab("aether");

	public static void initialization()
	{
		aether.setIcon(new ItemStack(BlocksAether.aether_grass));
	}
	
	public static class AetherTab extends CreativeTabs
	{

		private ItemStack stack;

		public AetherTab(String unlocalizedName)
		{
			super(unlocalizedName);
		}

		public AetherTab(String unlocalizedName, ItemStack stack)
		{
			super(unlocalizedName);
			this.stack = stack;
		}

		public void setIcon(ItemStack stack)
		{
			this.stack = stack;
		}

	    @SideOnly(Side.CLIENT)
	    public String getTranslationKey()
	    {
	        return "tab." + this.getTabLabel();
	    }

		@Override
		public ItemStack createIcon()
		{
			return stack;
		}
		
	}

}