package com.gildedgames.the_aether.lost;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommonProxy
{
	public static void commonPreInit() { }

	public static void commonInit() { }

	@SuppressWarnings("deprecation")
	public static void registerEvent(Object event)
	{
		FMLCommonHandler.instance().bus().register(event);
		MinecraftForge.EVENT_BUS.register(event);
	}
}
