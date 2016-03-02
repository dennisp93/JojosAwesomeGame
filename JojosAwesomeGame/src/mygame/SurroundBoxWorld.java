/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author dragondompy
 */
public class SurroundBoxWorld {
    public SurroundBoxWorld(GameController gameCon){
        float size = 5f;
        
        Box b = new Box(size,size,0);
        Geometry Standart = new Geometry("Box",b);
        Material mat = new Material(gameCon.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", gameCon.getAssetManager().loadTexture("Textures/Space_Background.jpg"));
        Standart.setMaterial(mat);
        Standart.setLocalTranslation(0f, 0f, 0f);
        
        Geometry Back = Standart.clone();
        Geometry Front = Standart.clone();
        Geometry Ground = Standart.clone();
        Geometry Left = Standart.clone();
        Geometry Right = Standart.clone();
        Geometry Top = Standart.clone();
        
        Ground.rotate((float) Math.PI/2, 0f, 0f);
        Top.rotate((float) Math.PI/2, 0f, 0f);
        Left.rotate(0f, (float) Math.PI/2, 0f);
        Right.rotate(0f, (float) Math.PI/2, 0f);
        
        Right.setLocalTranslation(size, 0f, 0f);
        Left.setLocalTranslation(-size, 0f, 0f);
        Top.setLocalTranslation(0f, size, 0f);
        Ground.setLocalTranslation(0f, -size, 0f);
        Back.setLocalTranslation(0f, 0f, -size);
        Front.setLocalTranslation(0f, 0f, size);
                
        Node BoxNode = new Node("Box");
        BoxNode.attachChild(Back);
        BoxNode.attachChild(Front);
        BoxNode.attachChild(Top);
        BoxNode.attachChild(Ground);
        BoxNode.attachChild(Left);
        BoxNode.attachChild(Right);
        
        gameCon.getRootNode().attachChild(BoxNode);
    }
}
