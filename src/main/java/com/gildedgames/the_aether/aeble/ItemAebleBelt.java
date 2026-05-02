package com.gildedgames.the_aether.aeble;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.entity.projectile.EntityPotion;
import com.gildedgames.the_aether.entities.projectile.darts.EntityDartBase;
import com.gildedgames.the_aether.entities.projectile.EntityZephyrSnowball;

import java.util.List;
import java.util.Random;

public class ItemAebleBelt extends Item implements IBauble
{
    private Random rand = new Random();

    public ItemAebleBelt(String name)
    {
        this.setRegistryName("aether_legacy", name);
        this.setTranslationKey(name);
        this.setCreativeTab(AetherCreativeTabs.aether);
        this.setMaxStackSize(1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack)
    {
        return BaubleType.BELT;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player)
    {
        if (itemstack.getItem() == AebleRegistry.luck_belt)
        {
            player.getEntityAttribute(SharedMonsterAttributes.LUCK).setBaseValue(60.0);
        }

        if (itemstack.getItem() == AebleRegistry.valkyrie_belt)
        {
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20, 0));
        }

        if (itemstack.getItem() == AebleRegistry.repulsion_belt)
        {
            List<?> entities = player.world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(4.0, 4.0, 4.0));
            for (int i = 0; i < entities.size(); ++i)
            {
                Entity projectile = (Entity) entities.get(i);
                if (isProjectile(projectile) && getShooter(projectile) != player)
                {
                    double x, y, z;
                    Entity shooter = getShooter(projectile);
                    if (shooter != null)
                    {
                        x = player.posX - shooter.posX;
                        y = player.getEntityBoundingBox().minY - shooter.getEntityBoundingBox().minY;
                        z = player.posZ - shooter.posZ;
                    }
                    else
                    {
                        x = player.posX - projectile.posX;
                        y = player.posY - projectile.posY;
                        z = player.posZ - projectile.posZ;
                    }

                    double difference = -Math.sqrt(x * x + y * y + z * z);
                    x /= difference;
                    y /= difference;
                    z /= difference;
                    projectile.motionX = x * 0.75;
                    projectile.motionY = y * 0.75 + 0.05;
                    projectile.motionZ = z * 0.75;
                    setShooter(projectile, player);
                    player.playSound(SoundEvents.BLOCK_NOTE_SNARE, 1.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F + 0.8F) * 1.1F);

                    for (int pack = 0; pack < 12; ++pack)
                    {
                        double packX = -projectile.motionX * 0.15000000596046448 + (this.rand.nextFloat() - 0.5F) * 0.05F;
                        double packY = -projectile.motionY * 0.15000000596046448 + (this.rand.nextFloat() - 0.5F) * 0.05F;
                        double packZ = -projectile.motionZ * 0.15000000596046448 + (this.rand.nextFloat() - 0.5F) * 0.05F;
                        packX *= 0.625;
                        packY *= 0.625;
                        packZ *= 0.625;
                        player.world.spawnParticle(EnumParticleTypes.FLAME, projectile.posX, projectile.posY, projectile.posZ, packX, packY, packZ, new int[0]);
                    }
                }
            }
        }
    }

    private Entity getShooter(Entity ent)
    {
        if (ent instanceof EntityArrow) return ((EntityArrow) ent).shootingEntity;
        if (ent instanceof EntityThrowable) return ((EntityThrowable) ent).getThrower();
        if (ent instanceof EntityDartBase) return ((EntityDartBase) ent).shootingEntity;
        if (ent instanceof EntityFireball) return ((EntityFireball) ent).shootingEntity;
        return null;
    }

    private void setShooter(Entity ent, EntityLivingBase shooter)
    {
        if (ent instanceof EntityArrow)
        {
            ((EntityArrow) ent).shootingEntity = shooter;
        }
        else if (ent instanceof EntityFireball)
        {
            ((EntityFireball) ent).shootingEntity = shooter;
        }
        else if (ent instanceof EntityDartBase)
        {
            ((EntityDartBase) ent).shootingEntity = shooter;
        }
    }

    public static boolean isProjectile(Entity entity)
    {
        return entity instanceof EntityArrow || entity instanceof EntityFireball || entity instanceof EntityThrowable || entity instanceof EntityPotion || entity instanceof EntityDartBase || entity instanceof EntityZephyrSnowball;
    }
}
