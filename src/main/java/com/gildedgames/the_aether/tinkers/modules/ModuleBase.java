package com.gildedgames.the_aether.tinkers.modules;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.api.enchantments.AetherEnchantment;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.tinkers.Materials;
import com.gildedgames.the_aether.tinkers.TinkersIntegration;
import com.gildedgames.the_aether.tinkers.blocks.TABlock;
import com.gildedgames.the_aether.tinkers.fluids.FluidHelper;
import com.gildedgames.the_aether.tinkers.items.TAItem;
import com.gildedgames.the_aether.tinkers.misc.MiscUtils;
import com.gildedgames.the_aether.tinkers.misc.OreDict;
import com.gildedgames.the_aether.tinkers.traits.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.smeltery.CastingRecipe;
import slimeknights.tconstruct.tools.TinkerTools;

import static slimeknights.tconstruct.library.utils.HarvestLevels.*;
import static slimeknights.tconstruct.tools.TinkerTraits.*;

public class ModuleBase {
    public static ModuleBase base = new ModuleBase();

    public ModuleBase() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final Material skyroot = Materials.mat("skyroot", 0x6C633E);
    public static final Material holystone = Materials.mat("holystone", 0xA8AAA8);
    public static final Material goldenAmber = Materials.mat("golden_amber", 0xFFE41C);
    public static final Material zanite = Materials.mat("zanite", 0x6611DD);
    public static final Material gravitite = Materials.mat("gravitite", 0xCC55AA);
    public static final Material valkyrie = Materials.mat("valkyrie", 0xEEEEDD);
    public static final Material swet = Materials.mat("swet", 0x29A6D9);
    public static final Material candyCane = Materials.mat("candy_cane", 0xFF3333);
    public static final Material aercloudBlue = Materials.mat("aercloud_blue", 0x99B2C2);
    public static final Material aercloudCold = Materials.mat("aercloud_cold", 0xAAAAAA);
    public static final Material aercloudGold = Materials.mat("aercloud_gold", 0xFFF1A1);
    public static final Material icestone = Materials.mat("icestone", 0x99999F);

    public static final Material skyrootLeaf = Materials.mat("skyroot_leaf", 0xBFFF5F);
    public static final Material goldenOakLeaf = Materials.mat("golden_oak_leaf", 0xEAF84F);
    public static final Material crystalLeaf = Materials.mat("crystal_leaf", 0x29AAD9);
    public static final Material holidayLeaf = Materials.mat("holiday_leaf", 0xC7A0EB);
    public static final Material goldenFeather = Materials.mat("golden_feather", 0xFFF25C);

    public static final TAItem valkyrieIngot = new TAItem("valkyrie_ingot").setBeaconPayment();
    public static final TAItem valkyrieNugget = new TAItem("valkyrie_nugget");
    public static final TABlock valkyrieBlock = new TABlock("valkyrie_block", net.minecraft.block.material.Material.IRON).setBeaconBase();

    public static final TAItem swetCrystal = new TAItem("swet_crystal");

    public void preInit() {
        TinkersIntegration.LOGGER.info("Base Module - Begin PreInit");

        if (AetherConfig.tinkers_options.skyroot) {
            TinkerRegistry.addMaterialStats(skyroot,
                    new HeadMaterialStats(40, 2.10f, 2.00f, STONE),
                    new HandleMaterialStats(1.10f, 30),
                    new ExtraMaterialStats(20),
                    new BowMaterialStats(1.05f, 1.05f, 0.05f),
                    new ArrowShaftMaterialStats(1.05f, 0));
            skyroot.setCraftable(true).setCastable(false);
            skyroot.addItem("stickSkyroot", 1, Material.VALUE_Shard);
            skyroot.addItem("plankSkyroot", 1, Material.VALUE_Ingot);
            skyroot.addItem("logSkyroot", 1, Material.VALUE_Ingot * 4);
            skyroot.addTrait(Skyrooted.skyrooted, MaterialTypes.HEAD);
            skyroot.addTrait(ecological, MaterialTypes.HEAD);
            skyroot.addTrait(ecological);
            MaterialIntegration skyrootMi = new MaterialIntegration(skyroot).setRepresentativeItem("plankSkyroot");
            TinkerRegistry.integrate(skyrootMi).preInit();
        }

        if (AetherConfig.tinkers_options.holystone) {
            TinkerRegistry.addMaterialStats(holystone,
                    new HeadMaterialStats(130, 4.10f, 3.00f, IRON),
                    new HandleMaterialStats(0.50f, -50),
                    new ExtraMaterialStats(25),
                    TinkersIntegration.PLZ_NO);
            holystone.setCraftable(true).setCastable(false);
            holystone.addItem("holystone", 1, Material.VALUE_Ingot);
            holystone.addTrait(Enlightened.enlightened, MaterialTypes.HEAD);
            holystone.addTrait(cheapskate, MaterialTypes.HEAD);
            holystone.addTrait(cheap);
            MaterialIntegration holystoneMi = new MaterialIntegration(holystone).setRepresentativeItem("holystone");
            TinkerRegistry.integrate(holystoneMi).preInit();
        }

        if (AetherConfig.tinkers_options.zanite) {
            TinkerRegistry.addMaterialStats(zanite,
                    new HeadMaterialStats(210, 2.00f, 4.00f, DIAMOND),
                    new HandleMaterialStats(0.9f, 65),
                    new ExtraMaterialStats(50),
                    TinkersIntegration.PLZ_NO);
            zanite.setCraftable(true).setCastable(false);
            zanite.addItem("gemZanite", 1, Material.VALUE_Ingot);
            zanite.addItem("blockZanite", 1, Material.VALUE_Block);
            zanite.addTrait(Gilded.gilded, MaterialTypes.HEAD);
            zanite.addTrait(Zany.zany, MaterialTypes.HEAD);
            zanite.addTrait(jagged, MaterialTypes.HEAD);
            zanite.addTrait(jagged);
            MaterialIntegration zaniteMi = new MaterialIntegration(zanite).setRepresentativeItem("gemZanite");
            TinkerRegistry.integrate(zaniteMi).preInit();
        }

        if (AetherConfig.tinkers_options.gravitite) {
            TinkerRegistry.addMaterialStats(gravitite,
                    new HeadMaterialStats(950, 7.50f, 5.00f, OBSIDIAN),
                    new HandleMaterialStats(0.9f, 90),
                    new ExtraMaterialStats(90),
                    TinkersIntegration.PLZ_NO);
            gravitite.setCraftable(false).setCastable(true);
            gravitite.addItem("blockEnchantedGravitite", 1, Material.VALUE_Ingot);
            gravitite.addTrait(Antigrav.antigrav, MaterialTypes.HEAD);
            gravitite.addTrait(Launching.launching, MaterialTypes.HEAD);
            gravitite.addTrait(Gilded.gilded, MaterialTypes.HEAD);
            gravitite.addTrait(Launching.launching);
            MaterialIntegration gravititeMi = new MaterialIntegration(null, gravitite, FluidHelper.createFluid(gravitite, 900), null).setRepresentativeItem("blockEnchantedGravitite");
            TinkerRegistry.integrate(gravititeMi).preInit();
        }

        if (AetherConfig.tinkers_options.goldenAmber) {
            TinkerRegistry.addMaterialStats(goldenAmber,
                    new HeadMaterialStats(300, 1.50f, 7.20f, STONE),
                    new HandleMaterialStats(0.7f, 40),
                    new ExtraMaterialStats(30),
                    TinkersIntegration.PLZ_NO);
            goldenAmber.setCraftable(true).setCastable(false);
            goldenAmber.addItem("gemGoldenAmber", 1, Material.VALUE_Ingot);
            goldenAmber.addTrait(Gilded.gilded);
            MaterialIntegration goldenAmberMi = new MaterialIntegration(goldenAmber).setRepresentativeItem("gemGoldenAmber");
            TinkerRegistry.integrate(goldenAmberMi).preInit();
        }

        if (AetherConfig.tinkers_options.valkyrie) {
            TinkerRegistry.addMaterialStats(valkyrie,
                    new HeadMaterialStats(1000, 8.0f, 6.50f, COBALT),
                    new HandleMaterialStats(1.0f, 80),
                    new ExtraMaterialStats(70),
                    TinkersIntegration.PLZ_NO);
            valkyrie.setCraftable(false).setCastable(true);
            valkyrie.addItem("blockValkyrie", 1, Material.VALUE_Block);
            valkyrie.addItem(valkyrieBlock, Material.VALUE_Block);
            valkyrie.addItem("ingotValkryie", 1, Material.VALUE_Ingot);
            valkyrie.addItem(valkyrieIngot, 1, Material.VALUE_Ingot);
            valkyrie.addItem("nuggetValkryie", 1, Material.VALUE_Nugget);
            valkyrie.addItem(valkyrieNugget, 1, Material.VALUE_Nugget);
            valkyrie.addTrait(Gilded.gilded, MaterialTypes.HEAD);
            valkyrie.addTrait(Reach.reach, MaterialTypes.HEAD);
            valkyrie.addTrait(Reach.reach);
            MaterialIntegration valkyrieMi = new MaterialIntegration(null, valkyrie, FluidHelper.createFluid(valkyrie, 1000), "Valkyrie");
            TinkerRegistry.integrate(valkyrieMi).preInit();
        }

        if (AetherConfig.tinkers_options.swet) {
            TinkerRegistry.addMaterialStats(swet,
                    new HeadMaterialStats(1100, 4.5f, 2.0f, STONE),
                    new HandleMaterialStats(0.7f, -100),
                    new ExtraMaterialStats(360),
                    new BowMaterialStats(1.0f, 1.5f, 0.5f));
            swet.setCraftable(true).setCastable(false);
            swet.addItem("slimecrystalSwet", 1, Material.VALUE_Ingot);
            swet.addItem(swetCrystal, 1, Material.VALUE_Ingot);
            swet.addTrait(Swetty.swetty);
            MaterialIntegration swetMi = new MaterialIntegration(swet).setRepresentativeItem("slimecrystalSwet");
            TinkerRegistry.integrate(swetMi).preInit();
        }

        if (AetherConfig.tinkers_options.candyCane) {
            TinkerRegistry.addMaterialStats(candyCane,
                    new HeadMaterialStats(250, 2.5f, 5.0f, STONE),
                    new HandleMaterialStats(1.2f, -120),
                    new ExtraMaterialStats(120),
                    TinkersIntegration.PLZ_NO);
            candyCane.setCraftable(true).setCastable(false);
            candyCane.addItem("candyCane", 1, Material.VALUE_Ingot);
            candyCane.addTrait(Festive.festive);
            candyCane.addTrait(tasty);
            MaterialIntegration candyCaneMi = new MaterialIntegration(candyCane).setRepresentativeItem("candyCane");
            TinkerRegistry.integrate(candyCaneMi).preInit();
        }

        if (AetherConfig.tinkers_options.aercloudCold) {
            TinkerRegistry.addMaterialStats(aercloudCold,
                    new HeadMaterialStats(2000, 0.5f, 0.0f, STONE),
                    new HandleMaterialStats(0.2f, -500),
                    new ExtraMaterialStats(0),
                    TinkersIntegration.PLZ_NO);
            aercloudCold.setCraftable(true).setCastable(false);
            aercloudCold.addItem("aercloudCold", 1, Material.VALUE_Ingot);
            aercloudCold.addTrait(Cushy.cushy);
            MaterialIntegration aercloudColdMi = new MaterialIntegration(aercloudCold).setRepresentativeItem("aercloudCold");
            TinkerRegistry.integrate(aercloudColdMi).preInit();
        }

        if (AetherConfig.tinkers_options.aercloudBlue) {
            TinkerRegistry.addMaterialStats(aercloudBlue,
                    new HeadMaterialStats(2000, 0.5f, 0.0f, STONE),
                    new HandleMaterialStats(0.2f, -500),
                    new ExtraMaterialStats(0),
                    TinkersIntegration.PLZ_NO);
            aercloudBlue.setCraftable(true).setCastable(false);
            aercloudBlue.addItem("aercloudBlue", 1, Material.VALUE_Ingot);
            aercloudBlue.addTrait(Cushy.cushy);
            aercloudBlue.addTrait(Cushy.cushy, MaterialTypes.HEAD);
            aercloudBlue.addTrait(Launching.launching, MaterialTypes.HEAD);
            MaterialIntegration aercloudBlueMi = new MaterialIntegration(aercloudBlue).setRepresentativeItem("aercloudBlue");
            TinkerRegistry.integrate(aercloudBlueMi).preInit();
        }

        if (AetherConfig.tinkers_options.aercloudGold) {
            TinkerRegistry.addMaterialStats(aercloudGold,
                    new HeadMaterialStats(2500, 1.0f, 0.1f, STONE),
                    new HandleMaterialStats(0.25f, -400),
                    new ExtraMaterialStats(20),
                    TinkersIntegration.PLZ_NO);
            aercloudGold.setCraftable(true).setCastable(false);
            aercloudGold.addItem("aercloudGold", 1, Material.VALUE_Ingot);
            aercloudGold.addTrait(Cushy.cushy);
            MaterialIntegration aercloudGoldMi = new MaterialIntegration(aercloudGold).setRepresentativeItem("aercloudGold");
            TinkerRegistry.integrate(aercloudGoldMi).preInit();
        }

        if (AetherConfig.tinkers_options.icestone) {
            TinkerRegistry.addMaterialStats(icestone,
                    new HeadMaterialStats(250, 4.20f, 3.50f, IRON),
                    new HandleMaterialStats(0.50f, -20),
                    new ExtraMaterialStats(40),
                    TinkersIntegration.PLZ_NO);
            icestone.setCraftable(true).setCastable(false);
            icestone.addItem("icestone", 1, Material.VALUE_Ingot);
            icestone.addTrait(Refrigeration.refrigeration);
            MaterialIntegration icestoneMi = new MaterialIntegration(icestone).setRepresentativeItem("icestone");
            TinkerRegistry.integrate(icestoneMi).preInit();
        }

        if (AetherConfig.tinkers_options.skyrootLeaf) {
            TinkerRegistry.addMaterialStats(skyrootLeaf, new FletchingMaterialStats(0.5f, 1.6f));
            skyrootLeaf.setCraftable(true).setCastable(false);
            skyrootLeaf.addItem("treeLeavesSkyroot", 1, Material.VALUE_Shard);
            MaterialIntegration skyrootLeafMi = new MaterialIntegration(skyrootLeaf).setRepresentativeItem("treeLeavesSkyroot");
            TinkerRegistry.integrate(skyrootLeafMi).preInit();
        }

        if (AetherConfig.tinkers_options.goldenOakLeaf) {
            TinkerRegistry.addMaterialStats(goldenOakLeaf, new FletchingMaterialStats(0.7f, 1.7f));
            goldenOakLeaf.setCraftable(true).setCastable(false);
            goldenOakLeaf.addItem("treeLeavesGoldenOak", 1, Material.VALUE_Shard);
            MaterialIntegration goldenOakLeafMi = new MaterialIntegration(goldenOakLeaf).setRepresentativeItem("treeLeavesGoldenOak");
            TinkerRegistry.integrate(goldenOakLeafMi).preInit();
        }

        if (AetherConfig.tinkers_options.crystalLeaf) {
            TinkerRegistry.addMaterialStats(crystalLeaf, new FletchingMaterialStats(0.8f, 2f));
            crystalLeaf.setCraftable(true).setCastable(false);
            crystalLeaf.addItem("treeLeavesCrystal", 1, Material.VALUE_Shard);
            MaterialIntegration crystalLeafMi = new MaterialIntegration(crystalLeaf).setRepresentativeItem("treeLeavesCrystal");
            TinkerRegistry.integrate(crystalLeafMi).preInit();
        }

        if (AetherConfig.tinkers_options.holidayLeaf) {
            TinkerRegistry.addMaterialStats(holidayLeaf, new FletchingMaterialStats(0.9f, 1.8f));
            holidayLeaf.setCraftable(true).setCastable(false);
            holidayLeaf.addItem("treeLeavesHoliday", 1, Material.VALUE_Shard);
            MaterialIntegration holidayLeafMi = new MaterialIntegration(holidayLeaf).setRepresentativeItem("treeLeavesHoliday");
            TinkerRegistry.integrate(holidayLeafMi).preInit();
        }

        if (AetherConfig.tinkers_options.goldenFeather) {
            TinkerRegistry.addMaterialStats(goldenFeather, new FletchingMaterialStats(1.0f, 2f));
            goldenFeather.setCraftable(true).setCastable(false);
            goldenFeather.addItem("featherGold", 1, Material.VALUE_Ingot);
            MaterialIntegration goldenFeatherMi = new MaterialIntegration(goldenFeather).setRepresentativeItem("featherGold");
            TinkerRegistry.integrate(goldenFeatherMi).preInit();
        }

        TinkersIntegration.LOGGER.info("Base Module - Materials Registered");
        TinkersIntegration.LOGGER.info("Base Module - End PreInit");
    }

    public void init() {
        TinkersIntegration.LOGGER.info("Base Module - Begin Init");
        FluidHelper.registerFluidBlocks();
        OreDict.register();
        TinkersIntegration.LOGGER.info("Base Module - OreDict Registered");
        TinkersIntegration.LOGGER.info("Base Module - End Init");
    }

    public void postInit() {
        if (AetherConfig.tinkers_options.gravitite) {
            TinkerRegistry.registerMelting("blockEnchantedGravitite", gravitite.getFluid(), Material.VALUE_Ingot);
            TinkerRegistry.registerBasinCasting(new CastingRecipe(MiscUtils.stackFromOreDict("blockEnchantedGravitite"), gravitite.getFluid(), Material.VALUE_Ingot, 180));
            TinkersIntegration.LOGGER.info("Base Module - Gravitite Stuffs Registered");
        }
    }

    @SubscribeEvent
    public void disableOreSmelting(TinkerRegisterEvent.MeltingRegisterEvent event) {
        if (event.getRecipe().input.getInputs().contains(MiscUtils.stackFromOreDict("oreGravitite"))) {
            event.setCanceled(!AetherConfig.tinkers_options.gravititeOreMelt);
        }
    }

    @SubscribeEvent
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistry<IRecipe> registry = event.getRegistry();
        if (AetherConfig.tinkers_options.gravititeForge) {
            TinkerTools.registerToolForgeBlock(registry, "blockEnchantedGravitite");
        }
        if (AetherConfig.tinkers_options.valkyrie && AetherConfig.tinkers_options.valkyrieForge) {
            TinkerTools.registerToolForgeBlock(registry, "blockValkyrie");
        }
        TinkersIntegration.LOGGER.info("Base Module - Recipes Registered");
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        if (AetherConfig.tinkers_options.valkyrie) {
            registry.register(valkyrieIngot);
            registry.register(valkyrieNugget);
            Item valkBlock = new ItemBlock(valkyrieBlock).setRegistryName(valkyrieBlock.getRegistryName()).setTranslationKey(valkyrieBlock.getTranslationKey());
            registry.register(valkBlock);
            com.gildedgames.the_aether.tinkers.TinkersClientHelper.registerItemRenderer(valkyrieIngot, 0, "valkyrie_ingot");
            com.gildedgames.the_aether.tinkers.TinkersClientHelper.registerItemRenderer(valkyrieNugget, 0, "valkyrie_nugget");
            com.gildedgames.the_aether.tinkers.TinkersClientHelper.registerItemRenderer(valkBlock, 0, "valkyrie_block");
            OreDictionary.registerOre("ingotValkyrie", valkyrieIngot);
            OreDictionary.registerOre("nuggetValkyrie", valkyrieNugget);
            OreDictionary.registerOre("blockValkyrie", valkyrieBlock);
        }
        if (AetherConfig.tinkers_options.swet) {
            registry.register(swetCrystal);
            com.gildedgames.the_aether.tinkers.TinkersClientHelper.registerItemRenderer(swetCrystal, 0, "swet_crystal");
            OreDictionary.registerOre("slimecrystal", swetCrystal);
            OreDictionary.registerOre("slimecrystalSwet", swetCrystal);
        }
        TinkersIntegration.LOGGER.info("Base Module - Items Registered");
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        if (AetherConfig.tinkers_options.valkyrie) {
            valkyrieBlock.setHarvestLevel("pickaxe", 3);
            valkyrieBlock.setHardness(4.0f);
            registry.register(valkyrieBlock);
        }
        TinkersIntegration.LOGGER.info("Base Module - Blocks Registered");
    }

    @SubscribeEvent
    public void onRegisterEnchantmentEvent(RegistryEvent.Register<AetherEnchantment> event) {
        IForgeRegistry<AetherEnchantment> registry = event.getRegistry();
        if (AetherConfig.tinkers_options.valkyrie && AetherConfig.tinkers_options.valkyrieMedalEnchant) {
            registry.register(new AetherEnchantment(new ItemStack(ItemsAether.victory_medal), new ItemStack(valkyrieNugget), 250));
        }
        TinkersIntegration.LOGGER.info("Base Module - Enchantment Recipes Registered");
    }

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        if (AetherConfig.tinkers_options.valkyrie && AetherConfig.tinkers_options.valkyrieDungeonChest) {
            if (event.getName().toString().equals("aether_legacy:chests/silver_dungeon_chest")) {
                LootEntry entry = new LootEntryTable(new ResourceLocation(TinkersIntegration.MODID, "inject/silver_dungeon_chest"), 100, 10, new LootCondition[0], "ta_inject_entry");
                event.getTable().addPool(new LootPool(new LootEntry[]{entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "ta_inject_pool"));
            } else if (event.getName().toString().equals("aether_legacy:chests/silver_dungeon_reward")) {
                LootEntry entry = new LootEntryTable(new ResourceLocation(TinkersIntegration.MODID, "inject/silver_dungeon_reward"), 100, 0, new LootCondition[0], "ta_inject_entry");
                event.getTable().addPool(new LootPool(new LootEntry[]{entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "ta_inject_pool"));
            }
        }
    }
}
