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
    
    @Override
    public void simpleInitApp() {
        gameWorld = new SurroundBoxWorld(this);
    }
    
    @Override
    public void simpleUpdate(float tpf){
    }
    
}
