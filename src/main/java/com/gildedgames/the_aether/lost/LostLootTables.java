package com.gildedgames.the_aether.lost;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LostLootTables
{
	public static ResourceLocation king_aerwhale = register("entities/king_aerwhale");

	private static ResourceLocation register(String location)
	{
		return LootTableList.register(LostAetherContent.locate(location));
	}
}