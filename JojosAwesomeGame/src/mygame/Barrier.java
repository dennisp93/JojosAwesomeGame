/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author dragondompy
 */
public class Barrier {

    private Box BarrierBox = new Box(0.5f, 0.5f, 0.5f);
    private GameController gameCon;
    private float SIZE;

    public Barrier(BarrierGenerator barGen) {
        gameCon = barGen.getGameController();
        SIZE = gameCon.getSize();

        Geometry Barrier = new Geometry("Barrier", BarrierBox);
        Barrier.setLocalTranslation(0.0f, 0.0f, 10.0f);
        Barrier.move((float) (Math.random() - 0.5) * 2 * SIZE, (float) (Math.random() - 0.5) * 2 * SIZE, (float) (Math.random() - 0.5) * 2 * SIZE);
        Material matBarrier = new Material(gameCon.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        matBarrier.setColor("Color", ColorRGBA.randomColor());
        Barrier.setMaterial(matBarrier);

        CollisionShape BarrierCollisionShape = CollisionShapeFactory.createMeshShape(Barrier);
        RigidBodyControl boxControl = new RigidBodyControl(BarrierCollisionShape, 0.0f);
        Barrier.addControl(boxControl);
        gameCon.getBulletAppState().getPhysicsSpace().add(boxControl);
        
        barGen.getBarrierNode().attachChild(Barrier);

    }
}
