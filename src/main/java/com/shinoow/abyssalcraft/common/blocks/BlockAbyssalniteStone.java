/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2017 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import com.shinoow.abyssalcraft.api.block.ACBlocks;

public class BlockAbyssalniteStone extends BlockACBasic {

	public BlockAbyssalniteStone() {
		super(Material.ROCK, "pickaxe", 4, 2.5F, 20.0F, SoundType.STONE, MapColor.PURPLE);
		setUnlocalizedName("abydreadstone");
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int j)
	{
		return Item.getItemFromBlock(ACBlocks.abyssalnite_cobblestone);
	}
}
