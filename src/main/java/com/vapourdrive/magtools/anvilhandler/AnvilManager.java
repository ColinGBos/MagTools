package com.vapourdrive.magtools.anvilhandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.vapourdrive.magtools.items.MagItems;

public class AnvilManager
{
	private static final AnvilManager instance = new AnvilManager();
	public List recipes = new ArrayList();

	public AnvilManager()
	{
		this.addItemRecipe(MagItems.MagBlade, MagItems.MagHandle, 1, MagItems.MagSword, 10);
		this.addItemRecipe(MagItems.MagHammerHead, MagItems.MagHandle, 1, MagItems.MagHammer, 10);
		this.addItemRecipe(MagItems.MagPickHead, MagItems.MagHandle, 1, MagItems.MagPick, 10);
		this.addItemRecipe(MagItems.MagEarthMoverHead, MagItems.MagHandle, 1, MagItems.MagEarthMover, 10);
	}

	public static AnvilManager getInstance()
	{
		return instance;
	}

	public void addItemRecipe(Item left, Item right, int matCost, Item Tool, int Cost)
	{
		this.addRecipe(new ItemStack(left, 1, 0), new ItemStack(right, 1, 0), matCost, new ItemStack(Tool, 1, 0), Cost);
	}

	public AnvilRecipe addRecipe(ItemStack left, ItemStack right, int matCost, ItemStack Tool, int Cost)
	{
		AnvilRecipe anvilrecipe = new AnvilRecipe(left, right, matCost, Tool, Cost);

		this.recipes.add(anvilrecipe);
		return anvilrecipe;
	}

	public ItemStack getValidRecipe(ItemStack left, ItemStack right)
	{
		List<IAnvilRecipe> recipes = AnvilManager.getInstance().getRecipeList();

		Iterator<IAnvilRecipe> iterator = recipes.iterator();

		while (iterator.hasNext())
		{
			IAnvilRecipe recipe = iterator.next();
			ItemStack Left = recipe.getLeftItem();
			ItemStack Right = recipe.getRightItem();

			if (Left != null && Right != null)
			{
				if (Left.getItem() == left.getItem() && Right.getItem() == right.getItem())
				{
					if (recipe.getResult() != null)
					{
						return recipe.getResult();
					}
				}
			}
		}

		return null;
	}

	public int getCost(ItemStack left, ItemStack right)
	{
		List<IAnvilRecipe> recipes = AnvilManager.getInstance().getRecipeList();

		Iterator<IAnvilRecipe> iterator = recipes.iterator();

		while (iterator.hasNext())
		{
			IAnvilRecipe recipe = iterator.next();
			ItemStack Left = recipe.getLeftItem();
			ItemStack Right = recipe.getRightItem();

			if (Left != null && Right != null)
			{
				if (Left.getItem() == left.getItem() && Right.getItem() == right.getItem())
				{
					if (recipe.getResult() != null)
					{
						return recipe.getCost();
					}
				}
			}
		}

		return 1;
	}

	public int getMatCost(ItemStack left, ItemStack right)
	{
		List<IAnvilRecipe> recipes = AnvilManager.getInstance().getRecipeList();

		Iterator<IAnvilRecipe> iterator = recipes.iterator();

		while (iterator.hasNext())
		{
			IAnvilRecipe recipe = iterator.next();
			ItemStack Left = recipe.getLeftItem();
			ItemStack Right = recipe.getRightItem();

			if (Left != null && Right != null)
			{
				if (Left.getItem() == left.getItem() && Right.getItem() == right.getItem())
				{
					if (recipe.getResult() != null)
					{
						return recipe.getMatCost();
					}
				}
			}
		}

		return 1;
	}

	public List getRecipeList()
	{
		return this.recipes;
	}
}
