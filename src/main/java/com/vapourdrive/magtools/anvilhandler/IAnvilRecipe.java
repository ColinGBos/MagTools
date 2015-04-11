package com.vapourdrive.magtools.anvilhandler;

import net.minecraft.item.ItemStack;

public interface IAnvilRecipe
{
	boolean matches(ItemStack handle, ItemStack toolhead);
	
	ItemStack getLeftItem();
	ItemStack getResult();
	ItemStack getRightItem();
	int getCost();
	int getMatCost();
}
