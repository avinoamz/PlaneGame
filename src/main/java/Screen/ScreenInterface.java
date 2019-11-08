package Screen;

import Model.GameObject;
import java.util.LinkedList;

/**
 *
 * Define public methods for screen related operations
 */
public interface ScreenInterface {

    void drawObjects(LinkedList<GameObject> gameObjects, int lives, int score);

    void gameOver();

}
