package com.gildedgames.the_aether.lost.registry;

import com.gildedgames.the_aether.world.AetherWorld;
import com.gildedgames.the_aether.lost.LostAetherContent;
import com.gildedgames.the_aether.lost.entities.EntityAerwhaleKing;
import com.gildedgames.the_aether.lost.entities.EntityFallingRock;
import com.gildedgames.the_aether.lost.entities.EntityZephyroo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class LostAetherEntities
{
	private static int modEntityId;

	public static void initialization()
	{
		register("king_aerwhale", EntityAerwhaleKing.class);
		register("falling_rock", EntityFallingRock.class);
		register("zephyroo", EntityZephyroo.class, 0x88a5c9, 0xf3f868);

		EntityRegistry.addSpawn(EntityZephyroo.class, 10, 2, 2, EnumCreatureType.CREATURE, AetherWorld.aether_biome);
	}

	private static void register(String entityName, Class<? extends Entity> clazz, int primaryEggColor, int secondaryEggColor)
	{
		EntityRegistry.registerModEntity(LostAetherContent.locate(entityName), clazz, entityName, modEntityId, com.gildedgames.the_aether.Aether.instance, 80, 3, false, primaryEggColor, secondaryEggColor);
		modEntityId++;
	}

	private static void register(String entityName, Class<? extends Entity> clazz)
	{
		EntityRegistry.registerModEntity(LostAetherContent.locate(entityName), clazz, entityName, modEntityId, com.gildedgames.the_aether.Aether.instance, 64, 3, false);
		modEntityId++;
	}
}