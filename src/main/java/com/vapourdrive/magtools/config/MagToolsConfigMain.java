package com.vapourdrive.magtools.config;

import java.io.File;

import org.apache.logging.log4j.Level;

import com.vapourdrive.magtools.MagTools;

import net.minecraftforge.common.config.Configuration;

public class MagToolsConfigMain
{
	public static Configuration config;
	
	private static final String ToolConfigs = "ToolConfigs";
	
	
	public static void init(File mtconfig)
	{
		config = new Configuration(mtconfig);
		MagTools.log.log(Level.INFO, "Loading MagTools Main Config");
		
		config.load();
		
		config.save();
	}
}
