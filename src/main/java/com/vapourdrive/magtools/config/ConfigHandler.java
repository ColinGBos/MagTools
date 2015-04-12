package com.vapourdrive.magtools.config;

import java.io.File;

public class ConfigHandler
{
	public static File MagToolsConfig;
	
	public static void init(String ConfigPath)
	{
		MagToolsConfig = new File(ConfigPath + "magtools.cfg");
		MagToolsConfigMain.init(MagToolsConfig);
	}
}
