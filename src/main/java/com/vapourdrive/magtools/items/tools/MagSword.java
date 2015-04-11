package com.vapourdrive.magtools.items.tools;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;
import com.vapourdrive.magtools.items.MagItemRef;
import com.vapourdrive.magtools.utils.LangUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MagSword extends ItemSword
{

	public MagSword(ToolMaterial material)
	{
		super(material);
		this.setCreativeTab(MagTools.MagCreativeTab);
		this.setUnlocalizedName(MagItemRef.MagSwordName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(Reference.ResourcePath + MagItemRef.MagSwordName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add(EnumChatFormatting.GREEN + LangUtils.Translate("phrase.magtools.does") + " " + (float)(player.experienceLevel/10) + " " + LangUtils.Translate("phrase.magtools.xpdamage"));
	}
	
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
    	double xVelocity = ((entity.posX - player.posX)/(entity.getDistanceToEntity(player)*3)) + player.motionX * 2;
    	double zVelocity = ((entity.posZ - player.posZ)/(entity.getDistanceToEntity(player)*3)) + player.motionZ * 2;
    	entity.setVelocity(xVelocity, 0.5, zVelocity);
    	
    	if(player.experienceLevel > 0)
    	{
    		entity.attackEntityFrom(DamageSource.magic, player.experienceLevel/10);
    	}
        return false;
    }

}
