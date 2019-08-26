package com.clussmanproductions.trafficcontrol.gui;

import com.clussmanproductions.trafficcontrol.ModItems;
import com.clussmanproductions.trafficcontrol.tileentity.SignTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID)
		{
			case GUI_IDs.TRAFFIC_LIGHT_FRAME:
				if (player.getHeldItemMainhand().getItem() == ModItems.traffic_light_frame)
				{
					return new TrafficLightFrameContainer(player.inventory, player.getHeldItemMainhand());
				}
				break;
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		switch(ID)
		{
			case GUI_IDs.SIGN:
				BlockPos pos = new BlockPos(x, y, z);
				TileEntity te = world.getTileEntity(pos);
				if (te instanceof SignTileEntity)
				{
					SignTileEntity signTE = (SignTileEntity)te;
					return new SignGui(signTE, pos);
				}
				break;
			case GUI_IDs.TRAFFIC_LIGHT_FRAME:
				if (player.getHeldItemMainhand().getItem() == ModItems.traffic_light_frame)
				{
					return new TrafficLightFrameGui(player.inventory, player.getHeldItemMainhand());
				}
				break;
		}
		
		return null;
	}

	public static class GUI_IDs
	{
		public static final int SIGN = 1;
		public static final int TRAFFIC_LIGHT_FRAME = 2;
	}
}
