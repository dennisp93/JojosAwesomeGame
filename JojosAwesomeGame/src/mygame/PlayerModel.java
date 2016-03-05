/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 *
 * @author dpa
 */
public class PlayerModel {

    RigidBodyControl playerControll;
    Geometry playerModel;

    public PlayerModel(GameController gameController) {
        
        Box b = new Box(0.1f, 0.1f, 0.1f);
        playerModel = new Geometry("Player", b);
        
        Material playerMaterial = new Material(gameController.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        playerMaterial.setColor("Color", ColorRGBA.Green);
        playerModel.setMaterial(playerMaterial);
     
        
        playerControll = new RigidBodyControl(0.01f);
        playerModel.addControl(playerControll);
       
        
        gameController.getRootNode().attachChild(playerModel);
        gameController.getBulletAppState().getPhysicsSpace().add(playerControll);
    }
    
    public Spatial getPlayerModel() {
        return playerModel;
    }
    
    public RigidBodyControl getPlayerControll() {
        return playerControll;
    }
}
