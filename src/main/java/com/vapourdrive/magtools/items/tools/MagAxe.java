package com.vapourdrive.magtools.items.tools;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.world.World;

import org.apache.logging.log4j.Level;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;
import com.vapourdrive.magtools.config.ConfigInfo;
import com.vapourdrive.magtools.items.MagItemRef;
import com.vapourdrive.magtools.utils.Location;
import com.vapourdrive.magtools.utils.RandomUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MagAxe extends ItemAxe
{

	public MagAxe(ToolMaterial material)
	{
		super(material);
		this.setCreativeTab(MagTools.MagCreativeTab);
		this.setUnlocalizedName(MagItemRef.MagAxe);
		if (ConfigInfo.MagAxeDurability != ConfigInfo.MagDamage)
		{
			this.setMaxDamage(ConfigInfo.MagAxeDurability);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(Reference.ResourcePath + MagItemRef.MagAxe);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
	{
		World world = player.worldObj;

		if (isTree(world, x, y, z))
		{
			breakTree(world, x, y, z, player);
		}

		return false;
	}

	public void breakTree(World world, int x, int y, int z, EntityPlayer player)
	{
		ArrayList<Location> blocks = new ArrayList<Location>();
		chainBreak(0, world, x, y, z, player, blocks);
	}

	public void chainBreak(int count, World world, int x, int y, int z, EntityPlayer player, ArrayList<Location> blocks)
	{
		Location spot = new Location(x, y, z);
		blocks.add(spot);
		for (int i = -1; i <= 1; i++)
		{
			for (int j = -1; j <= 1; j++)
			{
				if (world.getBlock(x + i, y, z + j).isWood(world, x + i, y, z + j))
				{
					log(world, "X: " + (x + i) + ", Y: " + y + ", Z: " + (z + j));
					logBreak(world, x + i, y, z + j, player);
					chainBreak(count + 1, world, x + i, y, z + j, player, blocks);
				}
			}
		}

		if (world.getBlock(x, y + 1, z).isWood(world, x, y + 1, z))
		{
			logBreak(world, x, y + 1, z, player);
			chainBreak(count + 1, world, x, y + 1, z, player, blocks);
		}
	}

	public void logBreak(World world, int x, int y, int z, EntityPlayer player)
	{
		Block block = world.getBlock(x, y, z);
		ItemStack stack = player.getCurrentEquippedItem();
		if(stack != null)
		{
			if(!player.capabilities.isCreativeMode && stack.getItemDamage() < stack.getMaxDamage())
			{
				stack.damageItem(1, player);
				//RandomUtils.breakBlock(world, block, x, y, z, 0, player);
				//ItemInWorldManager.
			}
			else if(player.capabilities.isCreativeMode)
			{
				//RandomUtils.breakBlock(world, block, x, y, z, 0, player);

			}
		}
	}

	public boolean isTree(World world, int x, int y, int z)
	{
		int treeheight = 0;
		boolean hasleaves = false;
		int i = 0;

		while ((world.getBlock(x, y + i, z)).isWood(world, x, y + i, z))
		{
			treeheight++;
			if (!hasleaves)
			{
				for (int h = 0; h <= 3; h++)
				{
					for (int j = -1; j <= 1; j++)
					{
						for (int k = -1; k <= 1; k++)
						{
							if (world.getBlock(x + j, y + i + h, z + k).isLeaves(world, x + j, y + i + h, z + k))
							{
								hasleaves = true;
							}
						}
					}
				}
			}
			i++;
		}

		if (hasleaves && treeheight >= 4)
		{
			return true;
		}

		return false;
	}

	public void log(World world, String log)
	{
		if (world.isRemote)
		{
			MagTools.log.log(Level.INFO, log);
		}
	}

}
