
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import GameObjects.GameObject;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Responsible for all the drawing and interactions that appear on screen
 */
public class ScreenManager extends JPanel implements KeyListener {

    private final GameData gameData;
    private final JFrame frame;
    private final Font monoFont;
    public static BufferedImage boatImage;
    public static BufferedImage planeImage;
    public static BufferedImage parachutistImage;
    public static BufferedImage seaImage;
    public static BufferedImage backgroundImage;

    static {
        try {
            boatImage = ImageIO.read(GameData.class.getResourceAsStream(GameData.BOAT_IMAGE));
            planeImage = ImageIO.read(GameData.class.getResourceAsStream(GameData.PLANE_IMAGE));
            parachutistImage = ImageIO.read(GameData.class.getResourceAsStream(GameData.PARACHUTIST_IMAGE));
            seaImage = ImageIO.read(GameData.class.getResourceAsStream(GameData.SEA_IMAGE));
            backgroundImage = ImageIO.read(GameData.class.getResourceAsStream(GameData.BACKGROUND_IMAGE));

        } catch (IOException e) {
            System.out.println("Error loading images");
            System.exit(ABORT);
        }
    }

    public ScreenManager() {

        gameData = GameData.getInstance();
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

        g2d.drawImage(backgroundImage, 0, 0, this);
        g2d.drawImage(seaImage, 0, GameData.WINDOW_Y_SIZE - GameData.SEA_HEIGHT, this);

        GameObject plane = gameData.getPlane();
        GameObject boat = gameData.getBoat();
        g2d.drawImage(planeImage, plane.getX(), plane.getY(), this);
        g2d.drawImage(boatImage, boat.getX(), boat.getY(), this);

        for (GameObject gameObject : gameData.getParachutists()) {
            g2d.drawImage(parachutistImage, gameObject.getX(), gameObject.getY(), this);
        }
        g.setFont(monoFont);
        g.drawString("Lives: " + gameData.getLives(), 0, 220);
        g.drawString("Score: " + gameData.getScore(), 0, 250);
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
