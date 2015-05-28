package com.vapourdrive.magtools.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;

import com.vapourdrive.magtools.blocks.MagSlab;

public class MagItemSlab extends ItemSlab
{

	public MagItemSlab(Block block, MagSlab slab1, MagSlab slab2)
	{
		super(block, slab1, slab2, block == slab2);
	}

}
