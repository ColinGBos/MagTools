package com.vapourdrive.magtools.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LogiclessItem extends Item
{
	String name;
	String phrase;
	String phrase2;

	public LogiclessItem(String Name, String Phrase, String Phrase2, int Stacksize)
	{
		super();
		this.maxStackSize = Stacksize;
		this.setCreativeTab(MagTools.MagCreativeTab);
		this.setUnlocalizedName(Name);
		phrase = Phrase;
		phrase2 = Phrase2;
		name = Name;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(Reference.ResourcePath + name);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advancedInfo)
	{
		if (phrase != null)
		{
			list.add(StatCollector.translateToLocal(phrase));
		}
		if (phrase2 != null)
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
			{
				list.add(StatCollector.translateToLocal(phrase2));
			}
			else
			{
				list.add(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("phrase.holdshift"));
			}
		}
	}
}
