package org.micro_gzm.v5.loaders.fbx;

public class FBXHeaderExtension extends FBXLoader {
	
	public static final String NAME = "HeaderExtension";
	
	private String fbxHeaderExtensionData;
	
	public FBXHeaderExtension() {
		// TODO Auto-generated constructor stub
	}
	
	public FBXHeaderExtension(String dataIn) {
		
		fbxHeaderExtensionData = dataIn;
	}
	
	public String getData() {
		
		return fbxHeaderExtensionData;
	}

}
