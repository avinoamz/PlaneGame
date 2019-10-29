package GameObjects;

import GameData.GameData;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * Represents a Boat
 */
public class Boat extends InteractableObject implements GameObject, ControlledObject {

    private final static String BOAT_IMAGE = "C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\boat.png";
    private int x;
    private int y;
    private int dx;
    private final int boatHeight;
    private final int boatWidth;
    private BufferedImage image;
    private final GameData gameData;

    public Boat() {
        try {
            image = ImageIO.read(new File(BOAT_IMAGE));
        } catch (IOException e) {

        }
        gameData = GameData.getInstance();
        this.x = gameData.getWindowXSize() / 2;
        this.y = gameData.getWindowYSize() - gameData.getSeaHeight();
        this.dx = 0;
        boatHeight = image.getHeight();
        boatWidth = image.getWidth();
    }

    @Override
    public void move() {
        x += dx;
    }

    @Override
    public boolean isCollision(InteractableObject interactableObject) {
        return (interactableObject.getBounds().intersects(getBounds()));
    }

    /* 
        checks the bounds of the object in order to check collosion with other units
        height is divided because parachutists land on the deck and not on top of the boat
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y + (boatHeight / 2), boatWidth, boatHeight);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -3;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 3;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public boolean needToRemove() {
        return false;
    }

}
