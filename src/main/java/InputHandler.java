
import java.awt.event.KeyEvent;

/**
 *
 * Handle KeyEvents
 */
public class InputHandler {

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        MovementManager movementManager = GameData.getInstance().getMovementManager();

        if (key == KeyEvent.VK_LEFT) {
            movementManager.moveBoatLeft();
        }
        if (key == KeyEvent.VK_RIGHT) {
            movementManager.moveBoatRight();
        }
    }

}
