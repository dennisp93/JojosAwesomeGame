/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.material.Material;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author dragondompy
 */
public class GameController extends SimpleApplication{
    private SurroundBoxWorld gameWorld;
    private BarrierGenerator barGen;
    private BulletAppState bulletAppState;
    
    private static final int MAXOBJECTS = 10;
    private static final float SIZE = 20;
    
    @Override
    public void simpleInitApp() {
        
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        //bulletAppState.getPhysicsSpace().enableDebug(gameCon.getAssetManager());

        gameWorld = new SurroundBoxWorld(this);
        //barGen = new BarrierGenerator(this);
    }
    
    @Override
    public void simpleUpdate(float tpf){
    }
    
    public BulletAppState getBulletAppState(){
        return bulletAppState;
    }
    
    public int getMaxObjects(){
        return MAXOBJECTS;
    }
    
    public float getSize(){
        return SIZE;
    }
    
}
