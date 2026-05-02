package com.gildedgames.the_aether.lost.client.renders;

import com.gildedgames.the_aether.lost.LostAetherContent;
import com.gildedgames.the_aether.lost.client.models.ModelZephyroo;
import com.gildedgames.the_aether.lost.entities.EntityZephyroo;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderZephyroo extends RenderLiving<EntityZephyroo>
{
	private static final ResourceLocation TEXTURE = LostAetherContent.locate("textures/entities/zephyroo.png");

	public RenderZephyroo(RenderManager rendermanagerIn)
	{
		super(rendermanagerIn, new ModelZephyroo(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityZephyroo zephyr)
	{
		return TEXTURE;
	}
}