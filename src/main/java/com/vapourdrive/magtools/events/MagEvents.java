package com.vapourdrive.magtools.events;

import net.minecraftforge.common.MinecraftForge;

public class MagEvents
{
	public static void init()
	{
		MinecraftForge.EVENT_BUS.register(new MagSpeedBonus());
		MinecraftForge.EVENT_BUS.register(new MagAnvilEvent());

	}
}
