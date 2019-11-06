
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Responsible for all the drawing that appear on screen
 */
public class ScreenManager extends JPanel implements KeyListener {

    private final JFrame frame;
    private final Font monoFont;
    public static BufferedImage boatImage;
    public static BufferedImage planeImage;
    public static BufferedImage parachutistImage;
    public static BufferedImage seaImage;
    public static BufferedImage backgroundImage;

    static {
        try {
            boatImage = ImageIO.read(ScreenManager.class.getResourceAsStream(GameData.BOAT_IMAGE));
            planeImage = ImageIO.read(ScreenManager.class.getResourceAsStream(GameData.PLANE_IMAGE));
            parachutistImage = ImageIO.read(ScreenManager.class.getResourceAsStream(GameData.PARACHUTIST_IMAGE));
            seaImage = ImageIO.read(ScreenManager.class.getResourceAsStream(GameData.SEA_IMAGE));
            backgroundImage = ImageIO.read(ScreenManager.class.getResourceAsStream(GameData.BACKGROUND_IMAGE));

        } catch (IOException e) {
            System.out.println("Error loading images");
            System.exit(ABORT);
        }
    }

    public ScreenManager() {
        monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 36);
        frame = new JFrame("Parachutist's");
        frame.setSize(GameData.WINDOW_X_SIZE, GameData.WINDOW_Y_SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setVisible(true);
    }

    @Override
    // draw all the objects on the screen
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(backgroundImage, 0, 0, this);
        g2d.drawImage(seaImage, 0, GameData.WINDOW_Y_SIZE - GameData.SEA_HEIGHT, this);

        GameData gameData = GameData.getInstance();
        for (GameObject gameObject : gameData.getDrawables()) {
            g2d.drawImage(getImageByType(gameObject), gameObject.getX(), gameObject.getY(), this);
        }
        
        g.setFont(monoFont);
        g.drawString("Lives: " + gameData.getLives(), 0, 220);
        g.drawString("Score: " + gameData.getScore(), 0, 250);
    }

    private BufferedImage getImageByType(GameObject gameObject) {
        switch (gameObject.getType()) {
            case PARACHUTIST:
                return parachutistImage;
            case BOAT:
                return boatImage;
            case PLANE:
                return planeImage;
            default:
                System.out.println("Unknown type");
                return null;
        }
    }

    // display a message when the game is over
    public void gameOver() {
        JOptionPane.showMessageDialog(this, GameData.GAME_OVER, GameData.GAME_OVER, JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        GameData.getInstance().getMovementManager().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
