
import GameData.GameData;
import GameObjects.GameObject;
import GameObjects.InteractableObject;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * Responsible for all the drawing and interactions that appear on screen
 */
public class ScreenManager extends JPanel {

    private final static String BACKGROUND_IMAGE = "C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\background.png";
    private final static String SEA_IMAGE = "C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\sea.png";

    private final ArrayList<GameObject> gameObjects;
    private final ArrayList<GameObject> needToRemoveGameObjects;
    private final ArrayList<InteractableObject> needToRemoveInteractableObjects;
    private final GameData gameData;
    private final KeyboardListener keyListener;
    private final JFrame frame;
    private final Font monoFont;
    private BufferedImage background, sea;

    public ScreenManager(ArrayList<GameObject> gameObjects) {
        try {
            background = ImageIO.read(new File(BACKGROUND_IMAGE));
            sea = ImageIO.read(new File(SEA_IMAGE));
        } catch (IOException e) {

        }
        this.gameObjects = gameObjects;
        this.needToRemoveGameObjects = new ArrayList<>();
        this.needToRemoveInteractableObjects = new ArrayList<>();
        gameData = GameData.getInstance();
        keyListener = new KeyboardListener(gameObjects);
        monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 36);
        frame = new JFrame("Parachutist's");
        frame.setSize(gameData.getWindowXSize(), gameData.getWindowYSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(keyListener);
        frame.setVisible(true);
    }

    public void moveObjects() {
        for (GameObject gameObject : gameObjects) {
            gameObject.move();
            if (gameObject.needToRemove()) {
                needToRemoveGameObjects.add(gameObject);
            }
        }
        gameObjects.removeAll(needToRemoveGameObjects);
        needToRemoveGameObjects.clear();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, this);
        g2d.drawImage(sea, 0, gameData.getWindowYSize() - gameData.getSeaHeight(), this);
        for (GameObject gameObject : gameObjects) {
            g2d.drawImage(gameObject.getImage(), gameObject.getX(), gameObject.getY(), this);
        }
        g.setFont(monoFont);
        g.drawString("Lives: " + gameData.getLives(), 0, 220);
        g.drawString("Score: " + gameData.getScore(), 0, 250);
    }

    public void addObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, GameData.GAME_OVER, GameData.GAME_OVER, JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

}
