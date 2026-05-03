package com.gildedgames.the_aether.tinkers;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.tinkers.modules.ModuleTools;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.TinkerRegistryClient;
import slimeknights.tconstruct.library.client.ToolBuildGuiInfo;
import slimeknights.tconstruct.library.modifiers.IModifier;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;

import javax.annotation.Nonnull;

public class TinkersClientHelper {

    public static void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Aether.modid + ":" + id, "inventory"));
    }

    public static void registerFluidModels(Fluid fluid) {
        if (fluid == null) return;
        Block block = fluid.getBlock();
        if (block != null) {
            Item item = Item.getItemFromBlock(block);
            TinkersFluidStateMapper mapper = new TinkersFluidStateMapper(fluid);
            if (item != Items.AIR) {
                ModelBakery.registerItemVariants(item);
                ModelLoader.setCustomMeshDefinition(item, mapper);
            }
            ModelLoader.setCustomStateMapper(block, mapper);
        }
    }

    public static void registerToolModel(ToolCore tc) {
        ModelRegisterUtil.registerToolModel(tc);
    }

    public static void registerModifierModel(IModifier mod, ResourceLocation rl) {
        ModelRegisterUtil.registerModifierModel(mod, rl);
    }

    public static <T extends Item & IToolPart> void registerToolPartModel(T part) {
        ModelRegisterUtil.registerPartModel(part);
    }

    public static void initToolGuis() {
        if (AetherConfig.tinkers_options.darts) {
            if (ModuleTools.dartShooter != null) {
                ToolBuildGuiInfo dartShooterInfo = new ToolBuildGuiInfo(ModuleTools.dartShooter);
                dartShooterInfo.addSlotPosition(32 - 9, 41 - 9);
                dartShooterInfo.addSlotPosition(32 + 9, 41 + 9);
                TinkerRegistryClient.addToolBuilding(dartShooterInfo);
            }
            if (ModuleTools.dart != null) {
                ToolBuildGuiInfo dartInfo = new ToolBuildGuiInfo(ModuleTools.dart);
                dartInfo.addSlotPosition(32, 41);
                dartInfo.addSlotPosition(32 - 18, 41 - 18);
                dartInfo.addSlotPosition(32 + 18, 41 + 18);
                TinkerRegistryClient.addToolBuilding(dartInfo);
            }
        }
    }

    public static void registerModels() {
        if (AetherConfig.tinkers_options.darts) {
            RenderingRegistry.registerEntityRenderingHandler(com.gildedgames.the_aether.tinkers.entities.EntityDart.class, com.gildedgames.the_aether.tinkers.render.RenderDart::new);
        }
        if (net.minecraftforge.fml.common.Loader.isModLoaded("tconstruct")) {
            for (net.minecraftforge.fluids.Fluid fluid : net.minecraftforge.fluids.FluidRegistry.getRegisteredFluids().values()) {
                if (fluid instanceof slimeknights.tconstruct.library.fluid.FluidMolten && fluid.getName().startsWith("molten_")) {
                    registerFluidModels(fluid);
                }
            }
        }
        Aether.TINKERS_LOGGER.info("Tinkers Aether - Models Registered");
    }

    public static class TinkersFluidStateMapper extends StateMapperBase implements ItemMeshDefinition {
        public final Fluid fluid;
        public final ModelResourceLocation location;

        public TinkersFluidStateMapper(Fluid fluid) {
            this.fluid = fluid;
            this.location = new ModelResourceLocation(new ResourceLocation(Aether.modid, "fluid_block"), fluid.getName());
        }

        @Nonnull
        @Override
        protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
            return location;
        }

        @Nonnull
        @Override
        public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
            return location;
        }
    }
}
