
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

/**
 * Responsible for all the drawing that appear on screen
 */
public class ScreenManager extends JPanel implements KeyListener {

    private final JFrame frame;
    private final Font monoFont;
    private final ImageHandler imageHandler;
    private final InputHandler inputHandler;
    private LinkedList<GameObject> gameObjects;
    private int lives, score;

    public ScreenManager(ImageHandler imageHandler, InputHandler inputHandler) {
        monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 36);
        frame = new JFrame("Parachutist's");
        frame.setSize(Information.WINDOW_X_SIZE, Information.WINDOW_Y_SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setVisible(true);
        this.imageHandler = imageHandler;
        this.inputHandler = inputHandler;
    }

    public void drawObjects(LinkedList<GameObject> gameObjects, int lives, int score) {
        this.gameObjects = gameObjects;
        this.lives = lives;
        this.score = score;
        repaint();
    }

    @Override
    // draw all the objects on the screen
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(imageHandler.getBackgroundImage(), 0, 0, this);
        g2d.drawImage(imageHandler.getSeaImage(), 0, Information.WINDOW_Y_SIZE - Information.SEA_HEIGHT, this);

        gameObjects.forEach((gameObject) -> {
            g2d.drawImage(imageHandler.getImageByType(gameObject), gameObject.getX(), gameObject.getY(), this);
        });

        g.setFont(monoFont);
        g.drawString("Lives: " + lives, 0, 220);
        g.drawString("Score: " + score, 0, 250);
    }

    // display a message when the game is over
    public void gameOver() {
        JOptionPane.showMessageDialog(this, Information.GAME_OVER, Information.GAME_OVER, JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputHandler.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
