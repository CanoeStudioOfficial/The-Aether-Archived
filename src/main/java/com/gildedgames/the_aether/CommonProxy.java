package com.gildedgames.the_aether;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.FMLCommonHandler;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.IModifier;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;

public class CommonProxy 
{

	public void preInitialization() { }

	public void initialization() { }

	public void postInitialization() { }

	public EntityPlayer getThePlayer() { return null; }

	public void sendMessage(EntityPlayer player, ITextComponent message) { }

	public void spawnBlockBrokenFX(IBlockState state, BlockPos pos) { }

	public void spawnSmoke(World world, BlockPos pos) { }

	public void spawnSplode(World world, double x, double y, double z) { }

	public void openSunAltar() { }

	public void registerTinkersItemRenderer(Item item, int meta, String id) { }

	public void setTinkersRenderInfo(Material mat, int color) { }

	public void setTinkersRenderInfo(Material mat, int lo, int mid, int hi) { }

	public void registerTinkersFluidModels(Fluid fluid) { }

	public void registerTinkersToolModel(ToolCore tc) { }

	public void registerTinkersModifierModel(IModifier mod, net.minecraft.util.ResourceLocation rl) { }

	public <T extends Item & IToolPart> void registerTinkersToolPartModel(T part) { }

	public void initTinkersToolGuis() { }

	public void registerTinkersModels() { }

	@SuppressWarnings("deprecation")
	public static void registerEvent(Object event)
	{
		FMLCommonHandler.instance().bus().register(event);
		MinecraftForge.EVENT_BUS.register(event);
	}
}