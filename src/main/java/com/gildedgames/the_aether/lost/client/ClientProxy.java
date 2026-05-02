package com.gildedgames.the_aether.lost.client;

import com.gildedgames.the_aether.lost.CommonProxy;
import com.gildedgames.the_aether.lost.LostSplashes;
import com.gildedgames.the_aether.lost.client.audio.LostMusicHandler;
import com.gildedgames.the_aether.lost.client.renders.LostAetherRendering;
import com.gildedgames.the_aether.lost.client.renders.LostEntityRenders;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy
{
	public static void clientPreInit()
	{
		new LostSplashes.GetSplashesThread().start();
		CommonProxy.registerEvent(new LostAetherRendering());
		CommonProxy.registerEvent(new LostClientEvents());
		CommonProxy.registerEvent(new LostMusicHandler());
		LostEntityRenders.initialize();
	}

	public static void clientInit()
	{
	}
}
