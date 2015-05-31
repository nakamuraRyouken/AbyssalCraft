/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2015 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.common.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.common.blocks.BlockCLiquid;

public class WorldGenAbyLake extends WorldGenerator
{
	private Block blockIndex;

	public WorldGenAbyLake(Block par1)
	{
		blockIndex = par1;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		int n = par4;
		par3 -= 8;

		for (par5 -= 8; par4 > 5 && par1World.isAirBlock(par3, par4, par5); --par4)
			;

		if (par4 <= 4)
			return false;
		else
		{
			par4 -= 4;
			boolean[] aboolean = new boolean[2048];
			int l = par2Random.nextInt(4) + 4;
			int i1;

			for (i1 = 0; i1 < l; ++i1)
			{
				double d0 = par2Random.nextDouble() * 6.0D + 3.0D;
				double d1 = par2Random.nextDouble() * 4.0D + 2.0D;
				double d2 = par2Random.nextDouble() * 6.0D + 3.0D;
				double d3 = par2Random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
				double d4 = par2Random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
				double d5 = par2Random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

				for (int j1 = 1; j1 < 15; ++j1)
					for (int k1 = 1; k1 < 15; ++k1)
						for (int l1 = 1; l1 < 7; ++l1)
						{
							double d6 = (j1 - d3) / (d0 / 2.0D);
							double d7 = (l1 - d4) / (d1 / 2.0D);
							double d8 = (k1 - d5) / (d2 / 2.0D);
							double d9 = d6 * d6 + d7 * d7 + d8 * d8;

							if (d9 < 1.0D)
								aboolean[(j1 * 16 + k1) * 8 + l1] = true;
						}
			}

			int i2;
			int j2;
			boolean flag;

			for (i1 = 0; i1 < 16; ++i1)
				for (j2 = 0; j2 < 16; ++j2)
					for (i2 = 0; i2 < 8; ++i2)
					{
						flag = !aboolean[(i1 * 16 + j2) * 8 + i2] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + i2] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + i2] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + i2] || j2 > 0 && aboolean[(i1 * 16 + j2 - 1) * 8 + i2] || i2 < 7 && aboolean[(i1 * 16 + j2) * 8 + i2 + 1] || i2 > 0 && aboolean[(i1 * 16 + j2) * 8 + i2 - 1]);

						if (flag)
						{
							Material material = par1World.getBlock(par3 + i1, par4 + i2, par5 + j2).getMaterial();

							if (i2 >= 4 && material.isLiquid())
								return false;

							if (i2 < 4 && !material.isSolid() && par1World.getBlock(par3 + i1, par4 + i2, par5 + j2) != blockIndex)
								return false;
						}
					}

			for (i1 = 0; i1 < 16; ++i1)
				for (j2 = 0; j2 < 16; ++j2)
					for (i2 = 0; i2 < 8; ++i2)
						if (aboolean[(i1 * 16 + j2) * 8 + i2])
							par1World.setBlock(par3 + i1, par4 + i2, par5 + j2, i2 >= 4 ? Blocks.air : blockIndex, 0, 2);

			for (i1 = 0; i1 < 16; ++i1)
				for (j2 = 0; j2 < 16; ++j2)
					for (i2 = 4; i2 < 8; ++i2)
						if (aboolean[(i1 * 16 + j2) * 8 + i2] && par1World.getBlock(par3 + i1, par4 + i2 - 1, par5 + j2) == AbyssalCraft.abystone && par1World.getSavedLightValue(EnumSkyBlock.Sky, par3 + i1, par4 + i2, par5 + j2) > 0)
							if(n <= 32)
								par1World.setBlock(par3 + i1, par4 + i2 - 1, par5 + j2, AbyssalCraft.AbyLCorOre, 0, 2);
							else par1World.setBlock(par3 + i1, par4 + i2 - 1, par5 + j2, AbyssalCraft.abystone, 0, 2);

			if (blockIndex.getMaterial() == BlockCLiquid.Cwater)
				for (i1 = 0; i1 < 16; ++i1)
					for (j2 = 0; j2 < 16; ++j2)
						for (i2 = 0; i2 < 8; ++i2)
						{
							flag = !aboolean[(i1 * 16 + j2) * 8 + i2] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + i2] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + i2] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + i2] || j2 > 0 && aboolean[(i1 * 16 + j2 - 1) * 8 + i2] || i2 < 7 && aboolean[(i1 * 16 + j2) * 8 + i2 + 1] || i2 > 0 && aboolean[(i1 * 16 + j2) * 8 + i2 - 1]);

							if (flag && (i2 < 4 || par2Random.nextInt(2) != 0) && par1World.getBlock(par3 + i1, par4 + i2, par5 + j2).getMaterial().isSolid())
								if(n <= 32)
									par1World.setBlock(par3 + i1, par4 + i2, par5 + j2, AbyssalCraft.AbyLCorOre, 0, 2);
								else par1World.setBlock(par3 + i1, par4 + i2, par5 + j2, AbyssalCraft.abystone, 0, 2);
						}

			return true;
		}
	}
}
