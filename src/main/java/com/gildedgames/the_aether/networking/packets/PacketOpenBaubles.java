package com.gildedgames.the_aether.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class PacketOpenBaubles extends AetherPacket<PacketOpenBaubles>
{

	public PacketOpenBaubles()
	{

	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
	}

	@Override
	public void handleClient(PacketOpenBaubles message, EntityPlayer player)
	{
	}

	@Override
	public void handleServer(PacketOpenBaubles message, EntityPlayer player)
	{
		Object baublesMod = Loader.instance().getIndexedModList().get("Baubles");

		if (baublesMod != null)
		{
			player.openGui(baublesMod, 0, player.world, (int) player.posX, (int) player.posY, (int) player.posZ);
		}
	}

}
