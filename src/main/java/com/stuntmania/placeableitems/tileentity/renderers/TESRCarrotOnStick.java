package com.stuntmania.placeableitems.tileentity.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TECarrotOnStick;

public class TESRCarrotOnStick extends TileEntitySpecialRenderer {
	

	IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "obj/carrot_on_stick.obj"));
	ResourceLocation texture = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/carrot_on_stick.png");
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_) {
		TECarrotOnStick facedEntity = (TECarrotOnStick) entity;
		
		bindTexture(texture);
		
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.0F, (float) z + 0.5F);
		GL11.glScalef(1, 1, 1);
		
		int facing = facedEntity.getFacing();
		int k = 0;
		k = facing * 90;
		GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
		
		model.renderAll();
		GL11.glPopMatrix();
	}
}