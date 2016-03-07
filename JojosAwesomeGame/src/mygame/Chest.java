package mygame;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dragondompy
 */
public class Chest {
    
    private float SIZE;
    private Sphere s;
    private Geometry Chest;
    private float maxScale;
    private float scale;
    
    public Chest(GameController gameCon){
        s = new Sphere(30,30,1);
        Chest = new Geometry("Chest", s);
        SIZE = gameCon.getSize();
        
        scale = 1;
        maxScale = 1000;

        Chest.setLocalTranslation(0.0f, 0.0f, 10.0f);
        Chest.move((float) (Math.random() - 0.5) * (SIZE/2), (float) (Math.random() - 0.5) * (SIZE/2), (float) (Math.random() - 0.5) * (SIZE/2));
        Material matBarrier = new Material(gameCon.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        matBarrier.setColor("Color", ColorRGBA.Yellow);
        Chest.setMaterial(matBarrier);

        CollisionShape ChestCollisionShape = CollisionShapeFactory.createMeshShape(Chest);
        RigidBodyControl chestControl = new RigidBodyControl(ChestCollisionShape, 0.0f);
        Chest.addControl(chestControl);
        gameCon.getBulletAppState().getPhysicsSpace().add(chestControl);
        
        gameCon.getRootNode().attachChild(Chest);
    }

    void pumpUp() {
        //if(scale < maxScale) {
            Chest.scale(1.01f);
            //scale = scale + 1;
        //}        
    }    
}
