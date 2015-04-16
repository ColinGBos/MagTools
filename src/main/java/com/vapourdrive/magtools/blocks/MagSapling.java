package com.vapourdrive.magtools.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;
import com.vapourdrive.magtools.world.feature.WorldGenMagTree;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MagSapling extends BlockSapling
{

	public MagSapling()
	{
		super();
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
	// TODO: updateTick()
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (!world.isRemote)
		{
			if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0)
			{
				if (meta >= 15)
				{
					this.func_149878_d(world, x, y, z, random);
				}
				else
				{
					world.setBlockMetadataWithNotify(x, y, z, meta + 1, 3);
				}
			}
		}
	}

	@Override
	public final void func_149878_d(World world, int x, int y, int z, Random rand)
	{
		if (!TerrainGen.saplingGrowTree(world, rand, x, y, z))
		{
			return;
		}

		int meta = world.getBlockMetadata(x, y, z);

		final WorldGenerator treeGen = new WorldGenMagTree(true);

		world.setBlock(x, y, z, Blocks.air, 0, 4);

		if (!treeGen.generate(world, rand, x, y, z))
		{
			world.setBlock(x, y, z, this, meta, 4);
		}
	}

	@Override
	public final int damageDropped(int metadata)
	{
		return 0;
	}

}
