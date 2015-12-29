package org.micro_gzm.v5;

import org.micro_gzm.v5.core.GZMRenderer;
import org.micro_gzm.v5.core.ResourceManager;
import org.micro_gzm.v5.utils.GZMTimer;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

public class Main extends Activity {
	
	private GZMRenderer renderer;
	private ResourceManager mng;

	
	private GZMTimer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        renderer = new GZMRenderer();

		timer = new GZMTimer();
        
        GLSurfaceView view = new GLSurfaceView(this);
        view.setRenderer(renderer);
        
        setContentView(view);
        
        initialize();
    }
    
    private void initialize() {

		timer.start();
		Log.d("MG", "PARSE STARTED @: " + timer.getElapsedTimeSecs() + " sec.");
    	
    	mng = new ResourceManager(this);
    	mng.loadFBXFile("models/off.fbx");
    	
    	renderer.addModel(mng.getModel());
		Log.d("MG", "COMPLETE @: " + timer.getElapsedTimeSecs() + " sec.");
    	

		timer.stop();
    }
}