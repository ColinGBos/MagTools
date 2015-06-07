package com.vapourdrive.magtools.items.tools;

import java.text.DecimalFormat;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;
import com.vapourdrive.magtools.config.ConfigInfo;
import com.vapourdrive.magtools.items.MagItemRef;
import com.vapourdrive.magtools.utils.RandomUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MagEarthMover extends ItemSpade
{

	public MagEarthMover(ToolMaterial material)
	{
		super(material);
		this.setUnlocalizedName(MagItemRef.MagEarthMover);
		this.setCreativeTab(MagTools.MagCreativeTab);
		if (ConfigInfo.MagEarthMoverDurability != ConfigInfo.MagDamage)
		{
			this.setMaxDamage(ConfigInfo.MagEarthMoverDurability);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(Reference.ResourcePath + MagItemRef.MagEarthMover);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("phrase.magtools.earthmoverinfo"));
		float Bonus = ((float) player.experienceLevel) / 75;
		if (ConfigInfo.EnableXPSpeed)
		{
			list.add(EnumChatFormatting.GREEN + "Goes " + (new DecimalFormat("#.##").format(1.0F + Bonus)) + " Times Faster");
		}
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
	{
		World world = player.worldObj;
		Block block = world.getBlock(x, y, z);

		MovingObjectPosition object = RandomUtils.raytraceFromEntity(world, player, false, 4.5D);

		if (object == null)
		{
			return super.onBlockDestroyed(stack, world, block, x, y, z, player);
		}

		int side = object.sideHit;
		int xmove = 0;
		int ymove = 0;
		int zmove = 0;

		if (side == 0 || side == 1)
		{
			xmove = 1;
			zmove = 1;
		}
		else
		{
			ymove = 1;
			if (side == 4 || side == 5)
			{
				zmove = 1;
			}
			else
			{
				xmove = 1;
			}
		}

		float strength = ForgeHooks.blockStrength(block, player, world, x, y, z);

		if (player.isSneaking() && ConfigInfo.EnableEarthMoverShiftOneBlock
				&& (player.experienceLevel >= 20 || player.capabilities.isCreativeMode))
		{
			checkBlockBreak(world, player, x, y, z, stack, strength, block, side);
		}
		else
		{
			for (int i = -xmove; i <= xmove; i++)
			{
				for (int j = -ymove; j <= ymove; j++)
				{
					for (int k = -zmove; k <= zmove; k++)
					{
						if (i != x && j != y && k != z)
						{
							checkBlockBreak(world, player, x + i, y + j, z + k, stack, strength, block, side);
						}
					}
				}
			}
		}
		return false;
	}

	public void checkBlockBreak(World world, EntityPlayer player, int x, int y, int z, ItemStack stack, float strength,
			Block originalBlock, int side)
	{
		Block breakBlock = world.getBlock(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);
		if (ForgeHooks.canToolHarvestBlock(breakBlock, metadata, stack))
		{
			float newStrength = ForgeHooks.blockStrength(breakBlock, player, world, x, y, z);

			if (newStrength > 0f && strength / newStrength <= 10f)
			{
				RandomUtils.breakBlock(world, breakBlock, x, y, z, side, player);

				if ((double) breakBlock.getBlockHardness(world, x, y, z) != 0.0D)
				{
					stack.damageItem(1, player);
				}
			}
		}

	}



}
