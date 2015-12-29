package org.micro_gzm.v5.core;

import org.micro_gzm.v5.loaders.fbx.FBXLoader;
import org.micro_gzm.v5.loaders.fbx.FBXObjects;
import org.micro_gzm.v5.loaders.fbx.Loader;
import org.micro_gzm.v5.loaders.obj.OBJLoader;

import android.util.Log;

public class Object3D {
	
	public float[] vertices;
	public short[] indices;
	public float[] UVS;
	
	public Object3D() {
	}

	
	public void setData(Loader dataIn, String type) {
		
		if(type == FBXLoader.NAME) {
			
			setDataFromFBX( (FBXObjects) dataIn );
		}
		
		if(type == OBJLoader.NAME) {
			
			setDataFromOBJ( (OBJLoader) dataIn );
		}
	}
	
	private void setDataFromFBX(FBXObjects dataIn) {
		
		
		int i, j;
		for( i = 0; i < dataIn.geometries.size(); i++ ) {
			
			vertices = new float[dataIn.geometries.get(i).vertices.size()];
			for(j = 0; j < dataIn.geometries.get(i).vertices.size(); j++) {
				
				
				vertices[j] = dataIn.geometries.get(i).vertices.get(j);
			}
			

			indices = new short[dataIn.geometries.get(i).polygonVertexIndex.size()];
			for(j = 0; j < dataIn.geometries.get(i).polygonVertexIndex.size(); j++) {
				
				indices[j] = dataIn.geometries.get(i).polygonVertexIndex.get(j);
			}
			

			UVS = new float[dataIn.geometries.get(i).UV.size()];
			for(j = 0; j < dataIn.geometries.get(i).UV.size(); j++) {
				
				UVS[j] = dataIn.geometries.get(i).UV.get(j);
			}
		}
	}
	
	private void setDataFromOBJ(OBJLoader dataIn) {
		
	}
}
