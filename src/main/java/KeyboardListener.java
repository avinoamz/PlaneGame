
import GameObjects.Boat;
import GameObjects.ControlledObject;
import GameObjects.GameObject;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * listens to keyboard input and updates the sprite accordingly
 */
public class KeyboardListener implements KeyListener {

    private final ArrayList<ControlledObject> controlledObjects;

    public KeyboardListener(ArrayList<GameObject> gameObjects) {
        controlledObjects = new ArrayList<>();
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof ControlledObject) {
                controlledObjects.add((ControlledObject) gameObject);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (ControlledObject controlledObject : controlledObjects) {
            controlledObject.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (ControlledObject controlledObject : controlledObjects) {
            controlledObject.keyReleased(e);
        }
    }

}
