package org.micro_gzm.v5.loaders.fbx;

public class FBXDefinitions extends FBXLoader {
	
	public static final String NAME = "Definitions";
	
	private String fbxDefinitionsData;
	
	public FBXDefinitions() {

	}
	
	public FBXDefinitions(String dataIn) {
		
		fbxDefinitionsData = dataIn;
	}
	
	public String getData() {
		
		return fbxDefinitionsData;
	}

}
