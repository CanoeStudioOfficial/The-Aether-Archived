package com.gildedgames.the_aether.lost.client.sounds;

import com.gildedgames.the_aether.lost.LostAetherContent;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class LostSounds
{
	public static SoundEvent ENTITY_AERWHALE_KING_IDLE;
	public static SoundEvent ENTITY_AERWHALE_KING_HURT;
	public static SoundEvent ENTITY_AERWHALE_KING_DEATH;

	public static IForgeRegistry<SoundEvent> soundRegistry;

	public static void initialization()
	{
		ENTITY_AERWHALE_KING_IDLE = register("entity.aerwhale_king.idle");
	}

	private static SoundEvent register(String name)
	{
		ResourceLocation location = LostAetherContent.locate(name);

		SoundEvent sound = new SoundEvent(location);

		sound.setRegistryName(location);

		if (soundRegistry != null)
			soundRegistry.register(sound);

		return sound;
	}

}