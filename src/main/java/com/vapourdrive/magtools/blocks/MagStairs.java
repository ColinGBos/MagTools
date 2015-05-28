package com.vapourdrive.magtools.blocks;

import com.vapourdrive.magtools.MagTools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.world.IBlockAccess;

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
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getMixedBrightnessForBlock(IBlockAccess access, int x, int y, int z)
    {
        return MagBlocks.MagPlank.getMixedBrightnessForBlock(access, x, y, z);
    }

}
