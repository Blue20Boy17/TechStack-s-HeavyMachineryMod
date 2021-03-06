package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityConduit extends TileEntity implements ITickable {

	private int MAX_ENERGY_STORAGE = 100;
	private int MAX_ENERGY_RECEIVE = 100;
	private int MAX_ENERGY_EXTRACT = 100;
	private EnergyStorage energyStroage = new EnergyStorage(MAX_ENERGY_STORAGE, MAX_ENERGY_RECEIVE, MAX_ENERGY_EXTRACT, 0);

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return (T) energyStroage;
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void update() {
		if (!this.world.isRemote) {
			// server

			if (this.energyStroage.getEnergyStored() > 0) {
				LogHelper.info("conduit at : " + this.pos + " I Have : " + this.energyStroage.getEnergyStored());
			}
			this.transferPower(this.pos.up(), EnumFacing.DOWN);
			this.transferPower(this.pos.down(), EnumFacing.UP);

			this.transferPower(this.pos.north(), EnumFacing.SOUTH);
			this.transferPower(this.pos.south(), EnumFacing.NORTH);

			this.transferPower(this.pos.east(), EnumFacing.WEST);
			this.transferPower(this.pos.west(), EnumFacing.EAST);

		}
	}

	private void transferPower(BlockPos bp, EnumFacing ef) {
		TileEntity te = this.world.getTileEntity(bp);
		if (te != null) {
			if (te.hasCapability(CapabilityEnergy.ENERGY, ef)) {
				IEnergyStorage remoteES = te.getCapability(CapabilityEnergy.ENERGY, ef);

				int maxTransferExtract = this.energyStroage.extractEnergy(this.energyStroage.getEnergyStored(), true);
				int maxRemoteTransferIn = remoteES.receiveEnergy(maxTransferExtract, true);

				this.energyStroage.extractEnergy(maxRemoteTransferIn, false);
				remoteES.receiveEnergy(maxRemoteTransferIn, false);
				if (maxRemoteTransferIn > 0) {
					LogHelper.info("conduit at : " + this.pos + " I just transfered: " + maxRemoteTransferIn + " to " + bp + " on " + ef);
				}

			}
		}

	}

	// NBT data
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("Energy", this.energyStroage.getEnergyStored());

		return super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("Energy")) {
			this.energyStroage = new EnergyStorage(MAX_ENERGY_STORAGE, MAX_ENERGY_RECEIVE, MAX_ENERGY_EXTRACT, nbt.getInteger("Energy"));
		}
	}
}
