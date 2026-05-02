package com.gildedgames.the_aether.aeble;

import com.gildedgames.the_aether.items.accessories.ItemAccessory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistry;

public class AebleEventHandler
{
    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event)
    {
        String name = event.getName().toString();

        switch (name)
        {
            case "aether_legacy:chests/bronze_dungeon_chest":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_bronze_dungeon_chest")));
                break;
            case "aether_legacy:chests/bronze_dungeon_chest_sub0":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_bronze_dungeon_chest_sub0")));
                break;
            case "aether_legacy:chests/bronze_dungeon_chest_sub1":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_bronze_dungeon_chest_sub1")));
                break;
            case "aether_legacy:chests/bronze_dungeon_chest_sub2":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_bronze_dungeon_chest_sub2")));
                break;
            case "aether_legacy:chests/bronze_dungeon_chest_sub3":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_bronze_dungeon_chest_sub3")));
                break;
            case "aether_legacy:chests/bronze_dungeon_reward":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_bronze_dungeon_reward")));
                break;
            case "aether_legacy:chests/silver_dungeon_chest":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_silver_dungeon_chest")));
                break;
            case "aether_legacy:chests/silver_dungeon_chest_sub0":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_silver_dungeon_chest_sub0")));
                break;
            case "aether_legacy:chests/silver_dungeon_chest_sub1":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_silver_dungeon_chest_sub1")));
                break;
            case "aether_legacy:chests/silver_dungeon_chest_sub2":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_silver_dungeon_chest_sub2")));
                break;
            case "aether_legacy:chests/silver_dungeon_chest_sub3":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_silver_dungeon_chest_sub3")));
                break;
            case "aether_legacy:chests/silver_dungeon_chest_sub4":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_silver_dungeon_chest_sub4")));
                break;
            case "aether_legacy:chests/silver_dungeon_chest_sub5":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_silver_dungeon_chest_sub5")));
                break;
            case "aether_legacy:chests/silver_dungeon_reward":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_silver_dungeon_reward")));
                break;
            case "aether_legacy:chests/gold_dungeon_chest":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_gold_dungeon_chest")));
                break;
            case "aether_legacy:chests/gold_dungeon_reward":
                event.setTable(event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("aether_legacy", "chests/aeble_gold_dungeon_reward")));
                break;
        }
    }

    @SubscribeEvent
    public void onRegisterRecipes(RegistryEvent.Register<IRecipe> event)
    {
        removeAccessoryRecipes();
    }

    private void removeAccessoryRecipes()
    {
        ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>) net.minecraftforge.fml.common.registry.ForgeRegistries.RECIPES;
        for (IRecipe recipe : recipeRegistry.getValuesCollection())
        {
            ItemStack output = recipe.getRecipeOutput();
            if (!output.isEmpty() && output.getItem().getClass() == ItemAccessory.class)
            {
                recipeRegistry.remove(recipe.getRegistryName());
            }
        }
    }
}
