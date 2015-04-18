package com.vapourdrive.magtools.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import com.vapourdrive.magtools.blocks.MagBlocks;

public class WorldGenMagTree extends WorldGenAbstractTree
{
	public boolean hasGems;
	public WorldGenMagTree(boolean doNotify, boolean hasgems)
	{
		super(doNotify);
		hasGems = hasgems;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		if (checkGenQuality(world, x, y, z) == false)
		{
			return false;
		}
		else
		{
			genBase(world, x, y, z, hasGems);
			genSecondHalf(world, x, y + 6, z, hasGems);
			genBranches(world, x, y + 6, z);
		}
		return true;
	}

	public boolean checkGenQuality(World world, int x, int y, int z)
	{
		if (checkAirSpace(world, x, y, z) && checkLevelGround(world, x, y, z))
		{
			return true;
		}

		return false;

	}

	public void genBase(World world, int x, int y, int z, boolean hasGems)
	{
		for (int i = -1; i <= 1; i++)
		{
			for (int j = -1; j <= 1; j++)
			{
				for (int k = 0; k <= 5; k++)
				{
					if (i == 0 && j == 0)
					{
						placeGemChance(world, x + i, y + k, z + j, hasGems);
					}

					else
						world.setBlock(x + i, y + k, z + j, MagBlocks.MagLog, 0, 3);

					if (k == 0)
					{
						fillGroundGap(world, x + i, y - 1, z + j);
					}
				}
			}
		}

		for (int k = 0; k <= 3; k++)
		{
			world.setBlock(x + 2, y + k, z, MagBlocks.MagLog, 0, 3);
			world.setBlock(x - 2, y + k, z, MagBlocks.MagLog, 0, 3);
			world.setBlock(x, y + k, z + 2, MagBlocks.MagLog, 0, 3);
			world.setBlock(x, y + k, z - 2, MagBlocks.MagLog, 0, 3);
		}

		world.setBlock(x + 3, y, z, MagBlocks.MagLog, 4, 3);
		world.setBlock(x - 3, y, z, MagBlocks.MagLog, 4, 3);
		world.setBlock(x, y, z + 3, MagBlocks.MagLog, 8, 3);
		world.setBlock(x, y, z - 3, MagBlocks.MagLog, 8, 3);

		fillGroundGap(world, x + 3, y - 1, z);
		fillGroundGap(world, x - 3, y - 1, z);
		fillGroundGap(world, x, y - 1, z + 3);
		fillGroundGap(world, x, y - 1, z - 3);
		fillGroundGap(world, x + 2, y - 1, z);
		fillGroundGap(world, x - 2, y - 1, z);
		fillGroundGap(world, x, y - 1, z + 2);
		fillGroundGap(world, x, y - 1, z - 2);

		return;
	}

	public void genSecondHalf(World world, int x, int y, int z, boolean hasGems)
	{
		for (int i = 0; i <= 12; i++)
		{
			world.setBlock(x + 1, y + i, z, MagBlocks.MagLog, 0, 3);
			world.setBlock(x - 1, y + i, z, MagBlocks.MagLog, 0, 3);
			world.setBlock(x, y + i, z + 1, MagBlocks.MagLog, 0, 3);
			world.setBlock(x, y + i, z - 1, MagBlocks.MagLog, 0, 3);
			placeGemChance(world, x, y + i, z, hasGems);
		}
		for (int i = 0; i <= 2; i++)
		{
			world.setBlock(x, y + i + 12, z, MagBlocks.MagLog, 0, 3);
		}
		genLeafNode(world, x, y + 14, z);

	}

	public void genBranches(World world, int x, int y, int z)
	{
		planBranch(world, x + 2, y, z, 1, 0);
		planBranch(world, x - 2, y, z, -1, 0);
		planBranch(world, x, y, z + 2, 0, 1);
		planBranch(world, x, y, z - 2, 0, -1);
		planBranch(world, x + 1, y, z + 1, 1, 1);
		planBranch(world, x - 1, y, z - 1, -1, -1);
		planBranch(world, x - 1, y, z + 1, -1, 1);
		planBranch(world, x + 1, y, z - 1, 1, -1);
	}

	public void planBranch(World world, int x, int y, int z, int xWalk, int zWalk)
	{
		for (int i = 0; i <= 12; i++)
		{
			if (world.rand.nextInt(5) > 2)
			{
				int length = (14 - i) / 4;
				if (length <= 0)
				{
					length = 1;
				}

				genBranch(world, x, y + i, z, xWalk, zWalk, length);

				i++;
			}
		}
	}

	public void genBranch(World world, int x, int y, int z, int xWalk, int zWalk, int length)
	{
		int meta = 8;
		if (xWalk != 0 && zWalk == 0)
		{
			meta = 4;
		}
		if (xWalk != 0 && zWalk != 0)
		{
			if (world.rand.nextFloat() >= 0.5f)
			{
				meta = 4;
			}
		}

		for (int i = 0; i <= length; i++)
		{
			world.setBlock(x + (xWalk * i), y, z + (zWalk * i), MagBlocks.MagLog, meta, 3);
		}

		genLeafNode(world, x + (xWalk * length), y, z + (zWalk * length));
	}

	public void genLeafNode(World world, int x, int y, int z)
	{
		int radius = 0;
		for (int i = -2; i <= 2; i++)
		{
			for (int j = -2; j <= 2; j++)
			{
				for (int k = -1; k <= 1; k++)
				{
					radius = (i * i + j * j + k * k + 1);
					if (world.rand.nextInt(radius) < 3 && radius < 16)
					{
						Block block = world.getBlock(x + i, y + k, z + j);
						if (block.canBeReplacedByLeaves(world, x + i, y + k, z + j))
						{
							world.setBlock(x + i, y + k, z + j, MagBlocks.MagLeaves, 0, 3);
						}
					}
				}
			}
		}

	}

	public void fillGroundGap(World world, int x, int y, int z)
	{
		for (int i = 0; i >= -4; i--)
		{
			Block block = world.getBlock(x, y + i, z);
			if (block.canBeReplacedByLeaves(world, x, y + i, z))
			{
				world.setBlock(x, y + i, z, MagBlocks.MagLog, 0, 3);
			}
			else
				i = -5;
		}
	}

	public void placeGemChance(World world, int x, int y, int z, boolean hasGems)
	{
		int meta = 0;
		if (world.rand.nextInt(4) == 0 && hasGems)
		{
			meta = 1;
		}
		world.setBlock(x, y, z, MagBlocks.MagLog, meta, 3);

	}

	public boolean checkLevelGround(World world, int x, int y, int z)
	{
		int groundLevelThreshold = 0;

		for (int i = -2; i <= 2; i++)
		{
			for (int j = -2; j <= 2; j++)
			{
				int height = world.getHeightValue(x + i, z + j);
				if (height < y)
				{
					groundLevelThreshold = groundLevelThreshold + (groundLevelThreshold - height);
				}

				Material material = world.getBlock(x + i, height - 1, z + j).getMaterial();
				if (material != Material.ground && material != Material.grass && material != Material.air)
				{
					return false;
				}
			}
		}

		if (groundLevelThreshold > 8)
		{
			return false;
		}

		return true;
	}

	public boolean checkAirSpace(World world, int x, int y, int z)
	{
		for (int i = -6; i <= 6; i++)
		{
			for (int j = -6; j <= 6; j++)
			{
				for (int k = 1; k <= 24; k++)
				{
					Block block = world.getBlock(i + x, k + y, j + z);
					if (!block.canBeReplacedByLeaves(world, x, y, z))
					{
						return false;
					}
				}
			}
		}
		return true;
	}

}
