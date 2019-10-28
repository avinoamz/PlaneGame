
import GameData.GameData;
import GameObjcets.Boat;
import GameObjcets.ParachutistManager;
import GameObjcets.Plane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author avino
 */
public class GameScreen extends JPanel {

    private final Boat boat;
    private final Plane plane;
    private final ParachutistManager parachutistManager;
    private final KeyboardListener keyListener;
    private final JFrame frame;
    private final Font monoFont;
    private BufferedImage background, sea;

    public GameScreen() {
        try {
            background = ImageIO.read(new File("C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\background.png"));
            sea = ImageIO.read(new File("C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\sea.png"));
        } catch (IOException e) {

        }
        boat = new Boat(sea.getHeight());
        plane = new Plane();
        parachutistManager = new ParachutistManager(sea.getHeight());
        keyListener = new KeyboardListener(boat);
        monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 36);
        frame = new JFrame("Parachutist's");
        frame.setSize(GameData.getWindowXSize(), GameData.getWindowYSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(keyListener);
        frame.setVisible(true);

        gameLoop();
    }

    private void gameLoop() {
        // need to change to lives > 0
        while (GameData.getLives() > 0) {
            moveObjects();
            parachutistManager.createNewParachutists(plane.getX(), plane.getY(), boat);
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
        }
        gameOver();
    }

    public void moveObjects() {
        plane.move();
        boat.move();
        parachutistManager.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, this);
        g2d.drawImage(sea, 0, GameData.getWindowYSize() - sea.getHeight(), this);
        plane.draw(g2d, this);
        boat.draw(g2d, this);
        parachutistManager.draw(g2d, this);
        g.setFont(monoFont);
        g.drawString("Lives: " + GameData.getLives(), 0, 220);
        g.drawString("Score: " + GameData.getScore(), 0, 250);
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    public static void main(String[] args) {
        GameScreen gameScreen1 = new GameScreen();
    }

}
