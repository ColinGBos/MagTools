package com.vapourdrive.magtools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;

import com.vapourdrive.magtools.config.ConfigHandler;
import com.vapourdrive.magtools.anvilhandler.AnvilManager;
import com.vapourdrive.magtools.blocks.MagBlocks;
import com.vapourdrive.magtools.creativetabs.MagCreativeTab;
import com.vapourdrive.magtools.events.MagEvents;
import com.vapourdrive.magtools.world.MagWorld;
import com.vapourdrive.magtools.items.MagItems;
import com.vapourdrive.magtools.proxies.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.ModID, version = Reference.Version)
public class MagTools
{
	@Instance(Reference.ModID)
	public static MagTools Instance;

	@SidedProxy(clientSide = "com.vapourdrive.magtools.proxies.ClientProxy", serverSide = "com.vapourdrive.magtools.proxies.CommonProxy")
	public static CommonProxy proxy;

	public static CreativeTabs MagCreativeTab;
	public static String ConfigPath;

	public static final Logger log = LogManager.getLogger(Reference.ModID);

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		ConfigPath = event.getModConfigurationDirectory() + "/magtools/";
		MagCreativeTab = new MagCreativeTab(CreativeTabs.getNextID(), "MagCreativeTab");
		ConfigHandler.init(ConfigPath);
		MagItems.init();
		MagBlocks.init();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		MagItems.registerRecipes();
		new MagWorld();
		MagEvents.init();
		new AnvilManager();
	}

	@EventHandler
	public void iPostInit(FMLPostInitializationEvent event)
	{
	}
}
