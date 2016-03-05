/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Node;

/**
 *
 * @author dpa
 */
public class PhysicController extends RigidBodyControl implements PhysicsCollisionListener {
    private GameController gameController;
    
    public PhysicController(GameController gameController) {
        this.gameController = gameController;
        this.gameController.getBulletAppState().getPhysicsSpace().addCollisionListener(this);
    }

    public void collision(PhysicsCollisionEvent event) {
        if ( event.getNodeA().getName().equals("Barrier") ) {
            this.gameController.showLooseMessage();
        }
    }    
}
