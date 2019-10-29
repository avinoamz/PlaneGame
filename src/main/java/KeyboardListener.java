
import GameObjects.Boat;
import GameObjects.GameObject;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * listens to keyboard input and updates the sprites accordingly
 */
public class KeyboardListener implements KeyListener {

    Boat boat;

    public KeyboardListener(ArrayList<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Boat) {
                this.boat = (Boat) gameObject;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boat.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        boat.keyReleased(e);
    }

}
