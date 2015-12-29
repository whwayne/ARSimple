package org.artoolkit.ar.samples.ARSimple;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

import org.artoolkit.ar.base.ARToolKit;
import org.artoolkit.ar.base.rendering.ARRenderer;
import org.micro_gzm.v5.core.Model3D;
import org.micro_gzm.v5.utils.ColorRGB;

import android.content.Context;
import android.opengl.GLES10;
import android.util.FloatMath;

/**
 * A very simple Renderer that adds a marker and draws a cube on it.
 */
public class FBXRenderer extends ARRenderer {


	private int markerID = -1;
//	private Cube cube = new Cube(40.0f, 0.0f, 0.0f, 20.0f);
	
	private ColorRGB bckgrdColor = new ColorRGB(0.5f, 0.5f, 0.5f, 1.0f);
	private Vector<Model3D> models;
	private int tick = 0;
	
	
	public FBXRenderer() {

		models = new Vector<Model3D>();
	}
	
	public void addModel(Model3D modelIn) {
		
		models.add(modelIn);
	}

	/**
	 * Markers can be configured here.
	 */
	@Override
	public boolean configureARScene() {

		markerID = ARToolKit.getInstance().addMarker("single;Data/patt.hiro;80");
		if (markerID < 0) return false;

		return true;
	}

   
	public void draw(GL10 gl) {

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		// Apply the ARToolKit projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadMatrixf(ARToolKit.getInstance().getProjectionMatrix(), 0);
	
		gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);        
    	gl.glFrontFace(GL10.GL_CW);
    			
		// If the marker is visible, apply its transformation, and draw a cube
		if (ARToolKit.getInstance().queryMarkerVisible(markerID)) {
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerID), 0);
			
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			
			for (Model3D mdl : models) {

				mdl.setGL(gl);
				mdl.rotate(tick, 0, 0, 1);
				mdl.translate(0, 0, FloatMath.sin(tick));
				mdl.draw(gl);			
			}
			
			// Disable the vertices buffer.
			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY); // OpenGL docs
			// Disable face culling.
			gl.glDisable(GL10.GL_CULL_FACE); // OpenGL docs
			
			tick++;
			
			
			//cube.draw(gl);
		}

	}
	
}