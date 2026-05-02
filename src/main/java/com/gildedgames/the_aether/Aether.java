package com.gildedgames.the_aether;

import com.gildedgames.aether_legacy.Tags;
import com.gildedgames.the_aether.addon.blocks.BlocksAetherAddon;
import com.gildedgames.the_aether.addon.tile_entities.AetherAddonTileEntities;
import com.gildedgames.the_aether.advancements.AetherAdvancements;
import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.IPlayerAetherStorage;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.AetherEntities;
import com.gildedgames.the_aether.events.AetherEntityEvents;
import com.gildedgames.the_aether.lost.CommonProxy;
import com.gildedgames.the_aether.lost.LostSplashes;
import com.gildedgames.the_aether.lost.client.ClientProxy;
import com.gildedgames.the_aether.lost.events.LostEvents;
import com.gildedgames.the_aether.lost.events.PlayerLostAetherEvents;
import com.gildedgames.the_aether.lost.registry.LostAetherEntities;
import com.gildedgames.the_aether.lost.world.AetherStructureGenerator;
import com.gildedgames.the_aether.networking.AetherNetworkingManager;
import com.gildedgames.the_aether.player.capability.PlayerAetherManager;
import com.gildedgames.the_aether.registry.AetherRegistryEvent;
import com.gildedgames.the_aether.registry.sounds.SoundsAether;
import com.gildedgames.the_aether.tile_entities.AetherTileEntities;
import com.gildedgames.the_aether.universal.crafttweaker.AetherCraftTweakerPlugin;
import com.gildedgames.the_aether.universal.reskillable.ReskillableTickHandler;
import com.gildedgames.the_aether.world.AetherWorld;
import com.gildedgames.the_aether.world.biome.BiomeStorage;
import com.gildedgames.the_aether.world.storage.loot.conditions.LootConditionsAether;
import com.gildedgames.the_aether.world.storage.loot.functions.LootFunctionsAether;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(name = Tags.MOD_NAME, modid = Tags.MOD_ID, version = Tags.VERSION, acceptedMinecraftVersions = "1.12.2", dependencies = "required-after:baubles;after:mantle;after:tconstruct", updateJSON = "https://raw.githubusercontent.com/Modding-Legacy/Aether-Legacy/master/aether-legacy-changelog.json")
public class Aether 
{

	public static final String modid = Tags.MOD_ID;

	public static final String LOST_MODID = "lost_aether";

	public static final LostSplashes.Splashes LOST_SPLASHES = new LostSplashes.Splashes();

	@Instance(Aether.modid)
	public static Aether instance;

	@SidedProxy(modId = Aether.modid, clientSide = "com.gildedgames.the_aether.client.ClientProxy", serverSide = "com.gildedgames.the_aether.CommonProxy")
	public static CommonProxy proxy;

	public Aether() {
		if (Loader.isModLoaded("tconstruct")) {
			com.gildedgames.the_aether.tinkers.TinkersIntegration.construct();
		}
	}

	@EventHandler
	public void preInitialization(FMLPreInitializationEvent event)
	{
		CapabilityManager.INSTANCE.register(IPlayerAether.class, new IPlayerAetherStorage(), () -> null);

		BlocksAether.initialization();
		BlocksAetherAddon.initialization();
		BlocksAether.initializeHarvestLevels();
		SoundsAether.initialization();
		LootConditionsAether.initialization();
		LootFunctionsAether.initialization();
		AetherAdvancements.initialization();
		AetherNetworkingManager.preInitialization();

		if(Loader.isModLoaded("crafttweaker"))
		{
			AetherCraftTweakerPlugin.preInitialization();
		}

		if(Loader.isModLoaded("tconstruct"))
		{
			com.gildedgames.the_aether.tinkers.TinkersIntegration.preInit();
		}

		CommonProxy.registerEvent(new AetherRegistryEvent());
		CommonProxy.registerEvent(new com.gildedgames.the_aether.addon.registry.AetherAddonRegistryEvent());

		CommonProxy.registerEvent(new LostEvents());
		LostAetherEntities.initialization();
		ClientProxy.clientPreInit();
		CommonProxy.commonPreInit();

		proxy.preInitialization();
	}

	@EventHandler
	public void initialization(FMLInitializationEvent event)
	{
		PlayerAetherManager.initialization();
		AetherEntities.initialization();
		AetherTileEntities.initialization();
		AetherAddonTileEntities.initialization();
		BiomeStorage.handleBiomeConfig();
		AetherWorld.initialization();

		CommonProxy.registerEvent(new AetherEventHandler());
		CommonProxy.registerEvent(new AetherEntityEvents());

		if(Loader.isModLoaded("reskillable"))
		{
			CommonProxy.registerEvent(new ReskillableTickHandler());
		}

		if(Loader.isModLoaded("tconstruct"))
		{
			com.gildedgames.the_aether.tinkers.TinkersIntegration.init();
		}

		CommonProxy.registerEvent(new PlayerLostAetherEvents());
		GameRegistry.registerWorldGenerator(new AetherStructureGenerator(), 0);
		CommonProxy.commonInit();
		ClientProxy.clientInit();

		proxy.initialization();
	}

	@EventHandler
	public void postInitialization(FMLPostInitializationEvent event)
	{
		if(Loader.isModLoaded("tconstruct"))
		{
			com.gildedgames.the_aether.tinkers.TinkersIntegration.postInit();
		}

		proxy.postInitialization();

		FurnaceRecipes.instance().addSmeltingRecipeForBlock(BlocksAether.aether_log, new ItemStack(Items.COAL, 1, 1), 0.15F);
	}

	public static ResourceLocation locate(String location)
	{
		return new ResourceLocation(modid, location);
	}

	public static ResourceLocation locateLost(String location)
	{
		return new ResourceLocation(LOST_MODID, location);
	}

	public static String modAddress()
	{
		return modid + ":";
	}

	public static String lostModAddress()
	{
		return LOST_MODID + ":";
	}

	public static String doubleDropNotifier()
	{
		return modid + "_double_drops";
	}

}
