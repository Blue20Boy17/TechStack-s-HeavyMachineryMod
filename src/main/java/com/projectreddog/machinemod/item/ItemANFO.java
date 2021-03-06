package com.projectreddog.machinemod.item;

import com.projectreddog.machinemod.block.BlockMachineDrilledStone;
import com.projectreddog.machinemod.init.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemANFO extends ItemMachineMod {
	public String registryName = "anfo";

	public ItemANFO() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {
		// public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {
		ItemStack stack = player.getHeldItem(hand);
		boolean result = false;

		final int MARK_BLOCKS_FOR_UPDATE_FLAG = 2;
		final int NOTIFY_NEIGHBOURS_FLAG = 1;
		if (world.getBlockState(pos).getBlock() == ModBlocks.machinedrilledstone || world.getBlockState(pos).getBlock() == ModBlocks.machinedrilledandesite || world.getBlockState(pos).getBlock() == ModBlocks.machinedrilleddiorite || world.getBlockState(pos).getBlock() == ModBlocks.machinedrilledgranite) {

			EnumFacing ef = (EnumFacing) world.getBlockState(pos).getValue(BlockMachineDrilledStone.FACING);
			if (ef == EnumFacing.DOWN || ef == EnumFacing.UP) {

				BlockPos bottom = null;
				for (int i = 0; i < 17; i++) {
					if (world.getBlockState(pos.down(i)).getBlock() == ModBlocks.machinedrilledstone || world.getBlockState(pos.down(i)).getBlock() == ModBlocks.machinedrilledandesite || world.getBlockState(pos.down(i)).getBlock() == ModBlocks.machinedrilleddiorite || world.getBlockState(pos.down(i)).getBlock() == ModBlocks.machinedrilledgranite) {
						bottom = pos.down(i);
					}
				}
				if (bottom != null) {
					world.setBlockState(bottom, ModBlocks.machineexplosivepackeddrilledstone.getDefaultState(), MARK_BLOCKS_FOR_UPDATE_FLAG | NOTIFY_NEIGHBOURS_FLAG);
					IBlockState state = world.getBlockState(bottom);
					world.notifyBlockUpdate(bottom, state, state, 3);
					result = true;
				}
			} else if (ef == EnumFacing.EAST || ef == EnumFacing.WEST || ef == EnumFacing.NORTH || ef == EnumFacing.SOUTH) {

				BlockPos bottom = null;
				for (int i = 0; i < 9; i++) {
					if (world.getBlockState(pos.offset(ef.getOpposite(), i)).getBlock() == ModBlocks.machinedrilledstone || world.getBlockState(pos.offset(ef.getOpposite(), i)).getBlock() == ModBlocks.machinedrilledandesite || world.getBlockState(pos.offset(ef.getOpposite(), i)).getBlock() == ModBlocks.machinedrilleddiorite
							|| world.getBlockState(pos.offset(ef.getOpposite(), i)).getBlock() == ModBlocks.machinedrilledgranite) {
						bottom = pos.offset(ef.getOpposite(), i);
					}
				}
				if (bottom != null) {
					world.setBlockState(bottom, ModBlocks.machineexplosivepackeddrilledstone.getDefaultState(), MARK_BLOCKS_FOR_UPDATE_FLAG | NOTIFY_NEIGHBOURS_FLAG);
					IBlockState state = world.getBlockState(bottom);
					world.notifyBlockUpdate(bottom, state, state, 3);
					result = true;
				}
			}

		}

		if (result && !player.capabilities.isCreativeMode) {
			stack.setCount(stack.getCount() - 1);
		}
		if (result) {
			return EnumActionResult.PASS;
		} else {
			return EnumActionResult.FAIL;
		}
	}
}
