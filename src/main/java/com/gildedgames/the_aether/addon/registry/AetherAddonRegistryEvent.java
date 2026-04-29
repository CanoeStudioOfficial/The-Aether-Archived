package com.gildedgames.the_aether.addon.registry;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.addon.blocks.BlocksAetherAddon;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.LootingEnchantBonus;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AetherAddonRegistryEvent {

	private static final ResourceLocation MIMIC_LOOT = Aether.locate("entities/chest_mimic");
	
	@SubscribeEvent
	public void onLootTableLoadEvent(LootTableLoadEvent event) {
		if(AetherConfig.addon_options.enable_skyroot_chest && event.getName().equals(MIMIC_LOOT)) {
			LootPool chest = event.getTable().getPool("chest");
			if(chest == null) return;
			LootEntry entry = chest.getEntry("minecraft:chest");
			if(entry == null || !(entry instanceof LootEntryItem)) return;
			LootEntryItem item = new LootEntryItem(Item.getItemFromBlock(BlocksAetherAddon.skyroot_chest), 1, 1, new LootFunction[] { new LootingEnchantBonus(new LootCondition[0], new RandomValueRange(0,1), 0) }, new LootCondition[0], "aether_legacy:skyroot_chest");
			chest.removeEntry("minecraft:chest");
			chest.addEntry(item);
		}
	}
	
}
