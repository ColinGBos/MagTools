package com.vapourdrive.magtools.config;

public class ConfigInfo
{
	//Magnanimous Material Options
	public static int MagDurability;
	public static int MagMiningLevel;
	public static int MagEnchantability;
	public static float MagEfficiency;
	public static float MagDamage;

	//Mag Mat Defaults
	public static int MagDurabilityDefault = 2000;
	public static int MagMiningLevelDefault = 4;
	public static int MagEnchantabilityDefault = 22;
	public static float MagEfficiencyDefault = 8.0F;
	public static float MagDamageDefault = 3.5F;

	//Individual Durability Overrides
	public static int MagPickDurability;
	public static int MagHammerDurability;
	public static int MagEarthMoverDurability;
	public static int MagSwordDurability;
	public static int MagAxeDurability;

	//Special tool options
	public static boolean EnableHammerShiftOneBlock;
	public static boolean EnableEarthMoverShiftOneBlock;
	public static boolean EnableAxeWholeTree;
	
	public static int HammerSneakMinXP;
	public static int EarthMoverSneakMinXP;
	public static int SneakXPMinDefault = 20;

	public static boolean EnableXPSpeed;
	public static boolean EnablePickFortune;
	public static boolean EnableSwordXPdamage;
	
	//Tool Enablement
	public static boolean EnableSword;
	public static boolean EnablePick;
	public static boolean EnableHammer;
	public static boolean EnableEarthMover;
	public static boolean EnableAxe;
	
	//WorldGen Options
	public static boolean EnableSaplingGem;
	public static boolean EnableTreeGen;
	public static boolean EnableFlatWorldTree;
	
	public static int TreeGenChance;
	public static int SaplingGemChance;
	public static int SaplingTreeGemLogChance;
	public static int WorldGenTreeGemLogChance;
	
	public static boolean CanBonemealSapling;
	public static int SaplingGrowthChanceNatural;
	public static int SaplingGrowthChanceBonemeal;

}
