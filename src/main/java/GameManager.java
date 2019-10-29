
import GameData.GameData;
import GameObjects.Boat;
import GameObjects.GameObject;
import GameObjects.Parachutist;
import GameObjects.Plane;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * The class that operates the game's logic and mechanic
 */
public class GameManager {

    private final GameData gameData;
    private final ScreenManager screenManager;
    private final Boat boat;
    private final Plane plane;
    private final Random rand;

    // Create all the base objects and start the game
    public GameManager() {
        gameData = GameData.getInstance();
        boat = new Boat();
        plane = new Plane();
        rand = new Random();

        ArrayList<GameObject> startingGameObjects = new ArrayList<>();
        startingGameObjects.add(boat);
        startingGameObjects.add(plane);

        screenManager = new ScreenManager(startingGameObjects);

        gameLoop();
    }

    // keeps the game running, while updating the objects
    private void gameLoop() {
        while (gameData.getLives() > 0) {
            moveObjects();
            createNewParachuter();
            paint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
        }
        gameOver();
    }

    // updates objects location
    public void moveObjects() {
        screenManager.moveObjects();
    }

    // randomly create new parachutists
    private void createNewParachuter() {
        if (gameData.getCurrentParachuters() < gameData.getMaxParachuters()) {

            int n = rand.nextInt(500);
            if (n < 3) {
                Parachutist parachutist = new Parachutist(plane.getX(), plane.getY(), boat);
                screenManager.addObject(parachutist);
                gameData.addParachutist();
            }
        }
    }

    // paiting the objects on the screen
    public void paint() {
        screenManager.repaint();
    }

    // finish the game
    public void gameOver() {
        screenManager.gameOver();
    }

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
    }

}
