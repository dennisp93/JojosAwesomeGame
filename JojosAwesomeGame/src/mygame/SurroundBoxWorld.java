/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import java.util.ArrayList;

/**
 *
 * @author dragondompy
 */
public class SurroundBoxWorld {
    public SurroundBoxWorld(GameController gameCon){
        //Size of the Cube, startVector and List of the sides
        float size = 20f;
        Vector3f startVector = new Vector3f(0.0f, 0.0f, 10.0f);
        ArrayList<Geometry> sideList = new ArrayList<Geometry>();
        
        //BulletAppState
        BulletAppState bulletAppState = new BulletAppState();
        gameCon.getStateManager().attach(bulletAppState);
        bulletAppState.getPhysicsSpace().enableDebug(gameCon.getAssetManager());
        
        //Predefined Box
        Box b = new Box(size,size,0.0f);
        Geometry Standart = new Geometry("Box",b);
        Material mat = new Material(gameCon.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", gameCon.getAssetManager().loadTexture("Textures/Space_Background.jpg"));
        Standart.setMaterial(mat);
        Standart.setLocalTranslation(startVector);
        
        //Makes sides of the Cube
        Geometry Back = Standart.clone();
        sideList.add(Back);
        Geometry Front = Standart.clone();
        sideList.add(Front);
        Geometry Ground = Standart.clone();
        sideList.add(Ground);
        Geometry Left = Standart.clone();
        sideList.add(Left);
        Geometry Right = Standart.clone();
        sideList.add(Right);
        Geometry Top = Standart.clone();
        sideList.add(Top);
        
        //Places sides
        
        Ground.rotate((float) Math.PI/2, 0f, 0f);
        Top.rotate((float) Math.PI/2, 0f, 0f);
        Left.rotate(0f, (float) Math.PI/2, 0f);
        Right.rotate(0f, (float) Math.PI/2, 0f);
        
        Right.move(size, 0f, 0f);
        Left.move(-size, 0f, 0f);
        Top.move(0f, size, 0f);
        Ground.move(0f, -size, 0f);
        Back.move(0f, 0f, -size);
        Front.move(0f, 0f, size);
        
        //Attaches Collision
        
        Node BoxNode = new Node("Box");
        for(Geometry g: sideList){
            CollisionShape boxCollisionShape = CollisionShapeFactory.createMeshShape(g);
            RigidBodyControl boxControl = new RigidBodyControl(boxCollisionShape,0.0f);
            g.addControl(boxControl);
            bulletAppState.getPhysicsSpace().add(boxControl);
            BoxNode.attachChild(g);
        }
        gameCon.getRootNode().attachChild(BoxNode);
    }
}
