package com.gildedgames.the_aether.client.gui.button;

import com.gildedgames.the_aether.networking.AetherNetworkingManager;
import com.gildedgames.the_aether.networking.packets.PacketOpenBaubles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAccessoryButton extends GuiButton
{
	public GuiAccessoryButton(int buttonId, int x, int y)
	{
		super(buttonId, x, y, 20, 18, "A");
	}

	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
	{
		boolean pressed = super.mousePressed(mc, mouseX, mouseY);

		if (pressed)
		{
			AetherNetworkingManager.sendToServer(new PacketOpenBaubles());
		}

		return pressed;
	}
}
