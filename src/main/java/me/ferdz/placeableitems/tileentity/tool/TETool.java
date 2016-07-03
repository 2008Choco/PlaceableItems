package me.ferdz.placeableitems.tileentity.tool;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TETool extends TileEntity {

	private ItemStack tool;

	public TETool() {
		tool = new ItemStack(Items.WOODEN_AXE);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		tool.readFromNBT(nbttagcompound.getCompoundTag("tool"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		NBTTagCompound nbt2 = tool.writeToNBT(new NBTTagCompound());
		nbttagcompound.setTag("tool", nbt2);
		return nbttagcompound;
	}
	
	/*
	 * Generic synchronisation code
	 */
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = super.getUpdateTag();
		return writeToNBT(tag);
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		super.handleUpdateTag(tag);
		readFromNBT(tag);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag = writeToNBT(tag);
		return new SPacketUpdateTileEntity(this.pos, this.getBlockMetadata(), tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		IBlockState state = worldObj.getBlockState(pos);
		readFromNBT(pkt.getNbtCompound());
		IBlockState newState = worldObj.getBlockState(pos);
		worldObj.notifyBlockUpdate(pos, state, newState, 2);
	}

	/*
	 * Get/set
	 */
	public ItemStack getTool() {
		return this.tool;
	}

	public void setTool(ItemStack tool) {
		this.tool = tool;
	}
}
