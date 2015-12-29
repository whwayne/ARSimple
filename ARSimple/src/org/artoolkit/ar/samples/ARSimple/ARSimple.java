package org.artoolkit.ar.samples.ARSimple;


import org.artoolkit.ar.base.ARActivity;
import org.artoolkit.ar.base.rendering.ARRenderer;
import org.artoolkit.ar.samples.ARSimple.R;
import org.micro_gzm.v5.core.ResourceManager;

import android.os.Bundle;
import android.widget.FrameLayout;

/**
 * A very simple example of extending ARActivity to create a new AR application.
 */
public class ARSimple extends ARActivity {
	private ResourceManager mng;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);      
		setContentView(R.layout.main); 
	}
 
	/**
	 * Provide our own SimpleRenderer.
	 */
	@Override
	protected ARRenderer supplyRenderer() {
		
		mng = new ResourceManager(this);
    	mng.loadFBXFile("models/rasta.fbx");
    	FBXRenderer fbxr = new FBXRenderer();
    	fbxr.addModel(mng.getModel());
		return  fbxr;
	}
	
	/**
	 * Use the FrameLayout in this Activity's UI.
	 */
	@Override
	protected FrameLayout supplyFrameLayout() {
		return (FrameLayout)this.findViewById(R.id.mainLayout);    	
	}

}