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

	public static void init(File mtconfig)
	{
		config = new Configuration(mtconfig);
		MagTools.log.log(Level.INFO, "Loading MagTools Main Config");
		config.load();

		config.setCategoryComment(WorldGen, "Here is where the world settings can be changed");
		config.setCategoryComment(ToolConfigs, "Here is where all the tool settings can be changed");

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

		ConfigInfo.MagPickDurability = config.getInt("Magnanimous Pick Durability", ToolConfigs + "." + OverrideDurability,
				ConfigInfo.MagDurabilityDefault, 1, 100000, "Set specific durability for the Pick");
		ConfigInfo.MagHammerDurability = config.getInt("Magnanimous Hammer Durability", ToolConfigs + "." + OverrideDurability,
				ConfigInfo.MagDurabilityDefault, 1, 100000, "Set specific durability for the Hammer");
		ConfigInfo.MagEarthMoverDurability = config.getInt("Magnanimous Earth Mover Durability", ToolConfigs + "." + OverrideDurability,
				ConfigInfo.MagDurabilityDefault, 1, 100000, "Set specific durability for the Earth Mover");
		ConfigInfo.MagSwordDurability = config.getInt("Magnanimous Sword Durability", ToolConfigs + "." + OverrideDurability,
				ConfigInfo.MagDurabilityDefault, 1, 100000, "Set specific durability for the Sword");

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
		
		ConfigInfo.EnableSword = config.getBoolean("Enable Mag Sword", ToolConfigs + "." + ToolEnabling, true, "If the Mag Sword is enabled");
		ConfigInfo.EnablePick = config.getBoolean("Enable Mag Pick", ToolConfigs + "." + ToolEnabling, true, "If the Mag Pick is enabled");
		ConfigInfo.EnableHammer = config.getBoolean("Enable Mag Hammer", ToolConfigs + "." + ToolEnabling, true, "If the Mag Hammer is enabled");
		ConfigInfo.EnableEarthMover = config.getBoolean("Enable Mag EarthMover", ToolConfigs + "." + ToolEnabling, true, "If the Mag EarthMover is enabled");


		ConfigInfo.EarthMoverSneakMinXP = config.getInt("EarthMover SneakMine XP Requirement",
				ToolConfigs + "." + SpecialAbilitiesSettings, ConfigInfo.EarthMoverSneakMinXP, 0, 200,
				"If the EarthMover sneak-mine-oneblock feature is enabled, this is the experience requirement");
		ConfigInfo.HammerSneakMinXP = config.getInt("Hammer SneakMine XP Requirement", ToolConfigs + "." + SpecialAbilitiesSettings,
				ConfigInfo.EarthMoverSneakMinXP, 0, 200,
				"If the Hammer sneak-mine-oneblock feature is enabled, this is the experience requirement");

		config.save();
	}
}
