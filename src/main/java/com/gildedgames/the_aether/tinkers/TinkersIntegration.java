package com.gildedgames.the_aether.tinkers;

import com.gildedgames.the_aether.tinkers.misc.MiscUtils;
import com.gildedgames.the_aether.tinkers.modules.ModuleBase;
import com.gildedgames.the_aether.tinkers.modules.ModuleTools;
import com.gildedgames.the_aether.tinkers.network.HandlerExtendedAttack;
import com.gildedgames.the_aether.tinkers.network.MessageExtendedAttack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.tools.TinkerMaterials;

public class TinkersIntegration {

    public static final String MODID = "tinkersaether";
    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MODID + "network");
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static final BowMaterialStats PLZ_NO = new BowMaterialStats(0.2f, 0.4f, -1f);

    private static boolean initialized = false;

    public static void construct() {
        MinecraftForge.EVENT_BUS.register(TinkersIntegration.class);
        FMLCommonHandler.instance().bus().register(TinkersIntegration.class);
        initialized = true;
    }

    public static void preInit() {
        if (!initialized) return;
        ModuleBase.base.preInit();
    }

    public static void init() {
        if (!initialized) return;
        NETWORK.registerMessage(HandlerExtendedAttack.class, MessageExtendedAttack.class, 0, Side.SERVER);
        ModuleBase.base.init();
        if (com.gildedgames.the_aether.AetherConfig.tinkers_options.skyroot) {
            MiscUtils.displace(TinkerMaterials.wood.getIdentifier());
        }
        if (com.gildedgames.the_aether.AetherConfig.tinkers_options.skyrootLeaf || com.gildedgames.the_aether.AetherConfig.tinkers_options.goldenOakLeaf || com.gildedgames.the_aether.AetherConfig.tinkers_options.crystalLeaf || com.gildedgames.the_aether.AetherConfig.tinkers_options.holidayLeaf) {
            MiscUtils.displace(TinkerMaterials.leaf.getIdentifier());
        }
        if (com.gildedgames.the_aether.AetherConfig.tinkers_options.goldenFeather) {
            MiscUtils.displace(TinkerMaterials.feather.getIdentifier());
        }
    }

    public static void postInit() {
        if (!initialized) return;
        ModuleBase.base.postInit();
        com.gildedgames.the_aether.tinkers.compat.GauntletCompatPatch.register();
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        if (!initialized) return;
        if (com.gildedgames.the_aether.AetherConfig.tinkers_options.darts) {
            EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dart"), com.gildedgames.the_aether.tinkers.entities.EntityDart.class, "dart", 13, com.gildedgames.the_aether.Aether.instance, 64, 1, false);
        }
        LOGGER.info("Tinkers Aether - Entities Registered");
    }
}
