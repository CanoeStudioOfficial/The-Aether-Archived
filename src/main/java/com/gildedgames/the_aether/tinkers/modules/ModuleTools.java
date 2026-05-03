package com.gildedgames.the_aether.tinkers.modules;

import com.gildedgames.the_aether.AetherConfig;
import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.tinkers.tools.ToolDart;
import com.gildedgames.the_aether.tinkers.tools.ToolDartShooter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.IModifier;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.Pattern;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModuleTools {

    public static ToolDartShooter dartShooter;
    public static ToolDart dart;

    public static ToolPart mouthpiece;
    public static ToolPart tube;

    public static ToolPart dartTip;

    public static final List<ToolCore> tools = new ArrayList<>();
    public static final List<IToolPart> parts = new ArrayList<>();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        Aether.TINKERS_LOGGER.info("Tools Module - Begin ItemInit");

        if (AetherConfig.tinkers_options.darts) {
            mouthpiece = new ToolPart(Material.VALUE_Ingot);
            mouthpiece.setTranslationKey("mouthpiece").setRegistryName("mouthpiece");
            event.getRegistry().register(mouthpiece);
            TinkerRegistry.registerToolPart(mouthpiece);
            com.gildedgames.the_aether.tinkers.TinkersClientHelper.registerToolPartModel(mouthpiece);
            parts.add(mouthpiece);

            tube = new ToolPart(Material.VALUE_Ingot * 3);
            tube.setTranslationKey("tube").setRegistryName("tube");
            event.getRegistry().register(tube);
            TinkerRegistry.registerToolPart(tube);
            com.gildedgames.the_aether.tinkers.TinkersClientHelper.registerToolPartModel(tube);
            parts.add(tube);

            dartTip = new ToolPart(Material.VALUE_Ingot);
            dartTip.setTranslationKey("dart_tip").setRegistryName("dart_tip");
            event.getRegistry().register(dartTip);
            TinkerRegistry.registerToolPart(dartTip);
            com.gildedgames.the_aether.tinkers.TinkersClientHelper.registerToolPartModel(dartTip);
            parts.add(dartTip);
        }

        Aether.TINKERS_LOGGER.info("Tools Module - Parts Registered");

        if (AetherConfig.tinkers_options.darts) {
            dartShooter = new ToolDartShooter();
            event.getRegistry().register(dartShooter);
            TinkerRegistry.registerToolForgeCrafting(dartShooter);
            com.gildedgames.the_aether.tinkers.TinkersClientHelper.registerToolModel(dartShooter);
            tools.add(dartShooter);

            dart = new ToolDart();
            event.getRegistry().register(dart);
            TinkerRegistry.registerToolForgeCrafting(dart);
            com.gildedgames.the_aether.tinkers.TinkersClientHelper.registerToolModel(dart);
            tools.add(dart);
        }

        Aether.TINKERS_LOGGER.info("Tools Module - Tools Registered");

        for (final IToolPart part : Collections.unmodifiableList(parts)) {
            for (final ToolCore tool : Collections.unmodifiableList(tools)) {
                for (final PartMaterialType pmt : tool.getRequiredComponents()) {
                    if (pmt.getPossibleParts().contains(part)) {
                        TinkerRegistry.registerStencilTableCrafting(Pattern.setTagForPart(new ItemStack(TinkerTools.pattern), (Item) part));
                    }
                }
            }
        }

        Aether.TINKERS_LOGGER.info("Tools Module - Stencil Crafting Registered");

        for (IModifier modifier : new IModifier[]{
                TinkerModifiers.modBaneOfArthopods,
                TinkerModifiers.modBeheading,
                TinkerModifiers.modDiamond,
                TinkerModifiers.modEmerald,
                TinkerModifiers.modFiery,
                TinkerModifiers.modFins,
                TinkerModifiers.modGlowing,
                TinkerModifiers.modHaste,
                TinkerModifiers.modKnockback,
                TinkerModifiers.modLuck,
                TinkerModifiers.modMendingMoss,
                TinkerModifiers.modNecrotic,
                TinkerModifiers.modReinforced,
                TinkerModifiers.modSharpness,
                TinkerModifiers.modShulking,
                TinkerModifiers.modSilktouch,
                TinkerModifiers.modSmite,
                TinkerModifiers.modSoulbound,
                TinkerModifiers.modWebbed,
        }) {
            com.gildedgames.the_aether.tinkers.TinkersClientHelper.registerModifierModel(modifier,
                    new ResourceLocation(Aether.modid, "models/item/modifiers/" + modifier.getIdentifier()));
        }

        Aether.TINKERS_LOGGER.info("Tools Module - Modifier Models Registered");
        Aether.TINKERS_LOGGER.info("Tools Module - End ItemInit");
    }
}
