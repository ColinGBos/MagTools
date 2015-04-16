package com.vapourdrive.magtools.items.tools;

import java.text.DecimalFormat;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;
import com.vapourdrive.magtools.config.ConfigInfo;
import com.vapourdrive.magtools.items.MagItemRef;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MagPick extends ItemPickaxe
{

	public MagPick(ToolMaterial material)
	{
		super(material);
		this.setUnlocalizedName(MagItemRef.MagPickName);
		this.setCreativeTab(MagTools.MagCreativeTab);
		if (ConfigInfo.MagPickDurability != ConfigInfo.MagDamage)
		{
			this.setMaxDamage(ConfigInfo.MagPickDurability);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(Reference.ResourcePath + MagItemRef.MagPickName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		float Bonus = ((float) player.experienceLevel) / 75;
		if (ConfigInfo.EnableXPSpeed)
		{
			list.add(EnumChatFormatting.GREEN + "Goes " + (new DecimalFormat("#.##").format(1.0F + Bonus)) + " Times Faster");
		}

		if (ConfigInfo.EnablePickFortune)
		{
			int fortune = (player.experienceLevel / 25);
			if (fortune > 10)
			{
				fortune = 10;
			}
			list.add(EnumChatFormatting.GREEN + "Has Level " + fortune + " Drop Bonus");
		}
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity)
	{
		int meta = world.getBlockMetadata(x, y, z);
		int fortune = 0;

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			fortune = (player.experienceLevel / 25 - 1);
			if (fortune > 10)
			{
				fortune = 10;
			}
		}

		if ((double) block.getBlockHardness(world, x, y, z) != 0.0D)
		{
			stack.damageItem(1, entity);
		}

		if (!world.isRemote && fortune >= 0 && EnchantmentHelper.getSilkTouchModifier(entity) == false && ConfigInfo.EnablePickFortune)
		{
			if ((world.rand.nextFloat() + 0.2F * fortune) > 0.6)
			{
				int halffortune = fortune / 2;
				if (block.quantityDroppedWithBonus(fortune + 1, world.rand) > block.quantityDroppedWithBonus(0, world.rand))
				{
					block.dropBlockAsItem(world, x, y, z, meta, (halffortune + world.rand.nextInt(halffortune + 1)));
				}
			}
		}

		return true;
	}
}
