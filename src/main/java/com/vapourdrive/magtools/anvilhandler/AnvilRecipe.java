package com.vapourdrive.magtools.anvilhandler;

import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;

public class AnvilRecipe implements IAnvilRecipe
{
	public final ItemStack leftItem;
	public final ItemStack rightItem;
	public final ItemStack resultItem;
	public final int cost;
	public final int matCost;

	public AnvilRecipe(ItemStack left, ItemStack right, int MatCost, ItemStack result, int xpCost)
	{
		this.leftItem = left;
		this.rightItem = right;
		this.resultItem = result;
		this.cost = xpCost;
		this.matCost = MatCost;
	}

	@Override
	public boolean matches(ItemStack left, ItemStack right)
	{
		ItemStack Left = this.leftItem;
		ItemStack Right = this.rightItem;
		if (left != null && right != null)
		{
			if (Left == left && Right == right)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public ItemStack getLeftItem()
	{
		return this.leftItem;
	}

	@Override
	public ItemStack getResult()
	{
		return this.resultItem;
	}

	@Override
	public ItemStack getRightItem()
	{
		return this.rightItem;
	}

	public boolean hasOutput(ItemStack tool)
	{
		List<IAnvilRecipe> recipes = AnvilManager.getInstance().getRecipeList();

		Iterator<IAnvilRecipe> iterator = recipes.iterator();

		while (iterator.hasNext())
		{
			IAnvilRecipe recipe = iterator.next();
			ItemStack Result = recipe.getResult();

			if (Result != null)
			{
				if (Result.getItem() == tool.getItem())
				{
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int getCost()
	{
		return this.cost;
	}

	@Override
	public int getMatCost()
	{
		return this.matCost;
	}

}
