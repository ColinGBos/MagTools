package com.vapourdrive.magtools.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.vapourdrive.magtools.items.MagItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MagCreativeTab extends CreativeTabs
{

	public MagCreativeTab(int id, String lable)
	{
		super(id, lable);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTabLabel()
	{
		return "MagnanimousTools";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "Magnanimous";
	}

	@Override
	public Item getTabIconItem()
	{
		return MagItems.MagGem;
	}

}
