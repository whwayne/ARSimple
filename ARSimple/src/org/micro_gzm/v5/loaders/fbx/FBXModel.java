package org.micro_gzm.v5.loaders.fbx;

public class FBXModel {

	public static final String NAME = "Model";
	
	private String fbxModelData;
	
	public FBXModel() {

	}
	
	public FBXModel(String dataIn) {
		
		fbxModelData = dataIn;
	}
	
	public String getData() {
		
		return fbxModelData;
	}

}
