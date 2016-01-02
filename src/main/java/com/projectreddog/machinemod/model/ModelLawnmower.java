// Date: 12/24/2014 5:11:17 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3i;
import net.minecraftforge.client.model.Attributes;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.client.model.pipeline.LightUtil;

public class ModelLawnmower extends ModelTransportable {
	// fields
	// private IModelCustom myModel;
	private OBJModel myModel;
	IFlexibleBakedModel ibakedmodel;

	Tessellator tessellator;
	WorldRenderer worldrenderer;
	VertexFormat vFmt;

	List<BakedQuad> allQuads;
	Vec3i vec3i;
	public static final ResourceLocation MODEL_TEXTURE = new ResourceLocation("machinemod", Reference.MODEL_LAWNMOWER_TEXTURE_LOCATION);

	public ModelLawnmower() {

		// myModel = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/lawnmower.obj"));
		try {
			myModel = (OBJModel) OBJLoader.instance.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/cube.obj"));
			// myModel = (OBJModel) OBJLoader.instance.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/lawnmower.obj"));
			Function<ResourceLocation, TextureAtlasSprite> textureGetter;
			textureGetter = new Function<ResourceLocation, TextureAtlasSprite>() {
				public TextureAtlasSprite apply(ResourceLocation location) {
					return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());

				}
			};

			// possible example of how to apply texteure?
			// IModel manaModel = ((OBJModel) myModel.retexture(ImmutableMap.of("#lawnmower", "machinemod:model/lawnmower"))).process(ImmutableMap.of("flip-v", "true"));
			IModel texturedModel = ((OBJModel) myModel.retexture(ImmutableMap.of("#lawnmower", "machinemod:model/lawnmower")));

			ibakedmodel = texturedModel.bake(myModel.getDefaultState(), Attributes.DEFAULT_BAKED_FORMAT, textureGetter);
			allQuads = ibakedmodel.getGeneralQuads();
			vFmt = ibakedmodel.getFormat();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// casinoTexture = new ResourceLocation("modid",
		tessellator = Tessellator.getInstance();
		worldrenderer = tessellator.getWorldRenderer();
	}

	public void renderGroupObject(String groupName) {
		// myModel.renderPart(groupName);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		// super.render(entity, f, f1, f2, f3, f4, f5);
		// myModel.renderAll();
		// will now call rendering for each individual object
		// this.renderGroupObject("Tractor_Cube.001");

		int i = 0;
		// long start = System.currentTimeMillis();

		worldrenderer.begin(GL11.GL_QUADS, vFmt);
		// 1.8 worldrenderer.startDrawingQuads();
		// 1.8 worldrenderer.setVertexFormat(DefaultVertexFormats.ITEM);
		// worldrenderer.func_181668_a(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
		// this.renderManager.renderEngine.bindTexture(TextureMap.locationBlocksTexture);

		// this.RenderHelper_a(worldrenderer, ibakedmodel.getGeneralQuads(), -1, is);

		// for (Iterator iterator = allQuads.iterator(); iterator.hasNext();) {
		//
		// bakedquad = (BakedQuad) iterator.next();
		// worldrenderer.addVertexData(bakedquad.getVertexData());
		//
		// // vec3i = bakedquad.getFace().getDirectionVec();
		// // worldrenderer.putNormal((float) vec3i.getX(), (float) vec3i.getY(), (float) vec3i.getZ());
		// }
		for (BakedQuad bakedQuad : allQuads) {
			i++;
			LightUtil.renderQuadColor(worldrenderer, bakedQuad, -1);

		}
		tessellator.draw();
		// long end = System.currentTimeMillis();

		// LogHelper.info("Debug :" + (long) (end - start));

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

		return MODEL_TEXTURE;
	}

}
