package com.vapourdrive.magtools.events;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;

import com.vapourdrive.magtools.anvilhandler.AnvilManager;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MagAnvilEvent
{
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void anvilEvent(AnvilUpdateEvent event)
	{
		ItemStack leftInput = event.left;
		ItemStack rightInput = event.right;
		int xpCost;
		int matCost;

		if (leftInput == null || rightInput == null)
		{
			return;
		}

		ItemStack Output = AnvilManager.getInstance().getValidRecipe(leftInput, rightInput);

		if (Output != null)
		{
			xpCost = AnvilManager.getInstance().getCost(leftInput, rightInput);
			matCost = AnvilManager.getInstance().getMatCost(leftInput, rightInput);

			if (xpCost <= 0)
			{
				xpCost = 1;
			}

			event.materialCost = matCost;
			event.cost = xpCost;
			event.output = Output;
		}
	}
}
