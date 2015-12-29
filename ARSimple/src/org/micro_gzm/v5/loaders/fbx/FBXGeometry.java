package org.micro_gzm.v5.loaders.fbx;

import java.util.Vector;

public class FBXGeometry extends FBXLoader {
	
	public static final String NAME = "Geometry";
	
	public String ID;
	public String className;
	public String type;
	
	public Vector<Float> vertices;
	public int numVertices;
	
	public Vector<Short> polygonVertexIndex;
	public int numIndices;
	
	public Vector<Integer> edges;
	public int numEdges;
	
	public Vector<Float> normals;
	public int numNormals;
	
	public Vector<Float> UV;
	public int numUVS;
	
	public Vector<Integer> UVIndex;
	public int numUVIndices;
	
	
	private String fbxGeometryData;
	
	public FBXGeometry() {

	}
	
	public FBXGeometry(String dataIn) {
		
		fbxGeometryData = "Geometry: " + dataIn;
		
		initialize();
	}
	
	public FBXGeometry(StringBuffer dataIn) {
		
		fbxGeometryData = "Geometry: " + dataIn.toString();
		
		initialize();
	}
	
	private void initialize() {

		String[] attrData = getBlockAttributes(fbxGeometryData, "Geometry: ");
		
		ID =  attrData[0];
		className =  attrData[1];
		type =  attrData[2];
		
		vertices = toVectorFloat(getDataFromBlock(getPropertyBlock(fbxGeometryData, "Vertices:"), "a: "));
		numVertices = vertices.size();
		
		polygonVertexIndex = normalizeIndices(toVectorShort(getDataFromBlock(getPropertyBlock(fbxGeometryData, "PolygonVertexIndex:"), "a: ")));
		numIndices = polygonVertexIndex.size();
		
		UV = toVectorFloat(getDataFromBlock(getPropertyBlock(fbxGeometryData, "UV:"), "a: "));
		numUVS = UV.size();	
	}
	
	public String getData() {
		
		return fbxGeometryData;
	}
	
	/**
	 * UTILS
	 * 
	 */
	private Vector<Short> normalizeIndices(Vector<Short> inds) {
		
		Vector<Short> shortVec = new Vector<Short>();
		
		int i;
		for( i = 0; i < inds.size(); i++) {
			
			if(inds.get(i) < 0) {
				shortVec.add((short)((inds.get(i) + 1) * -1));
			}
			else {
				shortVec.add(inds.get(i));
			}
		}

		return shortVec;
	}
}
