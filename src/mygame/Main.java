package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.*;
import com.jme3.texture.Texture;
 
public class Main extends SimpleApplication {
 
    public static void main(String[] args)
    {	
        Main app = new Main();
        app.start(); // start the game
    }
 
    @Override
    public void simpleInitApp() 
    {
    	initGrid();
    	initScene();
    	initString();
    }
    
    @Override
    public void simpleUpdate(float tpf) 
    {
        rootNode.getChild(0).rotate(0, -tpf, 0); 
    }
    
    void initString ()
    {
        BitmapFont guiFont;
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText helloText = new BitmapText(guiFont, false);
        helloText.setSize(guiFont.getCharSet().getRenderedSize());
        helloText.setText("Hello World");
        helloText.setLocalTranslation(200, helloText.getLineHeight() + 100, 100);
        guiNode.attachChild(helloText);
    }
    
    void initGrid ()
    {
    	Node grid = new Node("Grid");
    	Line xLine, zLine;
    	Geometry xGeo, zGeo;
    	
    	Material gridMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    	
    	for (int i = -10; i <= 10; i++)
    	{
    		xLine = new Line(new Vector3f((float)i,0f,-10f), new Vector3f((float)i,0f,10f));
    		zLine = new Line(new Vector3f(-10f,0f,(float)i), new Vector3f(10f,0f,(float)i));
    		xGeo = new Geometry("x", xLine);
    		zGeo = new Geometry("z", zLine);
    		xGeo.setMaterial(gridMat);
    		zGeo.setMaterial(gridMat);
    		grid.attachChild(xGeo);
    		grid.attachChild(zGeo);
    	}
    	
    	//grid.rotate(1f, 1f, 0f);
    	//grid.setLocalTranslation(10f, 0f, 10f);
    	
    	rootNode.attachChild(grid);
    }
    
    void initScene ()
    {
    	Line xAxis = new Line(new Vector3f(0f,0f,0f), new Vector3f(1f,0f,0f));
    	Line yAxis = new Line(new Vector3f(0f,0f,0f), new Vector3f(0f,1f,0f));
    	Line zAxis = new Line(new Vector3f(0f,0f,0f), new Vector3f(0f,0f,1f));
    	    	
    	xAxis.setLineWidth(3f);
    	yAxis.setLineWidth(3f);
    	zAxis.setLineWidth(3f);
    	
    	Geometry xGeometry = new Geometry("x", xAxis);
    	Geometry yGeometry = new Geometry("y", yAxis);
    	Geometry zGeometry = new Geometry("z", zAxis);
    	
    	Material xMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    	Material yMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    	Material zMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    	
    	xMat.setColor("Color", ColorRGBA.Green);
    	yMat.setColor("Color", ColorRGBA.Red);
    	zMat.setColor("Color", ColorRGBA.Blue);
        
    	xGeometry.setMaterial(xMat);
    	yGeometry.setMaterial(yMat);
    	zGeometry.setMaterial(zMat);
    	
    	//	Mass 1
    	Sphere mass1 = new Sphere(50, 50, 2f);
    	Geometry mass1Geometry = new Geometry("Mass1", mass1);
    	Material mass1Mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    	mass1Mat.setColor("Color", ColorRGBA.White);
        Texture texsp = assetManager.loadTexture("Interface/Logo/Monkey.jpg");
        mass1Mat.setTexture("ColorMap", texsp);
    	mass1Geometry.setMaterial(mass1Mat);
    	mass1Geometry.setLocalTranslation(3f, 0f, -2f);
        mass1Geometry.rotate((float)-(3.14 / 2), 0f, 0f);
    	rootNode.attachChild(mass1Geometry);
    	
        rootNode.attachChild(xGeometry);
        rootNode.attachChild(yGeometry);
        rootNode.attachChild(zGeometry);
        
        /** An unshaded textured cube. 
            *  Uses texture from jme3-test-data library! */ 
        Box boxshape1 = new Box(new Vector3f(0f, 10f, 0f), 1f,1f,1f); 
        Geometry cube_tex = new Geometry("A Textured Box", boxshape1); 
        Material mat_tex = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"); 
        Texture tex = assetManager.loadTexture("Interface/Logo/Monkey.jpg"); 
        mat_tex.setTexture("ColorMap", tex); 
        cube_tex.setMaterial(mat_tex); 
        rootNode.attachChild(cube_tex); 
        
    }
}
