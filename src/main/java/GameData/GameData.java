
package GameData;

/**
 *
 * Responsible for holding the data of the game. Singleton
 */
public class GameData {

    private static GameData instance = null;

    public final static String BOAT_IMAGE = "C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\boat.png";
    public final static String PLANE_IMAGE = "C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\plane.png";
    public final static String PARACHUTIST_IMAGE = "C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\parachutist.png";
    public final static String SEA_IMAGE = "C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\sea.png";
    public final static String BACKGROUND_IMAGE = "C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\background.png";

    public static final String PARACHUTIST_CAUGHT = "PARACHUTIST_CAUGHT";
    public static final String PARACHUTIST_DROWNED = "PARACHUTIST_DROWNED";
    public static final String GAME_OVER = "GAME_OVER";
    
    public static final int LIVES = 3;
    public static final int GAIN_POINTS = 10;
    public static final int PARACHUTIST_FALL_SPEED = 1;
    public static final int WINDOW_X_SIZE = 1080;
    public static final int WINDOW_Y_SIZE = 720;
    public static final int SEA_HEIGHT = 242;
    public static final int MAX_PARACHUTISTS = 3;

    private int lives;
    private int score;
    private int currentParachuters;

    private GameData() {
        this.lives = LIVES;
        this.score = 0;
        this.currentParachuters = 0;
    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    public void addParachutist() {
        currentParachuters++;
    }

    public void parachuterDrowned() {
        currentParachuters--;
        lives--;
    }

    public void parachuterCaught() {
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

}
