package com.gildedgames.the_aether.api.accessories;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class AetherAccessoryBaublesHandler
{
    private static final Map<ItemStack, AccessoryType> accessoryMap = new HashMap<>();
    private static boolean registered = false;

    public static void register()
    {
        if (registered) return;
        MinecraftForge.EVENT_BUS.register(new AetherAccessoryBaublesHandler());
        registered = true;
    }

    public static void registerAccessory(ItemStack stack, AccessoryType type)
    {
        accessoryMap.put(stack, type);
    }

    @SubscribeEvent
    public void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event)
    {
        ItemStack stack = event.getObject();
        if (stack.isEmpty()) return;

        for (Map.Entry<ItemStack, AccessoryType> entry : accessoryMap.entrySet())
        {
            ItemStack registeredStack = entry.getKey();
            if (registeredStack.getItem() == stack.getItem())
            {
                event.addCapability(
                    new ResourceLocation("aether_legacy", "accessory_bauble"),
                    new AetherAccessoryBaublesBridge(entry.getValue())
                );
                break;
            }
        }
    }
}
