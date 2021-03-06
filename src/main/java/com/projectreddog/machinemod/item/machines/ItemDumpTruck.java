package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityDumpTruck;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDumpTruck extends ItemMachineModMachine {
	public String registryName = "dumptruck";

	// public ModelTransportable mt ;

	public ItemDumpTruck() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;

	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {
		// public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {
		ItemStack stack = player.getHeldItem(hand);
		boolean result = false;

		if (!world.isRemote)// / only run on server
		{
			// LogHelper.info("Item used on dumptruck!");
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			EntityDumpTruck entityDumpTruck = new EntityDumpTruck(world);
			entityDumpTruck.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityDumpTruck.prevPosX = x + .5d;
			entityDumpTruck.prevPosY = y + 1.0d;
			entityDumpTruck.prevPosZ = z + .5d;
			result = world.spawnEntity(entityDumpTruck);
			// LogHelper.info("Spawn entity resutl:" + result );
			if (result && !player.capabilities.isCreativeMode) {
				stack.setCount(stack.getCount() - 1);
			}
		}
		if (result) {
			return EnumActionResult.PASS;
		} else {
			return EnumActionResult.FAIL;
		}
	}
	// @Override
	// public ModelTransportable getModel() {
	// if (mt == null){
	// mt = new ModelDumpTruck();
	// }
	// return mt;
	// }
}
