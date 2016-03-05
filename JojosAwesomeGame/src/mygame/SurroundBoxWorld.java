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
import com.jme3.math.ColorRGBA;
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
    private GameController gameCon;
    private float SIZE;
    private ArrayList<Geometry> sideList;
    
    public SurroundBoxWorld(GameController gameCon){
        //Size of the Cube, startVector and List of the sides
        this.gameCon = gameCon;
        SIZE = gameCon.getSize();
        
        Vector3f startVector = new Vector3f(0.0f, 0.0f, 0.0f);
        sideList = new ArrayList<Geometry>();
              
        //Predefined Box
        Box b = new Box(SIZE,SIZE,0.0f);
        Geometry Standart = new Geometry("Box",b);
        Standart.setLocalTranslation(startVector);
        
        createSides(Standart);
        
        attachToNode();        
    }
    
    private void createSides(Geometry Standart){
        Geometry Back = Standart.clone();
        addMaterial(Back, "Textures/box_back.jpg");
        Back.move(0f, 0f, -SIZE);
        
        Geometry Front = Standart.clone();
        addMaterial(Front, "Textures/box_front.jpg");
        Front.move(0f, 0f, SIZE);
        
        Geometry Ground = Standart.clone();
        addMaterial(Ground, "Textures/Space_Background.jpg");
        Ground.rotate((float) Math.PI/2, 0f, 0f);
        Ground.move(0f, -SIZE, 0f);
        
        Geometry Left = Standart.clone();
        addMaterial(Left, "Textures/box_left.jpg");
        Left.rotate(0f, (float) Math.PI/2, 0f);
        Left.move(-SIZE, 0f, 0f);
        
        Geometry Right = Standart.clone();
        addMaterial(Right, "Textures/box_right.jpg");
        Right.rotate(0f, (float) -Math.PI/2, 0f);
        Right.move(SIZE, 0f, 0f);
        
        Geometry Top = Standart.clone();
        addMaterial(Top, "Textures/Space_Background.jpg");
        Top.rotate((float) Math.PI/2, 0f, 0f);
        Top.move(0f, SIZE, 0f);
    }
    
    private void addMaterial(Geometry g,String matString){
        sideList.add(g);
        Material mat = new Material(gameCon.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", gameCon.getAssetManager().loadTexture(matString));
        g.setMaterial(mat);
    }

    private void attachToNode() {
        Node BoxNode = new Node("Box");
        for(Geometry g: sideList){
            addCollision(g);
            BoxNode.attachChild(g);
        }
        gameCon.getRootNode().attachChild(BoxNode);
    }
    
    private void addCollision(Geometry g){
        CollisionShape boxCollisionShape = CollisionShapeFactory.createMeshShape(g);
        RigidBodyControl boxControl = new RigidBodyControl(boxCollisionShape,0.0f);
        g.addControl(boxControl);
        gameCon.getBulletAppState().getPhysicsSpace().add(boxControl);
    }
}
