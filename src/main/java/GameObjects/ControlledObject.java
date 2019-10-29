package GameObjects;

import java.awt.event.KeyEvent;

/**
 *
 * @author avino
 */
public interface ControlledObject {

    public void keyPressed(KeyEvent e);

    public void keyReleased(KeyEvent e);
}
