
import GameObjects.Type;
import GameObjects.GameObject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;

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

    public static BufferedImage boatImage;
    public static BufferedImage planeImage;
    public static BufferedImage parachutistImage;
    public static BufferedImage seaImage;
    public static BufferedImage backgroundImage;

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
    private GameObject sea;
    private GameObject background;
    private LinkedList<GameObject> parachutists;
    private MovementManager movementManager;

    static {
        try {
            boatImage = ImageIO.read(GameData.class.getResourceAsStream(GameData.BOAT_IMAGE));
            planeImage = ImageIO.read(GameData.class.getResourceAsStream(GameData.PLANE_IMAGE));
            parachutistImage = ImageIO.read(GameData.class.getResourceAsStream(GameData.PARACHUTIST_IMAGE));
            seaImage = ImageIO.read(GameData.class.getResourceAsStream(GameData.SEA_IMAGE));
            backgroundImage = ImageIO.read(GameData.class.getResourceAsStream(GameData.BACKGROUND_IMAGE));

        } catch (IOException e) {

        }
    }

    private GameData() {
        this.lives = LIVES;
        this.score = 0;
        this.currentParachuters = 0;

        boat = new GameObject(WINDOW_X_SIZE / 2, WINDOW_Y_SIZE - SEA_HEIGHT, Type.BOAT, BOAT_MOVEMENT_SPEED, boatImage.getHeight(), boatImage.getWidth());
        plane = new GameObject(WINDOW_X_SIZE - planeImage.getWidth(), 0, Type.PLANE, PLANE_MOVEMENT_SPEED, planeImage.getHeight(), planeImage.getWidth());
        sea = new GameObject(0, WINDOW_Y_SIZE - SEA_HEIGHT, Type.SEA, 0, seaImage.getHeight(), seaImage.getWidth());
        background = new GameObject(0, 0, Type.BACKGROUND, 0, backgroundImage.getHeight(), backgroundImage.getWidth());
        parachutists = new LinkedList<>();

        movementManager = new MovementManager();
    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
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

    public static BufferedImage getBoatImage() {
        return boatImage;
    }

    public static void setBoatImage(BufferedImage boatImage) {
        GameData.boatImage = boatImage;
    }

    public static BufferedImage getPlaneImage() {
        return planeImage;
    }

    public static void setPlaneImage(BufferedImage planeImage) {
        GameData.planeImage = planeImage;
    }

    public static BufferedImage getParachutistImage() {
        return parachutistImage;
    }

    public static void setParachutistImage(BufferedImage parachutistImage) {
        GameData.parachutistImage = parachutistImage;
    }

    public static BufferedImage getSeaImage() {
        return seaImage;
    }

    public static void setSeaImage(BufferedImage seaImage) {
        GameData.seaImage = seaImage;
    }

    public static BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public static void setBackgroundImage(BufferedImage backgroundImage) {
        GameData.backgroundImage = backgroundImage;
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

    public GameObject getSea() {
        return sea;
    }

    public void setSea(GameObject sea) {
        this.sea = sea;
    }

    public GameObject getBackground() {
        return background;
    }

    public void setBackground(GameObject background) {
        this.background = background;
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

}
