package com.vapourdrive.magtools.blocks;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class MagSlab extends BlockSlab
{

	public MagSlab(boolean isDoubleSlab, Block block)
	{
		super(isDoubleSlab, Material.wood);
		this.setCreativeTab(MagTools.MagCreativeTab);
		this.setHardness(2.0f);
		this.setResistance(2.0f);
		this.setStepSound(soundTypeWood);
		this.textureName = Reference.ResourcePath + MagBlockRef.MagPlank;
		this.setBlockName(MagBlockRef.MagSlab);
	}

	@Override
	public String func_150002_b(int meta)
	{
		return "MagnanimousSlab";
	}

}
