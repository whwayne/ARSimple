package org.micro_gzm.v5.loaders.fbx;

import java.util.Vector;


public class FBXObjects extends FBXLoader {
	
	public final static String NAME = "Objects";
	
	private String fbxObjectsData;
	
	public Vector<FBXGeometry> geometries;
	public Vector<FBXModel> models;
	
	public FBXObjects() {

	}
	
	public FBXObjects(String dataIn) {
		
		fbxObjectsData = dataIn;
		
		initialize();
	}

	
	public FBXObjects(StringBuffer dataIn) {

		fbxObjectsData = dataIn.toString();
		
		initialize();
	}
	
	private void initialize() {
		
		geometries = new Vector<FBXGeometry>();
		String [] arr = fbxObjectsData.split(FBXGeometry.NAME + ": ");
		int i;
		for( i = 1; i < arr.length; i++) {
			
			geometries.add(new FBXGeometry(arr[i]));
		}		
	}
	
	public String getRawData() {
		
		return fbxObjectsData;
	}
}
