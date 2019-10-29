
import GameData.GameData;
import GameObjects.GameObject;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * Responsible for all the drawing and interactions that appear on screen
 */
public class ScreenManager extends JPanel {

    private final static String backgroundImage = "C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\background.png";
    private final static String seaImage = "C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\sea.png";

    private final ArrayList<GameObject> gameObjects;
    private final ArrayList<GameObject> needToRemoveObjects;
    private final GameData gameData;
    private final KeyboardListener keyListener;
    private final JFrame frame;
    private final Font monoFont;
    private BufferedImage background, sea;

    public ScreenManager(ArrayList<GameObject> gameObjects) {
        try {
            background = ImageIO.read(new File(backgroundImage));
            sea = ImageIO.read(new File(seaImage));
        } catch (IOException e) {

        }
        this.gameObjects = gameObjects;
        this.needToRemoveObjects = new ArrayList<>();
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
                needToRemoveObjects.add(gameObject);
            }
        }
        gameObjects.removeAll(needToRemoveObjects);
        needToRemoveObjects.clear();
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
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

}
