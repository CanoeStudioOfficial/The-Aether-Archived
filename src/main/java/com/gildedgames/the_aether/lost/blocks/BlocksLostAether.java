package com.gildedgames.the_aether.lost.blocks;

import com.gildedgames.the_aether.blocks.BlockAetherSapling;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherStairs;
import com.gildedgames.the_aether.blocks.decorative.BlockAetherWall;
import com.gildedgames.the_aether.items.block.ItemAetherSlab;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import com.gildedgames.the_aether.lost.LostAetherContent;
import com.gildedgames.the_aether.lost.world.AetherGenCrystalTree;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

public class BlocksLostAether
{
	public static Block crystal_sapling;
	public static Block songstone;
	public static Block gale_stone, light_gale_stone, locked_gale_stone, locked_light_gale_stone;
	public static Block gale_stairs, light_gale_stairs, gale_wall;
	public static Block gale_slab;
	public static Block gale_double_slab;

	public static void registerBlocks(IForgeRegistry<Block> registry)
	{
		crystal_sapling = new BlockAetherSapling(new AetherGenCrystalTree());
		songstone = new BlockSongstone(Material.IRON).setBlockUnbreakable();
		gale_stone = new BlockLostDungeonBase(false);
		light_gale_stone = new BlockLostDungeonBase(false);
		locked_gale_stone = new BlockLostDungeonBase(true).setCreativeTab(null);
		locked_light_gale_stone = new BlockLostDungeonBase(true).setCreativeTab(null);
		gale_double_slab = new BlockLostSlab("gale_double_slab", true, Material.ROCK).setHardness(2.0F).setResistance(10.0F);
		gale_double_slab.setCreativeTab(null);
		gale_slab = new BlockLostSlab("gale_slab", false, Material.ROCK).setHardness(0.5F).setResistance(10.0F);
		gale_stairs = new BlockAetherStairs(gale_stone.getDefaultState());
		gale_wall = new BlockAetherWall(gale_stone.getDefaultState());

		registerBlock(registry, "crystal_sapling", crystal_sapling);
		registerBlock(registry, "songstone", songstone);
		registerBlock(registry, "gale_stone", gale_stone);
		registerBlock(registry, "light_gale_stone", light_gale_stone);
		registerBlock(registry, "locked_gale_stone", locked_gale_stone);
		registerBlock(registry, "locked_light_gale_stone", locked_light_gale_stone);
		registerBlock(registry, "gale_double_slab", gale_double_slab);
		registerBlock(registry, "gale_slab", gale_slab);
		registerBlock(registry, "gale_stairs", gale_stairs);
		registerBlock(registry, "gale_wall", gale_wall);

		crystal_sapling.setCreativeTab(AetherCreativeTabs.aether);
		gale_stone.setCreativeTab(AetherCreativeTabs.aether);
		light_gale_stone.setCreativeTab(AetherCreativeTabs.aether);
		gale_slab.setCreativeTab(AetherCreativeTabs.aether);
		gale_stairs.setCreativeTab(AetherCreativeTabs.aether);
		gale_wall.setCreativeTab(AetherCreativeTabs.aether);
	}

	public static void registerItems(IForgeRegistry<Item> registry)
	{
		registerItemBlock(registry, "crystal_sapling", crystal_sapling);
		registerItemBlock(registry, "songstone", songstone);
		registerItemBlock(registry, "gale_stone", gale_stone);
		registerItemBlock(registry, "light_gale_stone", light_gale_stone);
		registerItemBlock(registry, "locked_gale_stone", locked_gale_stone);
		registerItemBlock(registry, "locked_light_gale_stone", locked_light_gale_stone);
		registerItemBlock(registry, "gale_double_slab", gale_double_slab);
		registry.register(new ItemAetherSlab(gale_slab, (BlockSlab) gale_slab, (BlockSlab) gale_double_slab).setRegistryName(LostAetherContent.locate("gale_slab")));
		registerItemBlock(registry, "gale_stairs", gale_stairs);
		registerItemBlock(registry, "gale_wall", gale_wall);
	}

	private static void registerBlock(IForgeRegistry<Block> registry, String name, Block block)
	{
		block.setTranslationKey(name);
		block.setRegistryName(LostAetherContent.locate(name));
		registry.register(block);
	}

	private static void registerItemBlock(IForgeRegistry<Item> registry, String name, Block block)
	{
		registry.register(new ItemBlock(block).setRegistryName(LostAetherContent.locate(name)));
	}
}
