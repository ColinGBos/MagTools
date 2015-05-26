package com.vapourdrive.magtools.oredictionary;

import com.vapourdrive.magtools.blocks.MagBlocks;
import com.vapourdrive.magtools.items.MagItems;

import net.minecraftforge.oredict.OreDictionary;


public class MagOreDictionary
{
	public static void init()
	{
		OreDictionary.registerOre("plankWood", MagBlocks.MagPlank);
		OreDictionary.registerOre("treeSapling", MagBlocks.MagSapling);
		OreDictionary.registerOre("treeLeaves", MagBlocks.MagLeaves);
		OreDictionary.registerOre("stickWood", MagItems.MagStick);
		OreDictionary.registerOre("stairWood", MagBlocks.MagStairs);
	}
}
