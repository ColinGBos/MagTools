package com.vapourdrive.magtools.blocks;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.Level;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;
import com.vapourdrive.magtools.world.feature.WorldGenMagTree;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MagSapling extends BlockBush implements IGrowable
{

	public MagSapling()
	{
		super();
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		this.setBlockName(MagBlockRef.MagSapling);
		this.setCreativeTab(MagTools.MagCreativeTab);
		this.textureName = (MagBlockRef.MagSapling);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1, 0));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockIcon = register.registerIcon(Reference.ResourcePath + this.getTextureName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return blockIcon;
	}

	@Override	
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		Object tree = new WorldGenMagTree(true, false);
		if (!((WorldGenerator)tree).generate(world, random, x, y, z))
		{
			MagTools.log.log(Level.INFO, "Tree did not grow");
		}
		else
		{
			MagTools.log.log(Level.INFO, "Grow tree!");
		}
	}

	@Override
	public final int damageDropped(int metadata)
	{
		return 0;
	}

	@Override
	public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_)
	{
		// TODO Auto-generated method stub

	}

}
