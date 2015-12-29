package org.micro_gzm.v5.core;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;


public class Model3D extends Entity3D {
	
	public float[] vertices;
	public short[] indices;
	public float[] UVS;
	
	private FloatBuffer vertexBuffer;
	private ShortBuffer indexBuffer;
	
	private GL10 gl;
	
	public Model3D() {

	}
	
	public void initialize(Object3D dataIn) {
		vertices = dataIn.vertices;
		indices = dataIn.indices;
		UVS = dataIn.UVS;

		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indexBuffer = ibb.asShortBuffer();
		indexBuffer.put(indices);
		indexBuffer.position(0);

	}
	
	public void setGL(GL10 glIn) {
		
		gl = glIn;
	}
	
	public void rotate(float val, int x, int y, int z) {
		
		gl.glRotatef(val, x, y, z);
	}
	
	public void translate(float x, float y, float z) {
		
		gl.glTranslatef(x, y, z);
	}
	
	public void draw(GL10 gl) {
		// Specifies the location and data format of an array of vertex
		// coordinates to use when rendering.
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
	}

}