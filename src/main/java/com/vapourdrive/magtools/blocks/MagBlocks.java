package com.vapourdrive.magtools.blocks;

import com.vapourdrive.magtools.items.blocks.MagItemSlab;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class MagBlocks
{
	public static Block MagLog;
	public static Block MagLeaves;
	public static Block MagSapling;
	public static Block MagPlank;
	public static Block MagStairs;
	public static Block MagSingleSlab;
	public static Block MagDoubleSlab;

	public static void init()
	{
		MagLog = new MagLog();
		MagSapling = new MagSapling();
		MagLeaves = new Magleaves();
		MagPlank = new MagPlank();
		MagStairs = new MagStairs(MagPlank);
		MagSingleSlab = new MagSlab(false, MagPlank);
		MagDoubleSlab = new MagSlab(true, MagPlank);

		GameRegistry.registerBlock(MagLog, MagBlockRef.MagTreeLog);
		GameRegistry.registerBlock(MagSapling, MagBlockRef.MagSapling);
		GameRegistry.registerBlock(MagLeaves, MagBlockRef.MagLeaves);
		GameRegistry.registerBlock(MagPlank, MagBlockRef.MagPlank);
		GameRegistry.registerBlock(MagStairs, MagBlockRef.MagStairs);
		GameRegistry.registerBlock(MagSingleSlab, MagItemSlab.class, MagBlockRef.MagSlab, MagSingleSlab, MagDoubleSlab);
		GameRegistry.registerBlock(MagDoubleSlab, MagItemSlab.class, MagBlockRef.MagSlabDouble, MagSingleSlab, MagDoubleSlab);
	}

	public static void registerRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(MagStairs, 4), new Object[]
		{
				"#  ", "## ", "###", '#', MagPlank
		});
		GameRegistry.addRecipe(new ItemStack(MagStairs, 4), new Object[]
		{
				"  #", " ##", "###", '#', MagPlank
		});
		GameRegistry.addShapelessRecipe(new ItemStack(MagPlank, 4), new ItemStack(MagLog));

	}

}
