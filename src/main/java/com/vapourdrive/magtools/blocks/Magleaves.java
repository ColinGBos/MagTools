package com.vapourdrive.magtools.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;
import com.vapourdrive.magtools.items.MagItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Magleaves extends BlockLeaves implements IShearable
{
	public IIcon FancyLeaves;
	public IIcon FastLeaves;
	public static final String[] field_150133_O = new String[]
	{
		"magleaves"
	};

	public Magleaves()
	{
		super();
		this.setBlockName(MagBlockRef.MagLeaves);
		this.setTickRandomly(true);
		this.setCreativeTab(MagTools.MagCreativeTab);
		this.setHardness(0.2F);
		this.setLightOpacity(1);
		this.setStepSound(soundTypeGrass);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		FancyLeaves = register.registerIcon(Reference.ResourcePath + "MagLeaves");
		FastLeaves = register.registerIcon(Reference.ResourcePath + "MagLeaves_fast");
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return MagItems.MagStick;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune)
	{
		if (world.isRemote)
		{
			return;
		}

		if (world.rand.nextInt(100) == 0)
		{
			Item item = this.getItemDropped(metadata, world.rand, fortune);

			this.dropBlockAsItem(world, x, y, z, new ItemStack(item, 1, this.damageDropped(metadata)));
		}
		else if (world.rand.nextInt(1000) == 0)
		{
			this.dropBlockAsItem(world, x, y, z, new ItemStack(MagBlocks.MagSapling));
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return 0;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return 0;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, 0));
		return ret;
	}

	public String[] func_150125_e()
	{
		return field_150133_O;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		if (Minecraft.getMinecraft().gameSettings.fancyGraphics)
		{
			return this.FancyLeaves;
		}
		return this.FastLeaves;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess acces, int x, int y, int z, int meta)
	{
		return true;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

}
