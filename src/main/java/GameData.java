
import java.util.LinkedList;

/**
 *
 * Responsible for holding the data of the game. Singleton
 */
public class GameData {

    private static GameData instance = null;

    public final static String BOAT_IMAGE = "/boat.png";
    public final static String PLANE_IMAGE = "/plane.png";
    public final static String PARACHUTIST_IMAGE = "/parachutist.png";
    public final static String SEA_IMAGE = "/sea.png";
    public final static String BACKGROUND_IMAGE = "/background.png";

    public static final String PARACHUTIST_CAUGHT = "PARACHUTIST_CAUGHT";
    public static final String PARACHUTIST_DROWNED = "PARACHUTIST_DROWNED";
    public static final String GAME_OVER = "GAME_OVER";

    public static final int LIVES = 3;
    public static final int GAIN_POINTS = 10;
    public static final int PARACHUTIST_FALL_SPEED = 1;
    public static final int BOAT_MOVEMENT_SPEED = 3;
    public static final int PLANE_MOVEMENT_SPEED = 1;
    public static final int WINDOW_X_SIZE = 1080;
    public static final int WINDOW_Y_SIZE = 720;
    public static final int SEA_HEIGHT = 242;
    public static final int MAX_PARACHUTISTS = 3;

    private int lives;
    private int score;
    private int currentParachuters;

    private GameObject plane;
    private GameObject boat;
    private LinkedList<GameObject> parachutists;
    private MovementManager movementManager;
    private ScreenManager screenManager;

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

        boat = new GameObject(
                WINDOW_X_SIZE / 2,
                WINDOW_Y_SIZE - SEA_HEIGHT,
                BOAT_MOVEMENT_SPEED,
                ScreenManager.boatImage.getHeight(),
                ScreenManager.boatImage.getWidth(),
                Type.BOAT);
        plane = new GameObject(
                WINDOW_X_SIZE - ScreenManager.planeImage.getWidth(),
                0,
                PLANE_MOVEMENT_SPEED,
                ScreenManager.planeImage.getHeight(),
                ScreenManager.planeImage.getWidth(),
                Type.PLANE);

        parachutists = new LinkedList<>();

        movementManager = new MovementManager();
        screenManager = new ScreenManager();
    }

    public void addParachutist(GameObject parachutist) {
        parachutists.add(parachutist);
        currentParachuters++;
    }

    public void parachuterDrowned(GameObject parachutist) {
        parachutists.remove(parachutist);
        currentParachuters--;
        lives--;
    }

    public void parachuterCaught(GameObject parachutist) {
        parachutists.remove(parachutist);
        currentParachuters--;
        score += GAIN_POINTS;
    }

    public LinkedList<GameObject> getDrawables() {
        LinkedList<GameObject> drawables = (LinkedList<GameObject>) parachutists.clone();
        drawables.addFirst(getPlane());
        drawables.addFirst(getBoat());
        System.out.println(drawables);
        System.out.println(parachutists);
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

}
