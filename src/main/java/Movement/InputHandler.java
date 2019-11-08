package Movement;

import java.awt.event.KeyEvent;

/**
 *
 * Handle KeyEvents
 */
public class InputHandler {

    MovementInterface movementInterface;

    public InputHandler(MovementInterface movementInterface) {
        this.movementInterface = movementInterface;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            movementInterface.moveBoatLeft();
        }
        if (key == KeyEvent.VK_RIGHT) {
            movementInterface.moveBoatRight();
        }
    }

}
