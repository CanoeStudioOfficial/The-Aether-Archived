package com.gildedgames.the_aether.addon.registry;

import com.gildedgames.the_aether.AetherConfig;



import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.addon.items.ItemsAetherAddon;
import com.gildedgames.the_aether.api.enchantments.AetherEnchantment;
import com.gildedgames.the_aether.registry.AetherRegistries;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

/**
 * @see AetherRegistries
 */
public class AetherAddonRegistries {
	
	public static void initializeRecipes(IForgeRegistryModifiable<IRecipe> registry) {
		if (AetherConfig.addon_options.enable_skyroot_crafting_table)
			registry.remove(Aether.locate("crafting_table"));
		else
			registry.remove(Aether.locate("skyroot_crafting_table"));
		
		if (AetherConfig.addon_options.enable_skyroot_chest)
			registry.remove(Aether.locate("skyroot_chest"));
		else
			registry.remove(Aether.locate("skyroot_chest"));
		
		if (AetherConfig.enable_skyroot_door())
			registry.remove(Aether.locate("skyroot_door"));
		else
			registry.remove(Aether.locate("skyroot_door"));
		
		if (!AetherConfig.enable_zanite_door())
			registry.remove(Aether.locate("zanite_door"));
		
		if (AetherConfig.enable_skyroot_trapdoor())
			registry.remove(Aether.locate("skyroot_trapdoor"));
		else
			registry.remove(Aether.locate("skyroot_trapdoor"));
		
		if (!AetherConfig.enable_zanite_trapdoor())
			registry.remove(Aether.locate("zanite_trapdoor"));
		
		if (AetherConfig.addon_options.enable_skyroot_ladder)
			registry.remove(Aether.locate("skyroot_ladder"));
		else
			registry.remove(Aether.locate("skyroot_ladder"));
		
		if (AetherConfig.addon_options.enable_skyroot_sign)
			registry.remove(Aether.locate("skyroot_sign"));
		else
			registry.remove(Aether.locate("skyroot_sign"));
		
		if (!AetherConfig.enable_skyroot_pressure_plate())
			registry.remove(Aether.locate("skyroot_pressure_plate"));
		
		if (!AetherConfig.enable_holystone_pressure_plate())
			registry.remove(Aether.locate("holystone_pressure_plate"));
		
		if (!AetherConfig.enable_zanite_pressure_plate())
			registry.remove(Aether.locate("zanite_pressure_plate"));
		
		if (!AetherConfig.enable_skyroot_button())
			registry.remove(Aether.locate("skyroot_button"));
		
		if (!AetherConfig.enable_holystone_button())
			registry.remove(Aether.locate("holystone_button"));
		
		if (!AetherConfig.addon_options.enable_aether_lever)
			registry.remove(Aether.locate("aether_lever"));
		
		if (!AetherConfig.addon_options.enable_zanite_bars)
			registry.remove(Aether.locate("zanite_bars"));
		
		if (!AetherConfig.addon_options.enable_quicksoil_glass_pane)
			registry.remove(Aether.locate("quicksoil_glass_pane"));
		
		if (!AetherConfig.addon_options.enable_ambrosium_block) {
			registry.remove(Aether.locate("ambrosium_block"));
			registry.remove(Aether.locate("ambrosium_from_block"));
		}
		
		if (!AetherConfig.addon_options.enable_aetherion_chest) {
			registry.remove(Aether.locate("aetherion_chest"));
			registry.remove(Aether.locate("aetherium_core"));
		}
	}
	
	public static void initializeEnchantments(IForgeRegistry<AetherEnchantment> registry) {
		if (AetherConfig.addon_options.enable_cockatrice_meat) {
			registry.register(new AetherEnchantment(ItemsAetherAddon.cockatrice, ItemsAetherAddon.enchanted_cockatrice, 255));
			registry.register(new AetherEnchantment(ItemsAetherAddon.burnt_cockatrice, ItemsAetherAddon.cooked_enchanted_cockatrice, 255));
		}
	}

}
