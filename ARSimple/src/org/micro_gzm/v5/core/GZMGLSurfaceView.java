package org.micro_gzm.v5.core;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;

public class GZMGLSurfaceView extends GLSurfaceView {
	
	GZMRenderer renderer;

    public GZMGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        renderer = new GZMRenderer();
        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer);

        // Render the view only when there is a change in the drawing data
      //  setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
    
    public void addModel(Model3D model) {
    	
    	renderer.addModel(model);
    }
}
