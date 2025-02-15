package com.gildedgames.the_aether.entities.util;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraftforge.registries.IForgeRegistry;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.api.moa.AetherMoaType;
import com.gildedgames.the_aether.api.moa.MoaProperties;

public class AetherMoaTypes
{

	public static IForgeRegistry<AetherMoaType> moaRegistry;

	public static AetherMoaType blue, orange, white, black;

	public static void initialization()
	{
		blue = register("blue", 0x7777FF, new MoaProperties(AetherConfig.moastats.blue_moa_jumps, AetherConfig.moastats.blue_moa_speed));
		orange = register("orange", -0xC3D78, new MoaProperties(AetherConfig.moastats.orange_moa_jumps, AetherConfig.moastats.orange_moa_speed));
		white = register("white", 0xFFFFFF, new MoaProperties(AetherConfig.moastats.white_moa_jumps, AetherConfig.moastats.white_moa_speed));
		black = register("black", 0x222222, new MoaProperties(AetherConfig.moastats.black_moa_jumps, AetherConfig.moastats.black_moa_speed));
	}

	public static AetherMoaType register(String name, int hexColor, MoaProperties properties)
	{
		AetherMoaType moaType = new AetherMoaType(hexColor, properties, AetherCreativeTabs.misc);

		moaRegistry.register(moaType.setRegistryName(Aether.locate(name)));

		return moaType;
	}

}