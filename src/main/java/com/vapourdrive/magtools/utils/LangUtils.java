package com.vapourdrive.magtools.utils;

import net.minecraft.util.StatCollector;

public class LangUtils
{
	public static String Translate(String input)
	{
		String ReturnString = StatCollector.translateToLocal(input);

		return ReturnString;
	}
}
