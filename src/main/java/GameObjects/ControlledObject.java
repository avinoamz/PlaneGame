package GameObjects;

import java.awt.event.KeyEvent;

/**
 *
 * Interface that represents a Controlled Object
 */
public interface ControlledObject {

    public void keyPressed(KeyEvent e);

    public void keyReleased(KeyEvent e);
}
