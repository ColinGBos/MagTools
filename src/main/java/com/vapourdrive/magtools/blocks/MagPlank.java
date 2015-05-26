package com.vapourdrive.magtools.blocks;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class MagPlank extends Block
{

	public MagPlank()
	{
		super(Material.wood);
		this.setHardness(2.0f);
		this.setResistance(5.0f);
		this.setStepSound(soundTypeWood);
		this.setCreativeTab(MagTools.MagCreativeTab);
		this.textureName = Reference.ResourcePath + MagBlockRef.MagPlank;
		this.setBlockName(MagBlockRef.MagPlank);
	}

}
