package com.vapourdrive.magtools.blocks;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

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
		this.useNeighborBrightness = true;
	}

	@Override
	public String func_150002_b(int meta)
	{
		return "tile.MagSlab";
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
