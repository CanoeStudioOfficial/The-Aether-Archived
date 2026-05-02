package com.gildedgames.the_aether.lost;

import com.gildedgames.the_aether.AetherConfig;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LostContentConfig
{
	public static final VisualChanges visual = new VisualChanges();

	public static class VisualChanges
	{
		public boolean aether_menu()
		{
			return AetherConfig.lost_content.aether_menu;
		}

		public boolean server_button()
		{
			return AetherConfig.lost_content.server_button;
		}
	}
}
