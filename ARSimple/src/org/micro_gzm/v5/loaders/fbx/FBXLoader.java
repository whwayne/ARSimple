package org.micro_gzm.v5.loaders.fbx;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import org.micro_gzm.v5.core.Object3D;

import android.util.Log;

public class FBXLoader implements Loader {
	
	public final static String NAME = "FBXLoader";
	
	protected String fbxData = "";
	
	protected FBXHeaderExtension fbxHeaderExtension;
	protected FBXDefinitions fbxDefinitions;
	protected FBXObjects fbxObjects;

	
	public FBXLoader() {

	}
	
	public void loadFBX(BufferedReader bufferIn) {
		

		BufferedReader buffer = bufferIn;
		String line;
		
		try {
			while (( line = buffer.readLine()) != null) fbxData += line;
			
			initialize();
		}
		catch (IOException e) {

			Log.d("MG", "Error: " + e.getMessage());
		}
		
	}
	
	private void initialize() {
		
		//fbxHeaderExtension = new FBXHeaderExtension(getBlock(fbxData, FBXHeaderExtension.NAME));
		//fbxDefinitions = new FBXDefinitions(getBlock(fbxData, FBXDefinitions.NAME));
		fbxObjects = new FBXObjects(getBlock(fbxData, FBXObjects.NAME));
		
	}
	
	protected String getBlock(String dataIn, String blockName) {
		
		StringBuffer block = new StringBuffer();
		
		int begin = dataIn.indexOf(blockName);
		String bracksStr = "{}";
		char openBrack = bracksStr.charAt(0);
		char closedBrack = bracksStr.charAt(1);
		
		if(begin != -1) {
			
			int i;
			int bracks = 0;
			boolean started = false;
			char character;
			
			for( i = begin; i < dataIn.length(); i++) {
				
				character = dataIn.charAt(i);
				
				if(character == openBrack) {
					
					started = true;
					bracks++;
				}

				if(character == closedBrack) bracks--;
				
				block.append(character);
				
				if(bracks == 0 && started) break;
				
			}
		}
		
		return block.toString();
	}
	

	
	protected String[] getBlockAttributes(String dataIn, String attr) {
		
		int begin = dataIn.indexOf(attr) + attr.length();
		
		int end = dataIn.indexOf("{");
		
		String[] dataOut = dataIn.substring(begin, end).split(",");

		return dataOut;
	}
	
	protected String getPropertyBlock(String dataIn, String prop) {
		
		StringBuffer r = new StringBuffer(dataIn);
		StringBuffer a = new StringBuffer();
		int begin = r.indexOf(prop);
		

		String bracksStr = "{}";
		char closedBrack = bracksStr.charAt(1);
		
		char character;

		int i;
		for(i = begin; i < r.length(); i++) {
			
			character = r.charAt(i);

			a.append(character);
			
			if(character == closedBrack) break;
		}
		
		return a.substring(a.indexOf("{"), a.indexOf("}"));
	}
	
	protected String getDataFromBlock(String dataIn, String dataType) {

		String str = "";
		
		if(dataIn.indexOf(dataType) > -1) {
			
			str = dataIn.substring(dataIn.indexOf(dataType) + dataType.length(), dataIn.length());
			str = clearString(str);
		}
		else str = dataIn;

		return str;
	}
	
	protected Vector<Float> toVectorFloat(String dataIn) {
		
		
		Vector<Float> vecNum = new Vector<Float>();
		String[] arr = dataIn.split(",");
		
		int i;
		for( i = 0; i < arr.length; i++) {
			
			if(isFloat(arr[i])) vecNum.add( Float.parseFloat(arr[i]) );
		}
		
		return vecNum;
	}	
	
	protected Vector<Short> toVectorShort(String dataIn) {
		
		
		Vector<Short> vecShort = new Vector<Short>();
		String[] arr = dataIn.split(",");
		
		int i;
		for( i = 0; i < arr.length; i++) {
			
			if(isInteger(arr[i])) vecShort.add( Short.parseShort(arr[i]) );
		}
		
		return vecShort;
	}
	
	/**
	 * UTILS
	 * */
	public boolean isFloat(String input)  {  
	   try {  
	      Float.parseFloat( input );  
	      return true;  
	   }
	   catch( Exception e) {  
	      return false;  
	   }  
	}
	public boolean isInteger(String input)  {  
	   try {  
	      Integer.parseInt( input );  
	      return true;  
	   }
	   catch( Exception e) {  
	      return false;  
	   }  
	}
	
	protected String clearString(String dataIn) {

		String str = dataIn.replace("\n", "");
		str = dataIn.replace("\t", "");
		
		return str;
	}
	
	public Object getData() {
		
		return this;
	}
	
	public Object3D getObjectsData() {
		
		Object3D obj = new Object3D();
		
		obj.setData(fbxObjects, NAME);
		
		return obj;
	}
}
