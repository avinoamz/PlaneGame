
import GameData.GameData;
import GameObjects.Boat;
import GameObjects.GameObject;
import GameObjects.InteractableObject;
import GameObjects.Parachutist;
import GameObjects.Plane;
import GameObjects.Sea;
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
    private final Sea sea;
    private final Random rand;
    ArrayList<InteractableObject> interactableObjects;

    // Create all the base objects and start the game
    public GameManager() {
        gameData = GameData.getInstance();
        boat = new Boat();
        plane = new Plane();
        sea = new Sea();
        rand = new Random();

        ArrayList<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(boat);
        gameObjects.add(plane);

        interactableObjects = new ArrayList<>();
        interactableObjects.add(boat);
        interactableObjects.add(sea);

        screenManager = new ScreenManager(gameObjects);

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
        if (gameData.getCurrentParachuters() < GameData.MAX_PARACHUTISTS) {

            int n = rand.nextInt(500);
            if (n < 3) {
                Parachutist parachutist = new Parachutist(plane.getX(), plane.getY(), interactableObjects);
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
