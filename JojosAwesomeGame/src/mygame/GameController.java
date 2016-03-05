/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.font.BitmapText;
import com.jme3.input.ChaseCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

/**
 *
 * @author dragondompy
 */
public class GameController extends SimpleApplication implements AnalogListener {

    private SurroundBoxWorld gameWorld;
    private BarrierGenerator barGen;
    private Chest chest;
    private BulletAppState bulletAppState;
    private PlayerModel player;
    private static final int MAXOBJECTS = 2000;
    private static final float SIZE = 60;
    private static final float SPEED = 20;
    private float timeRunning;
    private boolean loosed;

    @Override
    public void simpleInitApp() {

        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        //bulletAppState.setDebugEnabled(true);
        bulletAppState.getPhysicsSpace().setGravity(Vector3f.ZERO);

        PhysicController physicController = new PhysicController(this);

        gameWorld = new SurroundBoxWorld(this);
        barGen = new BarrierGenerator(this);
        player = new PlayerModel(this);
        chest = new Chest(this);

        initLight();
        initKeys();

        // Disable the default flyby cam
        flyCam.setEnabled(false);
        // Enable a chase cam for this target (typically the player).
        ChaseCamera chaseCam = new ChaseCamera(cam, player.getPlayerModel(), inputManager);
        chaseCam.setSmoothMotion(false);
        chaseCam.setMinVerticalRotation((float) (-Math.PI / 2));
        chaseCam.setDefaultDistance(2f);

    }

    @Override
    public void simpleUpdate(float tpf) {
        if (loosed != true) {
            timeRunning += tpf;
            if (timeRunning > 5) {
                player.getPlayerModel().move(cam.getDirection().mult(SPEED * tpf));
            }
        }
        player.getPlayerControll().setPhysicsLocation(player.getPlayerModel().getLocalTranslation());

        player.getPlayerControll().activate();

    }

    public BulletAppState getBulletAppState() {
        return bulletAppState;
    }

    public int getMaxObjects() {
        return MAXOBJECTS;
    }

    public float getSize() {
        return SIZE;
    }

    public void initLight() {
        /**
         * A white ambient light source.
         */
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        rootNode.addLight(ambient);

        /**
         * A white, directional light source
         */
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
    }

    private void initKeys() {
        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addListener(this, "Forward");
    }

    public void onAnalog(String name, float value, float tpf) {
        if (name.equals("Forward") && loosed != true) {
            player.getPlayerModel().move(cam.getDirection().mult(20 * tpf));
        }
        player.getPlayerControll().setPhysicsLocation(player.getPlayerModel().getLocalTranslation());

        player.getPlayerControll().activate();

    }

    public void showLooseMessage() {
        BitmapText hudText = new BitmapText(guiFont, false);
        hudText.setSize(50f);
        hudText.setColor(ColorRGBA.Yellow);
        hudText.setText("You Loose!");
        hudText.setLocalTranslation(settings.getWidth() / 2 - 100, settings.getHeight() / 2 + 100, 0);
        guiNode.attachChild(hudText);
        loosed = true;
    }

    public void showWinMessage() {
        BitmapText hudText = new BitmapText(guiFont, false);
        hudText.setSize(50f);
        hudText.setColor(ColorRGBA.Blue);
        hudText.setText("YOU WIN!!!\nTime: " + timeRunning);
        hudText.setLocalTranslation(settings.getWidth() / 2 - 100, settings.getHeight() / 2 + 100, 0);
        guiNode.attachChild(hudText);
        loosed = true;
    }
}
