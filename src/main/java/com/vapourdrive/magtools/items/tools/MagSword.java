package com.vapourdrive.magtools.items.tools;

import java.util.List;

import org.apache.logging.log4j.Level;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;
import com.vapourdrive.magtools.config.ConfigInfo;
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
		if (ConfigInfo.MagSwordDurability != ConfigInfo.MagDamage)
		{
			this.setMaxDamage(ConfigInfo.MagSwordDurability);
		}
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
		if (ConfigInfo.EnableSwordXPdamage)
		{
			list.add(EnumChatFormatting.GREEN + LangUtils.Translate("phrase.magtools.does") + " " + (float) (player.experienceLevel / 10)
					+ " " + LangUtils.Translate("phrase.magtools.xpdamage"));
		}
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (player.experienceLevel > 0 && ConfigInfo.EnableSwordXPdamage)
		{
			double xVelocity = ((entity.posX - player.posX) / (entity.getDistanceToEntity(player) * 2)) + player.motionX;
			double zVelocity = ((entity.posZ - player.posZ) / (entity.getDistanceToEntity(player) * 2)) + player.motionZ;
			double yVelocity = entity.motionY;
			MagTools.log.log(Level.INFO, yVelocity);
			if (yVelocity < 0.1 && yVelocity > -0.08)
			{
				yVelocity = 0.15;
			}
			entity.setVelocity(xVelocity, yVelocity, zVelocity);
			entity.attackEntityFrom(DamageSource.magic, player.experienceLevel / 10);
		}
		return false;
	}

}
