package com.gildedgames.the_aether.addon.tile_entities;

import com.gildedgames.the_aether.AetherConfig;



import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.tile_entities.AetherTileEntities;

import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @see AetherTileEntities
 */
public class AetherAddonTileEntities {
	
	public static void initialization() {
		if (AetherConfig.addon_options.enable_skyroot_chest)
			GameRegistry.registerTileEntity(TileEntitySkyrootChest.class, Aether.locate("skyroot_chest"));
		if (AetherConfig.addon_options.enable_aetherion_chest)
			GameRegistry.registerTileEntity(TileEntityAetherionChest.class, Aether.locate("aetherion_chest"));
		if (AetherConfig.addon_options.enable_skyroot_sign)
			GameRegistry.registerTileEntity(TileEntitySkyrootSign.class, Aether.locate("skyroot_sign"));
	}
	
}
