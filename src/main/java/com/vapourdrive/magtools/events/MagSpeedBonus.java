package com.vapourdrive.magtools.events;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

import com.vapourdrive.magtools.items.MagItems;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MagSpeedBonus
{
	/**
	 * Speeds up the player's ability to harvest Blocks based on their experience while using the magPick
	 * 
	 * @param event
	 */
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void magPickBonus(PlayerEvent.BreakSpeed event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack itemstack = player.getCurrentEquippedItem();
		
		if(itemstack != null)
		{
			float bonus = (float)player.experienceLevel;

			if(itemstack.getItem() == MagItems.MagPick)
			{
				event.newSpeed = event.originalSpeed + (bonus/25);
			}
			else if(itemstack.getItem() == MagItems.MagHatchet)
			{
				Block block = event.block;
				Material material = block.getMaterial();
		        if(material == Material.plants || material == Material.vine || material == Material.coral || material == Material.leaves || material == Material.gourd)
		        {
		        	event.newSpeed = event.originalSpeed + (bonus/5);
		        }
			}
		}
		
		return;
	}
}
