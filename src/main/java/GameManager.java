
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * The class that operates the game's flow
 */
public class GameManager {

    public static final int STARTING_LIVES = 3;
    public static final int GAIN_POINTS = 10;
    public static final int PARACHUTIST_MOVEMENT_SPEED = 1;
    public static final int BOAT_MOVEMENT_SPEED = 6;
    public static final int PLANE_MOVEMENT_SPEED = 1;
    public static final int WINDOW_X_SIZE = 1080;
    public static final int WINDOW_Y_SIZE = 720;
    public static final int SEA_HEIGHT = 242;
    public static final int MAX_PARACHUTISTS = 3;
    public static final String GAME_OVER = "GAME_OVER";

    private final Random rand;
    private Information information;

    private GameObject plane;
    private GameObject boat;
    private LinkedList<GameObject> parachutists;
    private final MovementManager movementManager;
    private final InputHandler inputHandler;
    private final ScreenManager screenManager;
    private final ImageHandler imageHandler;

    // Create all the base objects and start the game
    public GameManager() {
        rand = new Random();
        information = new Information(STARTING_LIVES, 0, 0);

        imageHandler = new ImageHandler();
        boat = new GameObject(
                WINDOW_X_SIZE / 2,
                WINDOW_Y_SIZE - SEA_HEIGHT,
                0,
                imageHandler.getBoatImage().getHeight(),
                imageHandler.getBoatImage().getWidth(),
                Type.BOAT);
        plane = new GameObject(
                WINDOW_X_SIZE - imageHandler.getPlaneImage().getWidth(),
                0,
                PLANE_MOVEMENT_SPEED,
                imageHandler.getPlaneImage().getHeight(),
                imageHandler.getPlaneImage().getWidth(),
                Type.PLANE);
        parachutists = new LinkedList<>();
        movementManager = new MovementManager(plane, boat, parachutists, information);
        inputHandler = new InputHandler(movementManager);
        screenManager = new ScreenManager(imageHandler, inputHandler);

        gameLoop();
    }

    // keeps the game running, while updating the objects
    private void gameLoop() {
        while (information.getLives() > 0) {
            moveObjectsAndRemoveIfNeeded();
            createNewParachuter();
            paint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
        }
        gameOver();
    }

    // updates objects location and remove if needed
    public void moveObjectsAndRemoveIfNeeded() {
        movementManager.movePlane();
        movementManager.moveParachutists();
    }

    // randomly create new parachutists
    private void createNewParachuter() {
        if (information.getNumberOfParachutists() < MAX_PARACHUTISTS) {

            int n = rand.nextInt(500);
            if (n < 3) {
                GameObject parachutist = new GameObject(
                        plane.getX(),
                        plane.getY(),
                        PARACHUTIST_MOVEMENT_SPEED,
                        imageHandler.getParachutistImage().getHeight(),
                        imageHandler.getParachutistImage().getWidth(),
                        Type.PARACHUTIST);
                addParachutist(parachutist);
            }
        }
    }

    // paiting the objects, lives and score on the screen
    public void paint() {
        screenManager.drawObjects(getDrawables(), information.getLives(), information.getScore());
    }

    // finish the game
    public void gameOver() {
        screenManager.gameOver();
    }

    public void addParachutist(GameObject parachutist) {
        parachutists.add(parachutist);
        information.addParachutist();
    }

    public LinkedList<GameObject> getDrawables() {
        LinkedList<GameObject> drawables = (LinkedList<GameObject>) parachutists.clone();
        drawables.addFirst(plane);
        drawables.addFirst(boat);
        return drawables;
    }

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
    }

}
