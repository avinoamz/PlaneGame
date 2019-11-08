package Model;

/**
 *
 * Holds basic game information
 */
public class Information {

    public static final int STARTING_LIVES = 3;
    public static final int POINTS_TO_GAIN = 10;
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
    private int numberOfParachutists;

    public Information() {
        this.lives = STARTING_LIVES;
        this.score = 0;
        this.numberOfParachutists = 0;
    }

    public void parachuterDrowned() {
        numberOfParachutists--;
        lives--;
    }

    public void parachuterCaught() {
        numberOfParachutists--;
        score += POINTS_TO_GAIN;
    }

    public void addParachutist() {
        numberOfParachutists++;
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

    public int getNumberOfParachutists() {
        return numberOfParachutists;
    }

    public void setNumberOfParachutists(int numberOfParachutists) {
        this.numberOfParachutists = numberOfParachutists;
    }

}
