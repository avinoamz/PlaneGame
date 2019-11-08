package Model;

import Screen.ImageHandler;
import Movement.InputHandler;
import Movement.MovementInterface;
import Movement.MovementManager;
import Screen.ScreenInterface;
import Screen.ScreenManager;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * The class that operates the game's flow
 */
public class GameManager {

    private final Random rand;
    private final Information information;
    private final GameObject plane;
    private final GameObject boat;
    private final LinkedList<GameObject> parachutists;
    private final MovementInterface movementInterface;
    private final InputHandler inputHandler;
    private final ScreenInterface screenInterface;
    private final ImageHandler imageHandler;

    // Create all the base objects and start the game
    public GameManager() {
        rand = new Random();
        information = new Information();

        imageHandler = new ImageHandler();
        boat = new GameObject(
                Information.WINDOW_X_SIZE / 2,
                Information.WINDOW_Y_SIZE - Information.SEA_HEIGHT,
                0,
                imageHandler.getBoatImage().getHeight(),
                imageHandler.getBoatImage().getWidth(),
                Type.BOAT);
        plane = new GameObject(
                Information.WINDOW_X_SIZE - imageHandler.getPlaneImage().getWidth(),
                0,
                Information.PLANE_MOVEMENT_SPEED,
                imageHandler.getPlaneImage().getHeight(),
                imageHandler.getPlaneImage().getWidth(),
                Type.PLANE);
        parachutists = new LinkedList<>();
        movementInterface = new MovementManager(plane, boat, parachutists, information);
        inputHandler = new InputHandler(movementInterface);
        screenInterface = new ScreenManager(imageHandler, inputHandler);

        gameLoop();
    }

    // keeps the game running, while updating the objects
    private void gameLoop() {
        while (information.getLives() > 0) {
            moveObjectsAndRemoveIfNeeded();
            createNewParachuter();
            paint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
        gameOver();
    }

    // updates objects location and remove if needed
    public void moveObjectsAndRemoveIfNeeded() {
        movementInterface.movePlane();
        movementInterface.moveParachutists();
    }

    // randomly create new parachutists
    private void createNewParachuter() {
        if (information.getNumberOfParachutists() < Information.MAX_PARACHUTISTS) {

            int n = rand.nextInt(500);
            if (n < 3) {
                GameObject parachutist = new GameObject(
                        plane.getX(),
                        plane.getY(),
                        Information.PARACHUTIST_MOVEMENT_SPEED,
                        imageHandler.getParachutistImage().getHeight(),
                        imageHandler.getParachutistImage().getWidth(),
                        Type.PARACHUTIST);
                addParachutist(parachutist);
            }
        }
    }

    // paiting the objects, lives and score on the screen
    public void paint() {
        screenInterface.drawObjects(getDrawables(), information.getLives(), information.getScore());
    }

    // finish the game
    public void gameOver() {
        screenInterface.gameOver();
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
