package com.vapourdrive.magtools.blocks;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

public class MagBlocks
{
	public static Block MagLog;
	public static Block MagLeaves;
	public static Block MagSapling;
	
	public static void init()
	{
		MagLog = new MagLog();
		MagSapling = new MagSapling();
		MagLeaves = new Magleaves();
		
		GameRegistry.registerBlock(MagLog, MagBlockRef.MagTreeLog);
		//GameRegistry.registerBlock(MagSapling, MagBlockRef.MagSapling);
		GameRegistry.registerBlock(MagLeaves, MagBlockRef.MagLeaves);
	}

}
