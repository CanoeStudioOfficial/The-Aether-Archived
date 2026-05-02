package com.gildedgames.the_aether.registry;

import com.gildedgames.the_aether.addon.blocks.BlocksAetherAddon;
import com.gildedgames.the_aether.addon.dictionary.AetherAddonDictionary;
import com.gildedgames.the_aether.addon.items.ItemsAetherAddon;
import com.gildedgames.the_aether.addon.registry.AetherAddonRegistries;
import com.gildedgames.the_aether.dictionary.AetherDictionary;
import com.gildedgames.the_aether.lost.blocks.BlocksLostAether;
import com.gildedgames.the_aether.lost.client.sounds.LostSounds;
import com.gildedgames.the_aether.lost.items.ItemsLostAether;
import com.gildedgames.the_aether.lost.registry.LostAetherRegistries;
import com.gildedgames.the_aether.lost.registry.LostMoaTypes;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import com.gildedgames.the_aether.registry.sounds.SoundsAether;
import com.gildedgames.the_aether.api.accessories.AetherAccessory;
import com.gildedgames.the_aether.api.accessories.RecipeAccessoryDyes;
import com.gildedgames.the_aether.entities.effects.PotionsAether;
import com.gildedgames.the_aether.world.biome.BiomesAether;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.api.enchantments.AetherEnchantment;
import com.gildedgames.the_aether.api.enchantments.AetherEnchantmentFuel;
import com.gildedgames.the_aether.api.freezables.AetherFreezable;
import com.gildedgames.the_aether.api.freezables.AetherFreezableFuel;
import com.gildedgames.the_aether.api.moa.AetherMoaType;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.util.AetherMoaTypes;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.world.AetherWorld;

public class AetherRegistryEvent 
{

	@SubscribeEvent
	public void onRegisterBlockEvent(RegistryEvent.Register<Block> event)
	{
		BlocksAether.registerBlocks(event.getRegistry());
		BlocksAetherAddon.registerBlocks(event.getRegistry());
		BlocksLostAether.setBlockRegistry(event.getRegistry());
	}

	@SubscribeEvent
	public void onRegisterItemEvent(RegistryEvent.Register<Item> event)
	{
		BlocksAether.registerItems(event.getRegistry());
		BlocksAetherAddon.registerItems(event.getRegistry());

		ItemsAether.itemRegistry = event.getRegistry();

		ItemsAether.initialization();
		ItemsAetherAddon.itemRegistry = event.getRegistry();
		ItemsAetherAddon.initialization();
		AetherCreativeTabs.initialization();

		BlocksLostAether.setItemRegistry(event.getRegistry());
		ItemsLostAether.itemRegistry = event.getRegistry();
		BlocksLostAether.init();
		ItemsLostAether.initialization();
	}

	@SubscribeEvent
	public void onRegisterBiomeEvent(RegistryEvent.Register<Biome> event)
	{
		BiomesAether.biomeRegistry = event.getRegistry();

		BiomesAether.initialization();
	}

	@SubscribeEvent
	public void onRegisterSoundEvent(RegistryEvent.Register<SoundEvent> event)
	{
		SoundsAether.soundRegistry = event.getRegistry();

		SoundsAether.initialization();

		LostSounds.soundRegistry = event.getRegistry();
		LostSounds.initialization();
	}

	@SubscribeEvent
	public void onRegisterPotionEffect(RegistryEvent.Register<Potion> event)
	{
		PotionsAether.potionRegistry = event.getRegistry();

		PotionsAether.initialization();
	}

	@SubscribeEvent
	public void onRegisterCraftingEvent(RegistryEvent.Register<IRecipe> event)
	{
		AetherDictionary.initialization();
		AetherAddonDictionary.initialization();

		event.getRegistry().register(new RecipeAccessoryDyes().setRegistryName("aether_dyed_gloves"));

		AetherAddonRegistries.initializeRecipes((IForgeRegistryModifiable<IRecipe>) event.getRegistry());
	}

	@SubscribeEvent
	public void onRegisterMoaTypeEvent(RegistryEvent.Register<AetherMoaType> event)
	{
		AetherMoaTypes.moaRegistry = event.getRegistry();

		AetherMoaTypes.initialization();

		LostMoaTypes.moaRegistry = event.getRegistry();
		LostMoaTypes.initialization();
	}

	@SubscribeEvent
	public void onRegisterEnchantmentEvent(RegistryEvent.Register<AetherEnchantment> event)
	{
		AetherRegistries.initializeEnchantments(event.getRegistry());
		AetherAddonRegistries.initializeEnchantments(event.getRegistry());
		LostAetherRegistries.initializeEnchantments(event.getRegistry());
	}

	@SubscribeEvent
	public void onRegisterEnchantmentFuelEvent(RegistryEvent.Register<AetherEnchantmentFuel> event)
	{
		AetherRegistries.initializeEnchantmentFuel(event.getRegistry());
	}

	@SubscribeEvent
	public void onRegisterFreezableEvent(RegistryEvent.Register<AetherFreezable> event)
	{
		AetherRegistries.initializeFreezables(event.getRegistry());
	}

	@SubscribeEvent
	public void onRegisterFreezableFuelEvent(RegistryEvent.Register<AetherFreezableFuel> event)
	{
		AetherRegistries.initializeFreezableFuel(event.getRegistry());
	}

	@SubscribeEvent
	public void onRegisterAccessoryEvent(RegistryEvent.Register<AetherAccessory> event)
	{
		AetherRegistries.initializeAccessories(event.getRegistry());
		LostAetherRegistries.initializeAccessories(event.getRegistry());
	}

}