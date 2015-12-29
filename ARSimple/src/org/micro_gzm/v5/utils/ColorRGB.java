package org.micro_gzm.v5.utils;

public class ColorRGB {
	
	public float r, g, b, a;
	
	public ColorRGB() {
		
		r = 1.0f;
		g = 1.0f;
		b = 1.0f;
		a = 1.0f;
	}
	
	public ColorRGB(float rIn, float gIn, float bIn, float aIn) {
		
		r = rIn;
		g = gIn;
		b = bIn;
		a = aIn;
	}
	
	public ColorRGB HEXA2RGB(int hexaIn) {
		
		return new ColorRGB();
	}

}
