package GameObjects;

import GameData.GameData;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 *
 * Represents a Parachutist
 */
public class Parachutist extends InteractableObject implements GameObject {

    private final static String PARACHUTIST_IMAGE = "C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\parachutist.png";
    private BufferedImage image;
    private int fallSpeed;
    private final int parachutistHeight;
    private final int parachutistWidth;
    private boolean needToRemove;
    private final GameData gameData;
    private final LinkedList<InteractableObject> interactableObjects;

    public Parachutist(int x, int y, LinkedList<InteractableObject> interactableObjects) {

        try {
            image = ImageIO.read(new File(PARACHUTIST_IMAGE));
        } catch (IOException e) {

        }
        this.gameData = GameData.getInstance();
        this.interactableObjects = interactableObjects;
        this.x = x;
        this.y = y;
        this.fallSpeed = 1;
        this.needToRemove = false;
        this.parachutistHeight = image.getHeight();
        this.parachutistWidth = image.getWidth();
    }

    @Override
    public void move() {
        y += fallSpeed;
        checkForInteraction();
    }

    public void checkForInteraction() {
        for (InteractableObject interactableObject : interactableObjects) {
            if (isCollision(interactableObject)) {
                String res = parachutistCollideWith(interactableObject);
                switch (res) {
                    case GameData.PARACHUTIST_CAUGHT:
                        needToRemove = true;
                        gameData.parachuterCaught();
                        break;

                    case GameData.PARACHUTIST_DROWNED:
                        needToRemove = true;
                        gameData.parachuterDrowned();
                        break;

                    default:
                        System.out.println("Error");
                }
            }
        }
    }

    // checks if the parachutists landed on the boat
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
        return new Rectangle(x, y, parachutistWidth, parachutistHeight);
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

    public int getFallSpeed() {
        return fallSpeed;
    }

    public void setFallSpeed(int fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean needToRemove() {
        return needToRemove;
    }

}
