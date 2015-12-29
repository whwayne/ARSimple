package org.micro_gzm.v5.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.micro_gzm.v5.loaders.fbx.FBXLoader;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;


public class ResourceManager {
	
	private AssetManager assets;
	
	private FBXLoader loader;

	
	public ResourceManager(Context ctx) {

		assets = ctx.getAssets();
	}
	
	// TODO Finish the tutorial on loading images, 
	// translate the FBXLoader (and the related classes) from the flash version	
	public void loadFBXFile(String filePath) {
		
		
		loader = new FBXLoader();
		
		try {

			loader.loadFBX(new BufferedReader(new InputStreamReader(assets.open(filePath), "UTF8")));
		}
		catch (IOException e) {
			Log.d("MG", "ResourceManager Error: " + e.getMessage());
		}
	}
	
	public Model3D getModel() {
		
		Model3D model = new Model3D();
		
		model.initialize(loader.getObjectsData());
		
		
		return model;
	}

}
