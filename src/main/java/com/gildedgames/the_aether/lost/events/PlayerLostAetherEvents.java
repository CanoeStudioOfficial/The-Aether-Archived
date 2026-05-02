package com.gildedgames.the_aether.lost.events;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.blocks.dungeon.BlockTreasureChest;
import com.gildedgames.the_aether.entities.projectile.darts.EntityDartBase;
import com.gildedgames.the_aether.tile_entities.TileEntityTreasureChest;
import com.gildedgames.the_aether.lost.items.ItemsLostAether;
import com.gildedgames.the_aether.lost.items.tools.ItemAetherShield;
import com.gildedgames.the_aether.lost.world.dungeon.PlatinumDungeonGenerator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerLostAetherEvents
{
	private boolean stepUpdate;

	private boolean isWearingBauble(EntityPlayer player, Item item)
	{
		IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
		for (int i = 0; i < handler.getSlots(); i++)
		{
			ItemStack stack = handler.getStackInSlot(i);
			if (!stack.isEmpty() && stack.getItem() == item)
			{
				return true;
			}
		}
		return false;
	}

	private void damageWornBauble(EntityPlayer player, Item item, int damage)
	{
		IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
		for (int i = 0; i < handler.getSlots(); i++)
		{
			ItemStack stack = handler.getStackInSlot(i);
			if (!stack.isEmpty() && stack.getItem() == item)
			{
				if (!player.capabilities.isCreativeMode)
				{
					stack.damageItem(damage, player);
				}
				return;
			}
		}
	}

	@SubscribeEvent
	public void onPlayerUpdate(LivingUpdateEvent event)
	{
		if ((event.getEntityLiving() instanceof EntityPlayer))
		{
			EntityPlayer player = ((EntityPlayer) event.getEntityLiving());

			if (player.inventory.armorInventory.get(0).getItem() == ItemsLostAether.agility_boots)
			{
				this.stepUpdate = true;
				player.stepHeight = 1.0F;
			}
			else
			{
				if (this.stepUpdate)
				{
					player.stepHeight = 0.5F;
					this.stepUpdate = false;
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingAttack(LivingAttackEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			DamageSource source = event.getSource();

			boolean isShielding = (player.getActiveItemStack().getItem() instanceof ItemAetherShield) && this.canBlockDamageSource(player, source);

			if (isWearingBauble(player, ItemsLostAether.sentry_shield))
			{
				if (source.isExplosion())
				{
					damageWornBauble(player, ItemsLostAether.sentry_shield, 1);
					event.setCanceled(true);
				}
				else if (player.world.rand.nextBoolean() && !isShielding)
				{
					damageWornBauble(player, ItemsLostAether.sentry_shield, 1);
					player.world.createExplosion(player, player.posX, player.posY, player.posZ, 1, false);
				}
			}

			if (isWearingBauble(player, ItemsLostAether.phoenix_cape) && source.getImmediateSource() instanceof EntityLivingBase && !isShielding)
			{
				source.getImmediateSource().setFire(3);
				damageWornBauble(player, ItemsLostAether.phoenix_cape, 1);
			}

			if (isWearingBauble(player, ItemsLostAether.invisibility_gem))
			{
				if (!player.isPotionActive(MobEffects.INVISIBILITY) && !isShielding && player.world.rand.nextFloat() < 0.05F && !player.world.isRemote)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 160, 0, true, true));
					Aether.proxy.spawnSmoke(player.world, player.getPosition());
				}
			}

			if (player.getActiveItemStack().getItem() instanceof ItemAetherShield && event.getAmount() > 0.0F && this.canBlockDamageSource(player, source))
			{
				source.getImmediateSource().playSound(SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 0.4F, 0.8F + player.world.rand.nextFloat() * 0.4F);

				this.damageShield(player, event.getAmount());

				if (player.getActiveItemStack().getItem() == ItemsLostAether.gravitite_shield && source.getImmediateSource() instanceof EntityLivingBase)
				{
					((EntityLivingBase) source.getImmediateSource()).knockBack(player, 1.5F, player.posX - source.getImmediateSource().posX, player.posZ - source.getImmediateSource().posZ);
					source.getImmediateSource().setPosition(source.getImmediateSource().posX, source.getImmediateSource().posY + 1D, source.getImmediateSource().posZ);
					source.getImmediateSource().isAirBorne = true;
				}

				if (player.getActiveItemStack().getItem() == ItemsLostAether.jeb_shield)
				{
					Entity projectile = source.getImmediateSource();

					if (source.getImmediateSource() instanceof IProjectile)
					{
						Vec3d vec3d = player.getLook(1.0F);
						double x = -(player.posX - (player.posX + vec3d.x * 16.0D));
						double y = vec3d.y * 8;
						double z = -(player.posZ - (player.posZ + vec3d.z * 16.0D));
						((IProjectile) source.getImmediateSource()).shoot(x, y, z, -15F, 0.0F);

						if (projectile instanceof EntityArrow)
						{
							((EntityArrow) projectile).shootingEntity = player;
						}
						else if (projectile instanceof EntityDartBase)
						{
							((EntityDartBase) projectile).shootingEntity = player;
						}
					}
				}
			}
		}
		else if (event.getSource().getImmediateSource() instanceof EntityPlayer)
		{
			DamageSource source = event.getSource();
			EntityPlayer player = (EntityPlayer) source.getImmediateSource();

			if (isWearingBauble(player, ItemsLostAether.power_gloves) && player.getHeldItemMainhand().isEmpty() && event.getEntityLiving().hurtTime <= 0)
			{
				player.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0F, 1.0F);
				player.playSound(SoundEvents.ENTITY_FIREWORK_LAUNCH, 0.4F, 1.0F);
				event.getEntityLiving().knockBack(source.getImmediateSource(), 1.5F, source.getImmediateSource().posX - event.getEntityLiving().posX, source.getImmediateSource().posZ - event.getEntityLiving().posZ);
				damageWornBauble(player, ItemsLostAether.power_gloves, 1);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event)
	{
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		IBlockState state = world.getBlockState(pos);
		EntityPlayer player = event.getEntityPlayer();

		if (state.getBlock() instanceof BlockTreasureChest && player.getHeldItemMainhand().getItem() == ItemsLostAether.platinum_key)
		{
			TileEntityTreasureChest treasurechest = (TileEntityTreasureChest) world.getTileEntity(pos);

			ItemStack guiID = player.getHeldItemMainhand();

			if (treasurechest.isLocked())
			{
				treasurechest.unlock(3);

				for (int p = 0; p < 5 + world.rand.nextInt(1); ++p)
				{
					treasurechest.setInventorySlotContents(world.rand.nextInt(treasurechest.getSizeInventory()), PlatinumDungeonGenerator.getPlatinumLoot(world.rand));
				}

				guiID.shrink(1);
			}
		}
	}

	protected void damageShield(EntityPlayer player, float damage)
	{
		if (damage >= 3.0F && player.getActiveItemStack().getItem() instanceof ItemAetherShield)
		{
			ItemStack copyBeforeUse = player.getActiveItemStack().copy();
			int i = 1 + MathHelper.floor(damage);
			player.getActiveItemStack().damageItem(i, player);

			if (player.getActiveItemStack().isEmpty())
			{
				EnumHand enumhand = player.getActiveHand();
				net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, copyBeforeUse, enumhand);

				if (enumhand == EnumHand.MAIN_HAND)
				{
					player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
				}
				else
				{
					player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
				}

				player.resetActiveHand();
				player.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + player.world.rand.nextFloat() * 0.4F);
			}
		}
	}

	private boolean canBlockDamageSource(EntityPlayer player, DamageSource damageSourceIn)
	{
		if (!damageSourceIn.isUnblockable() && player.isActiveItemStackBlocking())
		{
			Vec3d vec3d = damageSourceIn.getDamageLocation();

			if (vec3d != null)
			{
				Vec3d vec3d1 = player.getLook(1.0F);
				Vec3d vec3d2 = vec3d.subtractReverse(new Vec3d(player.posX, player.posY, player.posZ)).normalize();
				vec3d2 = new Vec3d(vec3d2.x, 0.0D, vec3d2.z);

				if (vec3d2.dotProduct(vec3d1) < 0.0D)
				{
					return true;
				}
			}
		}

		return false;
	}
}
