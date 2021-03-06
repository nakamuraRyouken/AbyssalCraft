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
package com.shinoow.abyssalcraft.integration.jei.upgrades;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.item.ItemUpgradeKit;

public class UpgradeRecipeWrapper extends BlankRecipeWrapper {

	private final ItemStack kit;
	private final ItemStack input;
	private final ItemStack output;

	public UpgradeRecipeWrapper(@Nonnull ItemUpgradeKit kit, @Nonnull ItemStack input, @Nonnull ItemStack output){
		this.kit = new ItemStack(kit);
		this.input = input;
		this.output = output;
	}

	public ItemStack getInput(){
		return input;
	}

	public ItemStack getUpgradeKit(){
		return kit;
	}

	public List<ItemStack> getOutputs(){
		return Collections.singletonList(output);
	}

	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {

	}

	@Override
	public void getIngredients(IIngredients ingredients) {

		List<List<ItemStack>> input = Lists.newArrayList();

		input.add(Collections.singletonList(this.input));
		input.add(Collections.singletonList(kit));

		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}
}
