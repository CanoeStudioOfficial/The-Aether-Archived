package com.gildedgames.the_aether;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * PLEASE NOTE: IF YOU ARE GOING TO CHANGE ANY EXISTING
 * CONFIGURATION VARIABLES, YOU MUST DELETE YOUR EXISTING
 * aether_legacy.cfg FILE IN THE CONFIG FOLDER OF YOUR
 * TESTING DIRECTORY (normally named "run")
 */
@Config(modid = Aether.modid)
@Config.LangKey(Aether.modid + ".config.title")
public class AetherConfig
{


	public static final Dimension dimension = new Dimension();

	public static class Dimension
	{
		@Config.RangeInt(min=2, max=256)
		@Config.RequiresMcRestart
		@Config.Comment("Set the Dimension ID for the Aether.")
		public int aether_dimension_id = 4;

		@Config.RangeInt(min=2, max=256)
		@Config.RequiresMcRestart
		@Config.Comment("Set the Dimension ID when FALLING FROM the Aether.")
		public int falling_dimension_id = 0;
	}

	public static final WorldGenOptions world_gen = new WorldGenOptions();

	public static class WorldGenOptions
	{
		@Config.Comment("Enables natural christmas decor all the time")
		public boolean christmas_time = false;

		@Config.Comment("Enables natural christmas decor automatically during December and January. If christmas_time is enabled it'll override this.")
		public boolean seasonal_christmas = true;
		
		@Config.Comment("Enables naturally generating tallgrass")
		public boolean tallgrass_enabled = false;
		
		@Config.Comment("Enables natural Pink Aercloud generation")
		public boolean pink_aerclouds = false;

		@Config.RangeInt(min=1, max=1000000)
		@Config.Comment("Pink Aerclouds will generate in about 1/X number of chunks.")
		public int pink_aercloud_spawn_chance = 50;

		@Config.RangeInt(min=1, max=1000000)
		@Config.Comment("Golden Aerclouds will generate in about 1/X number of chunks.")
		public int golden_aercloud_spawn_chance = 50;

		@Config.RangeInt(min=1, max=1000000)
		@Config.Comment("Blue Aerclouds will generate in about 1/X number of chunks.")
		public int blue_aercloud_spawn_chance = 26;

		@Config.RangeInt(min=1, max=1000000)
		@Config.Comment("Cold Aerclouds will generate in about 1/X number of chunks.")
		public int cold_aercloud_spawn_chance = 14;

		@Config.RangeInt(min=1, max=1000000)
		@Config.Comment("Crystal Tree Islands will generate in about 1/X number of chunks.")
		public int crystal_island_spawn_chance = 37;

		@Config.RangeInt(min=1, max=1000000)
		@Config.Comment("Spawn attempt 1 for Bronze Dungeons. Will succeed in about 1/X chunks.")
		public int bronze_dungeon_primary_chance = 15;

		@Config.RangeInt(min=1, max=1000000)
		@Config.Comment("Spawn attempt 2 for Bronze Dungeons. Will succeed in about 1/X chunks.")
		public int bronze_dungeon_secondary_chance = 40;

		@Config.RangeInt(min=1, max=1000000)
		@Config.Comment("Spawn attempt 1 for Silver Dungeons. Will succeed in about 1/X chunks.")
		public int silver_dungeon_primary_chance = 110;

		@Config.RangeInt(min=1, max=1000000)
		@Config.Comment("Spawn attempt 2 for Silver Dungeons. Will succeed in about 1/X chunks.")
		public int silver_dungeon_secondary_chance = 150;

		@Config.RangeInt(min=1, max=1000000)
		@Config.Comment("Spawn attempt 1 for Golden Dungeons. Will succeed in about 1/X chunks.")
		public int golden_dungeon_primary_chance = 140;

		@Config.RangeInt(min=1, max=1000000)
		@Config.Comment("Spawn attempt 2 for Golden Dungeons. Will succeed in about 1/X chunks.")
		public int golden_dungeon_secondary_chance = 170;
	}
	
	public static final VisualOptions visual_options = new VisualOptions();
	
	public static class VisualOptions
	{
		@Config.Comment("Changes Aether mobs to use their old models, if applicable")
		public boolean legacy_models = false;
		
		@Config.Comment("Aerclouds will use their more saturated colors from later updates")
		public boolean updated_aercloud_colors = false;
		
		@Config.Comment("Disables the random trivia/tips you see during loading screens")
		@Config.RequiresMcRestart
		public boolean trivia_disabled = false;

		@Config.Comment("Enables the Aether Menu")
		public boolean menu_enabled = false;

		@Config.Comment("Enables the Aether Menu toggle button")
		public boolean menu_button = true;

		@Config.Comment("Determines whether the Aether b1.7.3 resource pack should be generated.")
		public boolean install_resourcepack = true;

		@Config.Comment("Changes whether the Altar should be named Enchanter or not.")
		public boolean legacy_altar_name = false;
	}
	
	public static final AudioOptions audio_options = new AudioOptions();
	
	public static class AudioOptions
	{
		@Config.Comment("Enables dimensional music")
		public boolean game_music = true;
		
		@Config.Comment("Enables menu music")
		public boolean menu_music = true;
		
		@Config.Comment("Pauses the music when a record is playing")
		public boolean pause_on_record = true;
	}
	
	public static final GameplayChanges gameplay_changes = new GameplayChanges();
	
	public static class GameplayChanges
	{
		@Config.Comment("Determines if the player will get an Aether Portal Frame item when first joining the world.")
		public boolean aether_start = false;

		@Config.Comment("The max amount of life shards that can be used per player")
		public int max_life_shards = 10;
		
		@Config.Comment("If enabled, Aether Portals can only be lit by Skyroot Buckets")
		public boolean skyroot_bucket_only = false;
		
		@Config.Comment("Makes it so you have to eat Ambrosium, instead of just right clicking to heal")
		public boolean ambro_is_edible = false;
		
		@Config.Comment("Enables the Valkyrie Cape in dungeon loot")
		public boolean valkyrie_cape = true;

		@Config.Comment("Enables the Golden Feather in dungeon loot")
		public boolean golden_feather = false;

		@Config.Comment("Disables spawn of the Aether portal for use with portal being provided by another mod.")
		public boolean disable_portal = false;
		
		@Config.Comment("Disables startup loot when entering the Aether")
		public boolean disable_startup_loot = false;

		@Config.Comment("Removes the requirement for a player to be an operator to use the Sun Altar in multiplayer.")
		public boolean sun_altar_multiplayer = false;
		
		@Config.Comment("If disabed, the Sun Spirit's dialog will only show once per world.")
		public boolean repeat_sun_spirit_dialog = true;

		@Config.Comment("Disables eternal day making time cycle in the Aether without having to kill the Sun Spirit. This is mainly intended for use in modpacks.")
		public boolean disable_eternal_day = false;
	}

	public static final MoaStats moastats = new MoaStats();

	public static class MoaStats {

		@Config.RangeInt(min=0, max=10000)
		@Config.Comment("The number of times that a blue Moa can jump.")
		public int blue_moa_jumps = 3;

		@Config.RangeDouble(min=0.1F, max=3F)
		@Config.Comment("How fast a blue Moa can run.")
		public float blue_moa_speed = 0.3F;

		@Config.RangeInt(min=0, max=10000)
		@Config.Comment("The number of times that an orange Moa can jump.")
		public int orange_moa_jumps = 2;

		@Config.RangeDouble(min=0.1F, max=3F)
		@Config.Comment("How fast an orange Moa can run.")
		public float orange_moa_speed = 0.6F;

		@Config.RangeInt(min=0, max=10000)
		@Config.Comment("The number of times that a white Moa can jump.")
		public int white_moa_jumps = 4;

		@Config.RangeDouble(min=0.1F, max=3F)
		@Config.Comment("How fast a white Moa can run.")
		public float white_moa_speed = 0.3F;

		@Config.RangeInt(min=0, max=10000)
		@Config.Comment("The number of times that a black Moa can jump.")
		public int black_moa_jumps = 8;

		@Config.RangeDouble(min=0.1F, max=3F)
		@Config.Comment("How fast a black Moa can run.")
		public float black_moa_speed = 0.3F;
	}

	public static final AddonOptions addon_options = new AddonOptions();

	public static class AddonOptions
	{
		@Config.Comment("Enables the Skyroot Crafting Table")
		@Config.RequiresMcRestart
		public boolean enable_skyroot_crafting_table = true;

		@Config.Comment("Enables the Skyroot Chest")
		@Config.RequiresMcRestart
		public boolean enable_skyroot_chest = true;

		@Config.Comment("Enables the Aetherion Chest")
		@Config.RequiresMcRestart
		public boolean enable_aetherion_chest = true;

		@Config.Comment("Enables the Skyroot Sign")
		@Config.RequiresMcRestart
		public boolean enable_skyroot_sign = true;

		@Config.Comment("Enables the Skyroot Ladder")
		@Config.RequiresMcRestart
		public boolean enable_skyroot_ladder = true;

		@Config.Comment("Enables Doors")
		@Config.RequiresMcRestart
		public boolean enable_doors = true;

		@Config.Comment("Enables the Skyroot Door")
		@Config.RequiresMcRestart
		public boolean enable_skyroot_door = true;

		@Config.Comment("Enables the Zanite Door")
		@Config.RequiresMcRestart
		public boolean enable_zanite_door = true;

		@Config.Comment("Enables Trapdoors")
		@Config.RequiresMcRestart
		public boolean enable_trapdoors = true;

		@Config.Comment("Enables the Skyroot Trapdoor")
		@Config.RequiresMcRestart
		public boolean enable_skyroot_trapdoor = true;

		@Config.Comment("Enables the Zanite Trapdoor")
		@Config.RequiresMcRestart
		public boolean enable_zanite_trapdoor = true;

		@Config.Comment("Enables Pressure Plates")
		@Config.RequiresMcRestart
		public boolean enable_pressure_plates = true;

		@Config.Comment("Enables the Skyroot Pressure Plate")
		@Config.RequiresMcRestart
		public boolean enable_skyroot_pressure_plate = true;

		@Config.Comment("Enables the Holystone Pressure Plate")
		@Config.RequiresMcRestart
		public boolean enable_holystone_pressure_plate = true;

		@Config.Comment("Enables the Zanite Pressure Plate")
		@Config.RequiresMcRestart
		public boolean enable_zanite_pressure_plate = true;

		@Config.Comment("Enables Buttons")
		@Config.RequiresMcRestart
		public boolean enable_buttons = true;

		@Config.Comment("Enables the Skyroot Button")
		@Config.RequiresMcRestart
		public boolean enable_skyroot_button = true;

		@Config.Comment("Enables the Holystone Button")
		@Config.RequiresMcRestart
		public boolean enable_holystone_button = true;

		@Config.Comment("Enables the Aether Lever")
		@Config.RequiresMcRestart
		public boolean enable_aether_lever = true;

		@Config.Comment("Enables Zanite Bars")
		@Config.RequiresMcRestart
		public boolean enable_zanite_bars = true;

		@Config.Comment("Enables Quicksoil Glass Pane")
		@Config.RequiresMcRestart
		public boolean enable_quicksoil_glass_pane = true;

		@Config.Comment("Enables Ambrosium Block")
		@Config.RequiresMcRestart
		public boolean enable_ambrosium_block = true;

		@Config.Comment("Enables Cockatrice Meat")
		@Config.RequiresMcRestart
		public boolean enable_cockatrice_meat = true;
	}

	public static boolean enable_skyroot_door() {
		return addon_options.enable_doors && addon_options.enable_skyroot_door;
	}

	public static boolean enable_zanite_door() {
		return addon_options.enable_doors && addon_options.enable_zanite_door;
	}

	public static boolean enable_skyroot_trapdoor() {
		return addon_options.enable_trapdoors && addon_options.enable_skyroot_trapdoor;
	}

	public static boolean enable_zanite_trapdoor() {
		return addon_options.enable_trapdoors && addon_options.enable_zanite_trapdoor;
	}

	public static boolean enable_skyroot_pressure_plate() {
		return addon_options.enable_pressure_plates && addon_options.enable_skyroot_pressure_plate;
	}

	public static boolean enable_holystone_pressure_plate() {
		return addon_options.enable_pressure_plates && addon_options.enable_holystone_pressure_plate;
	}

	public static boolean enable_zanite_pressure_plate() {
		return addon_options.enable_pressure_plates && addon_options.enable_zanite_pressure_plate;
	}

	public static boolean enable_skyroot_button() {
		return addon_options.enable_buttons && addon_options.enable_skyroot_button;
	}

	public static boolean enable_holystone_button() {
		return addon_options.enable_buttons && addon_options.enable_holystone_button;
	}

	public static final Spawnrates spawnrates = new Spawnrates();

	public static class Spawnrates
	{
		@Config.RangeInt(min=1, max=10000)
		@Config.Comment("Adjusts spawnrate for the Phyg. 1 is always, anything above that lessens the chances.")
		public int phyg_spawnrate = 1;

		@Config.RangeInt(min=1, max=10000)
		@Config.Comment("Adjusts spawnrate for the Flying Cow. 1 is always, anything above that lessens the chances.")
		public int flyingcow_spawnrate = 1;

		@Config.RangeInt(min=1, max=10000)
		@Config.Comment("Adjusts spawnrate for the Sheepuff. 1 is always, anything above that lessens the chances.")
		public int sheepuff_spawnrate = 1;

		@Config.RangeInt(min=1, max=10000)
		@Config.Comment("Adjusts spawnrate for the Aerbunny. 1 is always, anything above that lessens the chances.")
		public int aerbunny_spawnrate = 1;

		@Config.RangeInt(min=1, max=10000)
		@Config.Comment("Adjusts spawnrate for the Moa. 1 is always, anything above that lessens the chances.")
		public int moa_spawnrate = 1;

		@Config.RangeInt(min=1, max=10000)
		@Config.Comment("Adjusts spawnrate for the Aerwhale. 1 is always, anything above that lessens the chances.")
		public int aerwhale_spawnrate = 1;

		@Config.RangeInt(min=1, max=10000)
		@Config.Comment("Adjusts spawnrate for the Zephyr. 1 is always, anything above that lessens the chances.")
		public int zephyr_spawnrate = 65;

		@Config.RangeInt(min=1, max=10000)
		@Config.Comment("Adjusts spawnrate for the Cockatrice. 1 is always, anything above that lessens the chances.")
		public int cockatrice_spawnrate = 45;

		@Config.RangeInt(min=1, max=10000)
		@Config.Comment("Adjusts spawnrate for the Swet. 1 is always, anything above that lessens the chances.")
		public int swet_spawnrate = 40;

		@Config.RangeInt(min=1, max=10000)
		@Config.Comment("Adjusts spawnrate for the Aechor Plant. 1 is always, anything above that lessens the chances.")
		public int aechorplant_spawnrate = 55;

		@Config.RangeInt(min=1, max=10000)
		@Config.Comment("Adjusts spawnrate for the Whirlwind. 1 is always, anything above that lessens the chances.")
		public int whirlwind_spawnrate = 55;
	}

	@Mod.EventBusSubscriber(modid = Aether.modid)
	private static class EventHandler
	{
		/**
		 * Inject the new values and save to the config file when the config has been changed from the GUI.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
		{
			if (event.getModID().equals(Aether.modid))
			{
				ConfigManager.sync(Aether.modid, Config.Type.INSTANCE);
			}
		}
	}
}
