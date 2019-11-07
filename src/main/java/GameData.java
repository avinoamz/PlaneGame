
import java.util.LinkedList;

/**
 *
 * Responsible for holding the data of the game. Singleton
 */
public class GameData {

    private static GameData instance = null;

    public static final int LIVES = 3;
    public static final int GAIN_POINTS = 10;
    public static final int PARACHUTIST_MOVEMENT_SPEED = 1;
    public static final int BOAT_MOVEMENT_SPEED = 6;
    public static final int PLANE_MOVEMENT_SPEED = 1;
    public static final int WINDOW_X_SIZE = 1080;
    public static final int WINDOW_Y_SIZE = 720;
    public static final int SEA_HEIGHT = 242;
    public static final int MAX_PARACHUTISTS = 3;
    public static final String GAME_OVER = "GAME_OVER";

    private int lives;
    private int score;
    private int currentParachuters;

    private GameObject plane;
    private GameObject boat;
    private LinkedList<GameObject> parachutists;
    private MovementManager movementManager;
    private InputHandler inputHandler;
    private ScreenManager screenManager;
    private ImageHandler imageHandler;

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    private GameData() {
        this.lives = LIVES;
        this.score = 0;
        this.currentParachuters = 0;

        movementManager = new MovementManager();
        imageHandler = new ImageHandler();
        screenManager = new ScreenManager();
        inputHandler = new InputHandler();

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

    }

    public void addParachutist(GameObject parachutist) {
        parachutists.add(parachutist);
        currentParachuters++;
    }

    public void removeDrownedParachutist(GameObject parachutist) {
        parachutists.remove(parachutist);
        currentParachuters--;
        lives--;
    }

    public void removeCaughtParachutist(GameObject parachutist) {
        parachutists.remove(parachutist);
        currentParachuters--;
        score += GAIN_POINTS;
    }

    public LinkedList<GameObject> getDrawables() {
        LinkedList<GameObject> drawables = (LinkedList<GameObject>) parachutists.clone();
        drawables.addFirst(getPlane());
        drawables.addFirst(getBoat());
        return drawables;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentParachuters() {
        return currentParachuters;
    }

    public void setCurrentParachuters(int currentParachuters) {
        this.currentParachuters = currentParachuters;
    }

    public GameObject getPlane() {
        return plane;
    }

    public void setPlane(GameObject plane) {
        this.plane = plane;
    }

    public GameObject getBoat() {
        return boat;
    }

    public void setBoat(GameObject boat) {
        this.boat = boat;
    }

    public LinkedList<GameObject> getParachutists() {
        return parachutists;
    }

    public void setParachutists(LinkedList<GameObject> parachutists) {
        this.parachutists = parachutists;
    }

    public MovementManager getMovementManager() {
        return movementManager;
    }

    public void setMovementManager(MovementManager movementManager) {
        this.movementManager = movementManager;
    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }

    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public ImageHandler getImageHandler() {
        return imageHandler;
    }

    public void setImageHandler(ImageHandler imageHandler) {
        this.imageHandler = imageHandler;
    }

}
