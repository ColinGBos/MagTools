package com.vapourdrive.magtools.world;

import java.util.Random;

import com.vapourdrive.magtools.world.feature.WorldGenMagTree;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class MagWorld implements IWorldGenerator
{
	private WorldGenerator MagTreeGenerator;

	public MagWorld()
	{
		GameRegistry.registerWorldGenerator(this, 0);

		MagTreeGenerator = new WorldGenMagTree(false, true);
	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		int xChunk = chunkX * 16 + rand.nextInt(16);
		int zChunk = chunkZ * 16 + rand.nextInt(16);
		int chx = xChunk + rand.nextInt(16);
		int chz = zChunk + rand.nextInt(16);

		BiomeGenBase Biome = world.getBiomeGenForCoords(chx, chz);

		if (isCorrectBiome(Biome) && rand.nextInt(50) == 0)
		{
			int height = world.getHeightValue(chx, chz);
			MagTreeGenerator.generate(world, rand, chx, height, chz);
		}

	}

	public boolean isCorrectBiome(BiomeGenBase biome)
	{
		if (BiomeDictionary.isBiomeOfType(biome, Type.SAVANNA) || BiomeDictionary.isBiomeOfType(biome, Type.PLAINS)
				|| BiomeDictionary.isBiomeOfType(biome, Type.MOUNTAIN) || BiomeDictionary.isBiomeOfType(biome, Type.FOREST))
		{
			return true;
		}
		return false;
	}
}
