package com.vapourdrive.magtools.blocks;

import com.vapourdrive.magtools.MagTools;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class MagStairs extends BlockStairs
{
	public MagStairs(Block block)
	{
		super(block, 0);
		this.setHardness(2.0f);
		this.setResistance(5.0f);
		this.setStepSound(soundTypeWood);
		this.setCreativeTab(MagTools.MagCreativeTab);
		this.setBlockName(MagBlockRef.MagStairs);
		this.useNeighborBrightness = true;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

}
