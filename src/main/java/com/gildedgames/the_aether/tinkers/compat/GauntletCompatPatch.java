package com.gildedgames.the_aether.tinkers.compat;

import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GauntletCompatPatch
{
    private static float lastDMG = -1;
    private static final Random RAND = new Random();

    private static final String MORETCON_MODID = "moretcon";
    private static final String GAUNTLET_CLASS_NAME = "com.existingeevee.moretcon.item.tooltypes.Gauntlet";

    private static boolean registered = false;
    private static Class<? extends Item> gauntletClass = null;

    public static void register()
    {
        if (registered) return;
        if (!Loader.isModLoaded(MORETCON_MODID)) return;

        try
        {
            Class<?> raw = Class.forName(GAUNTLET_CLASS_NAME);
            if (Item.class.isAssignableFrom(raw))
            {
                gauntletClass = raw.asSubclass(Item.class);
            }
        }
        catch (ClassNotFoundException e)
        {
            return;
        }

        if (gauntletClass != null)
        {
            MinecraftForge.EVENT_BUS.register(new GauntletCompatPatch());
            registered = true;
        }
    }

    private GauntletCompatPatch() {}

    private boolean isGauntlet(ItemStack stack)
    {
        return gauntletClass != null && !stack.isEmpty() && gauntletClass.isInstance(stack.getItem());
    }

    private List<ItemStack> getEquippedGauntlets(EntityPlayer player)
    {
        List<ItemStack> list = new ArrayList<>();
        IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
        for (int i = 0; i < handler.getSlots(); i++)
        {
            ItemStack stack = handler.getStackInSlot(i);
            if (isGauntlet(stack))
            {
                list.add(stack);
            }
        }
        return list;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onLivingKnockBack(LivingKnockBackEvent e)
    {
        if (gauntletClass == null) return;

        int count = 0;
        for (StackTraceElement ste : Thread.currentThread().getStackTrace())
        {
            if (ste.getClassName().equals(this.getClass().getName()))
            {
                count++;
            }
        }

        if (count > 1) return;

        if (e.getAttacker() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) e.getAttacker();

            for (ItemStack itemstack : getEquippedGauntlets(player))
            {
                if (isGauntlet(itemstack) && !ToolHelper.isBroken(itemstack))
                {
                    float knockback = e.getOriginalStrength();
                    for (ITrait t : ToolHelper.getTraits(itemstack))
                    {
                        knockback = t.knockBack(itemstack, player, e.getEntityLiving(), lastDMG, e.getOriginalStrength(), knockback, RAND.nextBoolean());
                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onLivingHurt(LivingHurtEvent e)
    {
        if (gauntletClass == null) return;

        int count = 0;
        for (StackTraceElement ste : Thread.currentThread().getStackTrace())
        {
            if (ste.getClassName().equals(this.getClass().getName()))
            {
                count++;
            }
        }

        if (count > 1) return;

        if (e.getSource().getImmediateSource() instanceof EntityPlayer && e.getSource().getDamageType().equals("player"))
        {
            EntityPlayer player = (EntityPlayer) e.getSource().getImmediateSource();

            lastDMG = e.getAmount();
            for (ItemStack itemstack : getEquippedGauntlets(player))
            {
                if (isGauntlet(itemstack) && !ToolHelper.isBroken(itemstack))
                {
                    boolean crit = Math.random() < 0.125;
                    for (ITrait t : ToolHelper.getTraits(itemstack))
                    {
                        lastDMG = t.damage(itemstack, player, e.getEntityLiving(), e.getAmount(), lastDMG, crit);
                    }
                    for (ITrait t : ToolHelper.getTraits(itemstack))
                    {
                        t.onHit(itemstack, player, e.getEntityLiving(), lastDMG, crit);
                    }
                    for (ITrait t : ToolHelper.getTraits(itemstack))
                    {
                        t.afterHit(itemstack, player, e.getEntityLiving(), lastDMG, crit, true);
                    }
                    if (Math.random() < 0.75 && !player.capabilities.isCreativeMode)
                    {
                        ToolHelper.damageTool(itemstack, 1, player);
                    }
                }
            }
            e.setAmount(lastDMG);
        }
    }
}
