package com.vapourdrive.magtools.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;
import com.vapourdrive.magtools.items.MagItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MagLog extends BlockLog
{
    public static final String[] variations = new String[] {"reg", "gem"};

	protected MagLog()
	{
		this.setCreativeTab(MagTools.MagCreativeTab);
		this.setBlockName(MagBlockRef.MagTreeLog);
	}
	
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(item, 1, 0));
        //list.add(new ItemStack(item, 1, 1));
    }
    
    public int damageDropped(int meta)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.field_150167_a = new IIcon[variations.length];
        this.field_150166_b = new IIcon[variations.length];

        for (int i = 0; i < this.field_150167_a.length; ++i)
        {
            this.field_150167_a[i] = register.registerIcon(Reference.ResourcePath + MagBlockRef.MagTreeLog + "_" + variations[i]);
            this.field_150166_b[i] = register.registerIcon(Reference.ResourcePath + MagBlockRef.MagTreeLog + "_top");
        }
    }
    
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);
		
		int dropCount = world.rand.nextInt(2 + fortune) + 1;

		ItemStack stack = new ItemStack(MagItems.MagGem, dropCount);

		if (stack != null && (metadata + 3) % 2 == 0)
		{
			ret.add(stack);
		}
		return ret;
	}
	
	@Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        return false;
    }
}
