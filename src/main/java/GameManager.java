
import java.util.Random;

/**
 *
 * The class that operates the game's flow
 */
public class GameManager {

    private final GameData gameData;
    private final Random rand;

    // Create all the base objects and start the game
    public GameManager() {
        gameData = GameData.getInstance();
        rand = new Random();

        gameLoop();
    }

    // keeps the game running, while updating the objects
    private void gameLoop() {
        while (gameData.getLives() > 0) {
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
        gameData.getMovementManager().movePlane();
        gameData.getMovementManager().moveParachutists();
    }

    // randomly create new parachutists
    private void createNewParachuter() {
        if (gameData.getCurrentParachuters() < GameData.MAX_PARACHUTISTS) {

            int n = rand.nextInt(500);
            if (n < 3) {
                GameObject parachutist = new GameObject(
                        gameData.getPlane().getX(),
                        gameData.getPlane().getY(),
                        GameData.PARACHUTIST_MOVEMENT_SPEED,
                        gameData.getImageHandler().getParachutistImage().getHeight(),
                        gameData.getImageHandler().getParachutistImage().getWidth(),
                        Type.PARACHUTIST);
                gameData.addParachutist(parachutist);
            }
        }
    }

    // paiting the objects on the screen
    public void paint() {
        gameData.getScreenManager().repaint();
    }

    // finish the game
    public void gameOver() {
        gameData.getScreenManager().gameOver();
    }

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
    }

}
