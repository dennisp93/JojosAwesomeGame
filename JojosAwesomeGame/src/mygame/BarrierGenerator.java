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
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author dragondompy
 */
public class BarrierGenerator {
    private int MAXOBJECTS;
    private float SIZE;
    public BarrierGenerator(GameController gameCon) {
        MAXOBJECTS = gameCon.getMaxObjects();
        SIZE = gameCon.getSize();
        
        Box MeteorBox = new Box(0.5f, 0.5f, 0.5f);
        String name;
        Node MeteorNode = new Node();
        for(int i=0;i<MAXOBJECTS;i++)
        {
            name = "Meteor"+i;
            Geometry Meteor = new Geometry(name ,MeteorBox);
            Meteor.setLocalTranslation(0.0f, 0.0f, 10.0f);
            Meteor.move((float) (Math.random()-0.5)*2*SIZE, (float) (Math.random()-0.5)*2*SIZE, (float) (Math.random()-0.5)*2*SIZE);
            Material matMeteor = new Material(gameCon.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
            matMeteor.setColor("Color", ColorRGBA.randomColor());
            Meteor.setMaterial(matMeteor);
            
            CollisionShape meteorCollisionShape = CollisionShapeFactory.createMeshShape(Meteor);
            RigidBodyControl boxControl = new RigidBodyControl(meteorCollisionShape,0.0f);
            Meteor.addControl(boxControl);
            gameCon.getBulletAppState().getPhysicsSpace().add(boxControl);
            
            MeteorNode.attachChild(Meteor);
        }
        gameCon.getRootNode().attachChild(MeteorNode);
        
    }
    
}
