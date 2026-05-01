package com.gildedgames.the_aether.lost;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gildedgames.the_aether.lost.client.ClientProxy;
import com.gildedgames.the_aether.lost.events.LostAetherRegistryEvent;
import com.gildedgames.the_aether.lost.events.LostEvents;
import com.gildedgames.the_aether.lost.events.PlayerLostAetherEvents;
import com.gildedgames.the_aether.lost.registry.LostAetherEntities;
import com.gildedgames.the_aether.lost.world.AetherStructureGenerator;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class LostAetherContent
{
	public static final Logger LOGGER = LogManager.getLogger("LostAether");
	public static final LostSplashes.Splashes SPLASHES = new LostSplashes.Splashes();

	public static final String MODID = "lost_aether";

	public static void preInit()
	{
		CommonProxy.registerEvent(new LostEvents());
		CommonProxy.registerEvent(new LostAetherRegistryEvent());
		ClientProxy.clientPreInit();
		CommonProxy.commonPreInit();
	}

	public static void init()
	{
		CommonProxy.registerEvent(new PlayerLostAetherEvents());
		LostAetherEntities.initialization();
		GameRegistry.registerWorldGenerator(new AetherStructureGenerator(), 0);
		CommonProxy.commonInit();
		ClientProxy.clientInit();
	}

	public static ResourceLocation locate(String location)
	{
		return new ResourceLocation(MODID, location);
	}

	public static String find()
	{
		return MODID + ":";
	}
}
