// Date: 12/24/2014 5:11:17 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package com.projectreddog.machinemod.model.tileentity;

import java.io.IOException;
import java.util.HashMap;

import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelFractionalDistillation extends ModelBase {
	// fields
	public OBJModel objModel;
	private HashMap<String, IFlexibleBakedModel> modelParts;

	public ModelFractionalDistillation() {

		try {
			objModel = (OBJModel) OBJLoader.instance.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/fractionaldistilation.obj"));
			modelParts = MachineModModelHelper.getModelsForGroups(objModel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void renderGroupObject(String groupName) {
		MachineModModelHelper.renderBakedModel(modelParts.get(groupName));

	}

	public void render(TileEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		// super.render(null, f, f1, f2, f3, f4, f5);
		renderGroupObject(MachineModModelHelper.ALL_PARTS);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}

	public ResourceLocation getTexture() {

		return new ResourceLocation("machinemod", Reference.MODEL_FRACTIONAL_DISTILATION_TEXTURE_LOCATION);
	}

}