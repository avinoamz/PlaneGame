
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Responsible for all the drawing that appear on screen
 */
public class ScreenManager extends JPanel implements KeyListener {

    private final JFrame frame;
    private final Font monoFont;

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
        GameData gameData = GameData.getInstance();
        ImageHandler imageHandler = GameData.getInstance().getImageHandler();
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(imageHandler.getBackgroundImage(), 0, 0, this);
        g2d.drawImage(imageHandler.getSeaImage(), 0, GameData.WINDOW_Y_SIZE - GameData.SEA_HEIGHT, this);

        gameData.getDrawables().forEach((gameObject) -> {
            g2d.drawImage(imageHandler.getImageByType(gameObject), gameObject.getX(), gameObject.getY(), this);
        });

        g.setFont(monoFont);
        g.drawString("Lives: " + gameData.getLives(), 0, 220);
        g.drawString("Score: " + gameData.getScore(), 0, 250);
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
        GameData.getInstance().getInputHandler().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
