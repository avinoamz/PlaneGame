
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import GameObjects.GameObject;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Responsible for all the drawing and interactions that appear on screen
 */
public class ScreenManager extends JPanel implements KeyListener {

    private final LinkedList<GameObject> gameObjects;
    private final ArrayList<GameObject> needToRemoveGameObjects;
    private final GameData gameData;
//    private final KeyboardListener keyListener;
    private final JFrame frame;
    private final Font monoFont;

    public ScreenManager() {

        gameData = GameData.getInstance();
        gameObjects = new LinkedList<>();
        gameObjects.add(gameData.getBackground());
        gameObjects.add(gameData.getSea());
        gameObjects.add(gameData.getPlane());
        gameObjects.add(gameData.getBoat());
        this.needToRemoveGameObjects = new ArrayList<>();

//        keyListener = new KeyboardListener(gameObjects);
        monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 36);
        frame = new JFrame("Parachutist's");
        frame.setSize(GameData.WINDOW_X_SIZE, GameData.WINDOW_Y_SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for (GameObject gameObject : gameObjects) {
            BufferedImage image = getImageByType(gameObject);
            g2d.drawImage(image, gameObject.getX(), gameObject.getY(), this);
        }
        for (GameObject gameObject : gameData.getParachutists()) {
            BufferedImage image = getImageByType(gameObject);
            g2d.drawImage(image, gameObject.getX(), gameObject.getY(), this);
        }
        g.setFont(monoFont);
        g.drawString("Lives: " + gameData.getLives(), 0, 220);
        g.drawString("Score: " + gameData.getScore(), 0, 250);
    }

    private BufferedImage getImageByType(GameObject gameObject) {
        switch (gameObject.getType()) {
            case PARACHUTIST:
                return GameData.parachutistImage;
            case PLANE:
                return GameData.planeImage;
            case BOAT:
                return GameData.boatImage;
            case SEA:
                return GameData.seaImage;
            case BACKGROUND:
                return GameData.backgroundImage;
            default:
                return null;
        }
    }

    public void addObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, GameData.GAME_OVER, GameData.GAME_OVER, JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameData.getMovementManager().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gameData.getMovementManager().keyReleased(e);
    }

}
