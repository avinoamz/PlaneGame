
import java.awt.event.KeyEvent;

/**
 *
 * Handle KeyEvents
 */
public class InputHandler {

    MovementManager movementManager;

    public InputHandler(MovementManager movementManager) {
        this.movementManager = movementManager;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            movementManager.moveBoatLeft();
        }
        if (key == KeyEvent.VK_RIGHT) {
            movementManager.moveBoatRight();
        }
    }

}
