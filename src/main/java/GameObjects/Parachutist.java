package GameObjects;

import GameData.GameData;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * Represents a Parachutist
 */
public class Parachutist implements GameObject {

    private final static String parachutistImage = "C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\parachutist.png";
    private BufferedImage image;
    private final Boat boat;
    private int x;
    private int y;
    private int fallSpeed;
    private final int parachutistHeight;
    private final int parachutistWidth;
    private boolean needToRemove;
    GameData gameData;

    public Parachutist(int x, int y, Boat boat) {

        try {
            image = ImageIO.read(new File(parachutistImage));
        } catch (IOException e) {

        }
        this.gameData = GameData.getInstance();
        this.boat = boat;
        this.x = x;
        this.y = y;
        this.fallSpeed = 1;
        this.needToRemove = false;
        this.parachutistHeight = image.getHeight();
        this.parachutistWidth = image.getWidth();
    }

    @Override
    public void move() {
        if (y > 0) {
            y += fallSpeed;
        }
        if (isCollision()) {
            needToRemove = true;
            gameData.parachuterCatched();
        } else if (y > (gameData.getWindowYSize() - gameData.getSeaHeight())) {
            needToRemove = true;
            gameData.parachuterDrowned();
        }
    }
    
    // checks if the parachutists landed on the boat
    private boolean isCollision() {
        return (boat.getBounds().intersects(getBounds()));
    }

    /* 
        checks the bounds of the object in order to check collosion with other units
        height is divided because parachutists land on the deck and not on top of the boat
     */
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

    @Override
    public boolean needToRemove() {
        return needToRemove;
    }

}
