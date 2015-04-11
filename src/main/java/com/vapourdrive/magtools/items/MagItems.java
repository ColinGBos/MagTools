package com.vapourdrive.magtools.items;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import com.vapourdrive.magtools.items.tools.MagEarthMover;
import com.vapourdrive.magtools.items.tools.MagHammer;
import com.vapourdrive.magtools.items.tools.MagHatchet;
import com.vapourdrive.magtools.items.tools.MagPick;
import com.vapourdrive.magtools.items.tools.MagSword;

import cpw.mods.fml.common.registry.GameRegistry;

public class MagItems
{
	public static Item MagPick;
	public static Item MagHatchet;
	public static Item MagAxe;
	public static Item MagSword;
	public static Item MagHammer;
	public static Item MagEarthMover;
	
	public static Item MagHandle;
	public static Item MagStick;
	public static Item MagHammerHead;
	public static Item MagPickHead;
	public static Item MagAxeHead;
	public static Item MagBlade;
	public static Item MagHatchetHead;
	public static Item MagGem;
	public static Item MagEarthMoverHead;
	
	public static ToolMaterial Magnanimous = EnumHelper.addToolMaterial("Magnanimous", 3, 2000, 8.0F, 3.5F, 20);
	
	public static void init()
	{
		MagPick = new MagPick(Magnanimous);
		MagSword = new MagSword(Magnanimous);
		MagHammer = new MagHammer(Magnanimous);
		MagHatchet = new MagHatchet(Magnanimous);
		MagEarthMover = new MagEarthMover(Magnanimous);
		
		MagHandle = new LogiclessItem(MagItemRef.HardenedHandle, "phrase.magtools.handleinfo", "phrase.magtools.handleinfo2", 64);
		MagStick = new LogiclessItem(MagItemRef.MagStick, "phrase.magtools.magstickinfo", null, 64);
		MagHammerHead = new LogiclessItem(MagItemRef.MagHammerHead, "phrase.magtools.hammerheadinfo", null, 1);
		MagAxeHead = new LogiclessItem(MagItemRef.MagAxeHead, "phrase.magtools.axeheadinfo", null, 1);
		MagPickHead = new LogiclessItem(MagItemRef.MagPickHead, "phrase.magtools.pickheadinfo", null, 1);
		MagBlade = new LogiclessItem(MagItemRef.MagSwordBlade, "phrase.magtools.swordbladeinfo", null, 1);
		MagGem = new LogiclessItem(MagItemRef.MagGem, "phrase.magtools.geminfo", null, 16);
		MagEarthMoverHead = new LogiclessItem(MagItemRef.MagEarthMoverHead, "phrase.magtools.earthmoverheadinfo", null, 1);
		
		GameRegistry.registerItem(MagSword, MagItemRef.MagSwordName);
		GameRegistry.registerItem(MagPick, MagItemRef.MagPickName);
		GameRegistry.registerItem(MagHammer, MagItemRef.MagHammerName);
		//GameRegistry.registerItem(MagHatchet, MagItemRef.MagHatchetName);
		GameRegistry.registerItem(MagEarthMover, MagItemRef.MagEarthMover);
		
		GameRegistry.registerItem(MagHandle, MagItemRef.HardenedHandle);
		GameRegistry.registerItem(MagEarthMoverHead, MagItemRef.MagEarthMoverHead);
		GameRegistry.registerItem(MagHammerHead, MagItemRef.MagHammerHead);
		//GameRegistry.registerItem(MagAxeHead, MagItemRef.MagAxeHead);
		GameRegistry.registerItem(MagPickHead, MagItemRef.MagPickHead);
		GameRegistry.registerItem(MagBlade, MagItemRef.MagSwordBlade);
		GameRegistry.registerItem(MagGem, MagItemRef.MagGem);
		GameRegistry.registerItem(MagStick, MagItemRef.MagStick);
		
		registerRecipes();
		
	}

	private static void registerRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(MagItems.MagAxeHead), new Object[] {"## ", "###", "## ", '#', MagItems.MagGem});
		GameRegistry.addShapedRecipe(new ItemStack(MagItems.MagBlade), new Object[] {" ##", "###", "## ", '#', MagItems.MagGem});
		GameRegistry.addShapedRecipe(new ItemStack(MagItems.MagHammerHead), new Object[] {"# #", "###", "# #", '#', MagItems.MagGem});
		GameRegistry.addShapedRecipe(new ItemStack(MagItems.MagEarthMoverHead), new Object[] {" # ", "###", "###", '#', MagItems.MagGem});
		GameRegistry.addShapedRecipe(new ItemStack(MagItems.MagPickHead), new Object[] {" # ", "# #", '#', MagItems.MagGem});

		
		GameRegistry.addShapedRecipe(new ItemStack(MagItems.MagHandle), new Object[] {"  #", " # ", "#  ", '#', MagItems.MagStick});
	}

}
