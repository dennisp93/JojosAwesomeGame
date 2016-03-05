/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.scene.Node;

/**
 *
 * @author dragondompy
 */
public class BarrierGenerator {
    private int MAXOBJECTS;
    private float SIZE;
    private GameController gameCon;
    private Node BarrierNode;
    public BarrierGenerator(GameController gameCon) {
        this.gameCon = gameCon;
        
        MAXOBJECTS = gameCon.getMaxObjects();
        SIZE = gameCon.getSize();
        
        String name;
        BarrierNode = new Node();
        for(int i=0;i<MAXOBJECTS;i++)
        {
            Barrier b = new Barrier(this);
        }
        gameCon.getRootNode().attachChild(BarrierNode);
        
    }
    
    public GameController getGameController(){
        return gameCon;
    }
    
    public Node getBarrierNode(){
        return BarrierNode;
    }
    
}
