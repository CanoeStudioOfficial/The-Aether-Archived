package com.gildedgames.the_aether.aeble;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.gildedgames.the_aether.registry.creative_tabs.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;

public class ItemAebleRing extends Item implements IBauble
{
    public ItemAebleRing(String name)
    {
        this.setRegistryName("aether_legacy", name);
        this.setTranslationKey(name);
        this.setCreativeTab(AetherCreativeTabs.aether);
        this.setMaxStackSize(1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack)
    {
        return BaubleType.RING;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player)
    {
        if (itemstack.getItem() == AebleRegistry.air_ring)
        {
            player.fallDistance = 0.0F;
        }

        if (itemstack.getItem() == AebleRegistry.ice_ring)
        {
            BlockPos position = player.getPosition();
            IBlockState underblock = player.getEntityWorld().getBlockState(new BlockPos(position.getX(), position.getY() - 1, position.getZ()));

            if (underblock.getBlock() == Blocks.LAVA)
            {
                player.getEntityWorld().setBlockState(new BlockPos(position.getX(), position.getY() - 1, position.getZ()), Blocks.OBSIDIAN.getDefaultState());
            }
            if (underblock.getBlock() == Blocks.FLOWING_LAVA)
            {
                player.getEntityWorld().setBlockState(new BlockPos(position.getX(), position.getY() - 1, position.getZ()), Blocks.STONE.getDefaultState());
            }
            if (underblock.getBlock() == Blocks.FLOWING_WATER)
            {
                player.getEntityWorld().setBlockState(new BlockPos(position.getX(), position.getY() - 1, position.getZ()), Blocks.FROSTED_ICE.getDefaultState());
            }
            if (underblock.getBlock() == Blocks.WATER)
            {
                player.getEntityWorld().setBlockState(new BlockPos(position.getX(), position.getY() - 1, position.getZ()), Blocks.FROSTED_ICE.getDefaultState());
            }
        }

        if (itemstack.getItem() == AebleRegistry.regeneration_ring)
        {
            boolean hasRegen = false;
            for (PotionEffect e : player.getActivePotionEffects())
            {
                if (e.getPotion() == MobEffects.REGENERATION && e.getDuration() > 20)
                {
                    hasRegen = true;
                }
            }
            if (!hasRegen)
            {
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60, 0));
            }
        }

        if (itemstack.getItem() == AebleRegistry.bubble_ring)
        {
            player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 20, 3));
        }

        if (itemstack.getItem() == AebleRegistry.zanite_ring)
        {
            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 20, 0));
        }

        if (itemstack.getItem() == AebleRegistry.obsidian_ring)
        {
            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 20, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20, 0));
        }
    }
}
