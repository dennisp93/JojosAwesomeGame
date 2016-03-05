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
    
    public Chest(GameController gameCon){
        Sphere s = new Sphere(30,30,1);
        Geometry Chest = new Geometry("Chest", s);
        SIZE = gameCon.getSize();

        Chest.setLocalTranslation(0.0f, 0.0f, 10.0f);
        Chest.move((float) (Math.random() - 0.5) * 2 * SIZE, (float) (Math.random() - 0.5) * 2 * SIZE, (float) (Math.random() - 0.5) * 2 * SIZE);
        Material matBarrier = new Material(gameCon.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        matBarrier.setColor("Color", ColorRGBA.randomColor());
        Chest.setMaterial(matBarrier);

        CollisionShape ChestCollisionShape = CollisionShapeFactory.createMeshShape(Chest);
        RigidBodyControl chestControl = new RigidBodyControl(ChestCollisionShape, 0.0f);
        Chest.addControl(chestControl);
        gameCon.getBulletAppState().getPhysicsSpace().add(chestControl);
        
        gameCon.getRootNode().attachChild(Chest);
    }
    
}
