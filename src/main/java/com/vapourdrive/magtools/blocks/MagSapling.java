package com.vapourdrive.magtools.blocks;

import java.util.List;
import java.util.Random;

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
import com.vapourdrive.magtools.config.ConfigInfo;
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
		if(random.nextInt(ConfigInfo.SaplingGrowthChanceNatural) == 0)
		{
			ageSapling(world, random, x, y, z);
		}
	}

	public void growTree(World world, Random random, int x, int y, int z)
	{
		Object tree = new WorldGenMagTree(true, false);
		((WorldGenerator)tree).generate(world, random, x, y, z);
	}

	@Override
	public final int damageDropped(int metadata)
	{
		return 0;
	}

	//is bonemeal consumed
	@Override
	public boolean func_149851_a(World world, int x, int y, int z, boolean check)
	{
		return true;
	}

	//can bonemeal activate
	@Override
	public boolean func_149852_a(World world, Random random, int x, int y, int z)
	{
		return true;
	}

	//what to do when bonemealed
	@Override
	public void func_149853_b(World world, Random random, int x, int y, int z)
	{
		if(ConfigInfo.CanBonemealSapling && random.nextInt(ConfigInfo.SaplingGrowthChanceBonemeal) == 0)
		{
			ageSapling(world, random, x, y, z);
		}
	}

	public void ageSapling(World world, Random random, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta < 15)
		{
			meta++;
		}
		
		world.setBlockMetadataWithNotify(x, y, z, meta, 3);
		
		if (meta >= 15)
		{
			growTree(world, random, x, y, z);
		}
	}

}
