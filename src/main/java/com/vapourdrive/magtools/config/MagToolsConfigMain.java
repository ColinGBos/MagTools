package com.vapourdrive.magtools.config;

import java.io.File;

import org.apache.logging.log4j.Level;

import com.vapourdrive.magtools.MagTools;

import net.minecraftforge.common.config.Configuration;

public class MagToolsConfigMain
{
	public static Configuration config;

	private static final String ToolConfigs = "Tool Settings";
	private static final String MaterialConfigs = "Material Settings";
	private static final String OverrideDurability = "Override Durability";
	private static final String SpecialAbilitiesEnablement = "Special Tool Features Enablement";
	private static final String SpecialAbilitiesSettings = "Special Tool Feature Settings";
	private static final String ToolEnabling = "Enable Tools";

	private static final String WorldGen = "World Settings";
	private static final String WorldGenEnabling = "Enable Worldgen";
	private static final String TreeGenSettings = "TreeGen Settings";
	private static final String SaplingSettings = "Tree settings";

	public static void init(File mtconfig)
	{
		config = new Configuration(mtconfig);
		MagTools.log.log(Level.INFO, "Loading MagTools Main Config");
		config.load();

		config.setCategoryComment(WorldGen, "Here is where the world settings can be changed");
		config.setCategoryComment(ToolConfigs, "Here is where all the tool settings can be changed");

		toolOptions();
		worldOptions();

		config.save();
	}

	public static void worldOptions()
	{
		// Enabling options
		ConfigInfo.EnableSaplingGem = config.getBoolean("Enable Sapling-Tree GemSpawning", WorldGen + "." + WorldGenEnabling, false,
				"Enables a tree grown from a sapling to grow with gems in its core");
		ConfigInfo.EnableTreeGen = config.getBoolean("Enable Tree Gen", WorldGen + "." + WorldGenEnabling, true,
				"Enables the tree to generate in the world");
		ConfigInfo.EnableFlatWorldTree = config.getBoolean("Enable FlatWorld Tree Gen", WorldGen + "." + WorldGenEnabling, false,
				"Enables the tree to generate in a flat world type");

		// World Gen tweaks
		ConfigInfo.TreeGenChance = config.getInt("Tree Gen Chance", WorldGen + "." + TreeGenSettings, 50, 1, 1000,
				"Chance 1/x that a tree will attempt to generate in a chunk. \n"
						+ "The conditions are strick, so 1/50 may mean 1 tree per 500 chunks");
		ConfigInfo.SaplingGemChance = config.getInt("Sapling GemTree Growth Chance", WorldGen + "." + TreeGenSettings, 3, 1, 1000,
				"If saplings can grow trees with gems, there is a 1/x chance that the tree will have gems");
		ConfigInfo.SaplingTreeGemLogChance = config.getInt("Sapling-Tree Gem-Log Chance", WorldGen + "." + TreeGenSettings, 4, 1, 20,
				"The 1/x chance that a center log will have a gem in a sapling grown tree (if enabled)");
		ConfigInfo.WorldGenTreeGemLogChance = config.getInt("WorldGen Tree Gem Chance", WorldGen + "." + TreeGenSettings, 4, 1, 20,
				"The 1/x chance that a center log will have a gem in a worldgen tree");

		// sapling settings
		ConfigInfo.CanBonemealSapling = config.getBoolean("Enable Bonemeal Sapling", WorldGen + "." + SaplingSettings, false,
				"Enables the sapling to grow via bonemealing");
		ConfigInfo.SaplingGrowthChanceNatural = config.getInt("Natural Sapling Growth Chance", WorldGen + "." + SaplingSettings, 10, 1,
				1000, "1/x chance that a sapling will age when it's block experiences an update naturally");
		ConfigInfo.SaplingGrowthChanceBonemeal = config.getInt("Bonemealed Sapling Growth Chance", WorldGen + "." + SaplingSettings, 4, 1,
				1000, "1/x chance that a sapling will age when it's block is bonemealed \n"
						+ "The average number of bonemeal consumed per tree is this number * 15");

	}

	public static void toolOptions()
	{
		// Material Settings
		ConfigInfo.MagDurability = config.getInt("Magnanimous Material Durability", ToolConfigs + "." + MaterialConfigs,
				ConfigInfo.MagDurabilityDefault, 1, 100000, "Default base durability for the magnanimous material");
		ConfigInfo.MagEnchantability = config.getInt("Magnanimous Material Enchantability", ToolConfigs + "." + MaterialConfigs,
				ConfigInfo.MagEnchantabilityDefault, 1, 30, "Enchantability of the magnanimous material");
		ConfigInfo.MagMiningLevel = config.getInt("Magannimous Material Mining Level", ToolConfigs + "." + MaterialConfigs,
				ConfigInfo.MagMiningLevelDefault, 0, 5, "The Mining Level of the magnanimous material");
		ConfigInfo.MagEfficiency = config.getFloat("Magnanimous Material Efficiency", ToolConfigs + "." + MaterialConfigs,
				ConfigInfo.MagEfficiencyDefault, 0.0F, 20.0F, "The efficiency that the tools will have mining appropriate blocks");
		ConfigInfo.MagDamage = config.getFloat("Magnanimous Material Damage", ToolConfigs + "." + MaterialConfigs,
				ConfigInfo.MagDamageDefault, 0.0F, 10.0F, "The damage worth that these tools will have against mobs");

		// Durability Overrides
		ConfigInfo.MagPickDurability = config.getInt("Magnanimous Pick Durability", ToolConfigs + "." + OverrideDurability,
				ConfigInfo.MagDurabilityDefault, 1, 100000, "Set specific durability for the Pick");
		ConfigInfo.MagHammerDurability = config.getInt("Magnanimous Hammer Durability", ToolConfigs + "." + OverrideDurability,
				ConfigInfo.MagDurabilityDefault, 1, 100000, "Set specific durability for the Hammer");
		ConfigInfo.MagEarthMoverDurability = config.getInt("Magnanimous Earth Mover Durability", ToolConfigs + "." + OverrideDurability,
				ConfigInfo.MagDurabilityDefault, 1, 100000, "Set specific durability for the Earth Mover");
		ConfigInfo.MagSwordDurability = config.getInt("Magnanimous Sword Durability", ToolConfigs + "." + OverrideDurability,
				ConfigInfo.MagDurabilityDefault, 1, 100000, "Set specific durability for the Sword");
		ConfigInfo.MagAxeDurability = config.getInt("Magnanimous Axe Durability", ToolConfigs + "." + OverrideDurability,
				ConfigInfo.MagDurabilityDefault, 1, 100000, "Set specific durability for the Axe");
		
		// Special Feature Enabling
		ConfigInfo.EnableEarthMoverShiftOneBlock = config.getBoolean("EarthMover Sneak Mine", ToolConfigs + "."
				+ SpecialAbilitiesEnablement, true, "Enables the player to mine one block while sneaking with the EarthMover");
		ConfigInfo.EnableHammerShiftOneBlock = config.getBoolean("Hammer Sneak Mine", ToolConfigs + "." + SpecialAbilitiesEnablement, true,
				"Enables the player to mine one block while sneaking with the Hammer");
		ConfigInfo.EnableXPSpeed = config.getBoolean("XP Speed Bonus Enabling", ToolConfigs + "." + SpecialAbilitiesEnablement, true,
				"This enables the speed boost you get from XPLevels");
		ConfigInfo.EnableSwordXPdamage = config.getBoolean("Sword XP Damage Enabling", ToolConfigs + "." + SpecialAbilitiesEnablement,
				true, "This enables the damage boost the sword gets from your XPLevels");
		ConfigInfo.EnablePickFortune = config.getBoolean("Pick Custom Fortune Enabling", ToolConfigs + "." + SpecialAbilitiesEnablement,
				true, "This enables the XP based extra drop chance");
		ConfigInfo.EarthMoverSneakMinXP = config.getInt("EarthMover SneakMine XP Requirement",
				ToolConfigs + "." + SpecialAbilitiesSettings, ConfigInfo.EarthMoverSneakMinXP, 0, 200,
				"If the EarthMover sneak-mine-oneblock feature is enabled, this is the experience requirement");
		ConfigInfo.HammerSneakMinXP = config.getInt("Hammer SneakMine XP Requirement", ToolConfigs + "." + SpecialAbilitiesSettings,
				ConfigInfo.EarthMoverSneakMinXP, 0, 200,
				"If the Hammer sneak-mine-oneblock feature is enabled, this is the experience requirement");
		ConfigInfo.EnableAxeWholeTree = config.getBoolean("Axe Entire Tree Enabling", ToolConfigs + "." + SpecialAbilitiesEnablement,
				true, "This enables the axe to cut entire trees");

		// Tool Enabling
		ConfigInfo.EnableSword = config.getBoolean("Enable Mag Sword", ToolConfigs + "." + ToolEnabling, true,
				"If the Mag Sword is enabled");
		ConfigInfo.EnablePick = config.getBoolean("Enable Mag Pick", ToolConfigs + "." + ToolEnabling, true, "If the Mag Pick is enabled");
		ConfigInfo.EnableHammer = config.getBoolean("Enable Mag Hammer", ToolConfigs + "." + ToolEnabling, true,
				"If the Mag Hammer is enabled");
		ConfigInfo.EnableEarthMover = config.getBoolean("Enable Mag EarthMover", ToolConfigs + "." + ToolEnabling, true,
				"If the Mag EarthMover is enabled");
		ConfigInfo.EnableAxe = config.getBoolean("Enable Mag Axe", ToolConfigs + "." + ToolEnabling, true,
				"If the Mag Axe is enabled");

	}
}
