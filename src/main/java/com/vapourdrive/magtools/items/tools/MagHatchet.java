package com.vapourdrive.magtools.items.tools;

import java.text.DecimalFormat;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;
import com.vapourdrive.magtools.items.MagItemRef;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MagHatchet extends ItemAxe
{

	public MagHatchet(ToolMaterial material)
	{
		super(material);
		this.setUnlocalizedName(MagItemRef.MagHatchetName);
		this.setCreativeTab(MagTools.MagCreativeTab);	
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(Reference.ResourcePath + MagItemRef.MagHatchetName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add(EnumChatFormatting.GREEN + "Chops leaves " + new DecimalFormat("#.##").format(1 + player.experienceLevel/5) + " faster");
		list.add(EnumChatFormatting.GREEN + "Also doesn't take damage when breaking plants");
	}
	
	@Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity)
    {
        if ((double)block.getBlockHardness(world, x, y, z) != 0.0D)
        {
        	Material material = block.getMaterial();
        	if(!(material == Material.plants || material == Material.vine || material == Material.coral || material == Material.leaves || material == Material.gourd))
        	{
        		stack.damageItem(1, entity);
        	}
        }

        return true;
    }

}
