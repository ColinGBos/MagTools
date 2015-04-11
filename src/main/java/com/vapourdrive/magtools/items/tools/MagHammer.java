package com.vapourdrive.magtools.items.tools;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

import com.vapourdrive.magtools.MagTools;
import com.vapourdrive.magtools.Reference;
import com.vapourdrive.magtools.items.MagItemRef;
import com.vapourdrive.magtools.utils.RandomUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MagHammer extends ItemPickaxe
{

	public MagHammer(ToolMaterial material)
	{
		super(material);
		this.setUnlocalizedName(MagItemRef.MagHammerName);
		this.setCreativeTab(MagTools.MagCreativeTab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(Reference.ResourcePath + MagItemRef.MagHammerName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("phrase.magtools.hammerinfo"));
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
	{
		World world = player.worldObj;
		Block block = world.getBlock(x, y, z);

		MovingObjectPosition object = RandomUtils.raytraceFromEntity(world, player, false, 4.5D);

		if (object == null)
		{
			return super.onBlockDestroyed(stack, world, block, x, y, z, player);
		}

		int side = object.sideHit;
		int xmove = 0;
		int ymove = 0;
		int zmove = 0;

		if (side == 0 || side == 1)
		{
			xmove = 1;
			zmove = 1;
		}
		else
		{
			ymove = 1;
			if (side == 4 || side == 5)
			{
				zmove = 1;
			}
			else
			{
				xmove = 1;
			}
		}

		float strength = ForgeHooks.blockStrength(block, player, world, x, y, z);

		if(player.isSneaking() && (player.experienceLevel >= 20 || player.capabilities.isCreativeMode))
		{
			checkBlockBreak(world, player, x, y, z, stack, strength, block, side);
		}
		
		else
		{
			for (int i = -xmove; i <= xmove; i++)
			{
				for (int j = -ymove; j <= ymove; j++)
				{
					for (int k = -zmove; k <= zmove; k++)
					{
						if (i != x && j != y && k != z)
						{
							checkBlockBreak(world, player, x + i, y + j, z + k, stack, strength, block, side);
						}
					}
				}
			}
		}

		return false;
	}

	public void checkBlockBreak(World world, EntityPlayer player, int x, int y, int z, ItemStack stack, float strength,
			Block originalBlock, int side)
	{
		Block breakBlock = world.getBlock(x, y, z);

		if (this.canHarvestBlock(breakBlock, stack))
		{
			float newStrength = ForgeHooks.blockStrength(breakBlock, player, world, x, y, z);
			Material material = originalBlock.getMaterial();

			if (newStrength > 0f && strength / newStrength <= 10f && breakBlock.getMaterial() == material)
			{
				breakBlock(world, breakBlock, x, y, z, side, player);

				if ((double) breakBlock.getBlockHardness(world, x, y, z) != 0.0D)
				{
					stack.damageItem(1, player);
				}
			}
		}

	}

	public boolean breakBlock(World world, Block block, int x, int y, int z, int side, EntityPlayer player)
	{
		if (world.isAirBlock(x, y, z))
		{
			return false;
		}

		EntityPlayerMP playerMP = null;

		if (player instanceof EntityPlayerMP)
		{
			playerMP = (EntityPlayerMP) player;
		}

		int meta = world.getBlockMetadata(x, y, z);
		if (block.getHarvestTool(meta) != "pickaxe" || !canHarvestBlock(block, player.getCurrentEquippedItem())
				|| !ForgeHooks.canHarvestBlock(block, player, meta))
		{
			return false;
		}

		if (playerMP != null)
		{
			BreakEvent event = ForgeHooks.onBlockBreakEvent(world, playerMP.theItemInWorldManager.getGameType(), playerMP, x, y, z);

			int drop = event.getExpToDrop();
			block.dropXpOnBlockBreak(world, x, y, z, drop);
			world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) | (world.getBlockMetadata(x, y, z) << 12));

			if (event.isCanceled())
			{
				return false;
			}
		}

		if (player.capabilities.isCreativeMode)
		{
			if (!world.isRemote)
			{
				block.onBlockHarvested(world, x, y, z, meta, player);
			}

			if (block.removedByPlayer(world, player, x, y, z, false))
			{
				block.onBlockDestroyedByPlayer(world, x, y, z, meta);
			}

			if (!world.isRemote)
			{
				playerMP.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
			}
			else
			{
				Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging(2, x, y, z, side));
			}

			return true;
		}

		if (!world.isRemote)
		{
			block.onBlockHarvested(world, x, y, z, meta, player);

			if (block.removedByPlayer(world, player, x, y, z, true))
			{
				block.onBlockDestroyedByPlayer(world, x, y, z, meta);
				if (!player.capabilities.isCreativeMode)
				{
					block.harvestBlock(world, player, x, y, z, meta);
				}
			}

			playerMP.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
		}

		else
		{
			if (block.removedByPlayer(world, player, x, y, z, true))
			{
				block.onBlockDestroyedByPlayer(world, x, y, z, meta);
			}

			Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging(2, x, y, z, side));
		}
		return true;
	}

}
