package com.gildedgames.the_aether.aeble;

import net.minecraft.item.Item;

public class AebleRegistry
{
    public static ItemAebleRing ice_ring;
    public static ItemAebleRing air_ring;
    public static ItemAebleRing regeneration_ring;
    public static ItemAebleRing bubble_ring;
    public static ItemAebleRing obsidian_ring;
    public static ItemAebleRing zanite_ring;
    public static ItemAebleBelt luck_belt;
    public static ItemAebleBelt valkyrie_belt;
    public static ItemAebleBelt repulsion_belt;

    public static void initialization()
    {
        ice_ring = new ItemAebleRing("aeble_ice_ring");
        air_ring = new ItemAebleRing("aeble_air_ring");
        regeneration_ring = new ItemAebleRing("aeble_regeneration_ring");
        bubble_ring = new ItemAebleRing("aeble_bubble_ring");
        obsidian_ring = new ItemAebleRing("aeble_obsidian_ring");
        zanite_ring = new ItemAebleRing("aeble_zanite_ring");
        luck_belt = new ItemAebleBelt("aeble_luck_belt");
        valkyrie_belt = new ItemAebleBelt("aeble_valkyrie_belt");
        repulsion_belt = new ItemAebleBelt("aeble_repulsion_belt");
    }
}
