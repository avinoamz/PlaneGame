package GameObjects;

import GameData.GameData;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * Represents a Plane
 */
public class Plane implements GameObject {

    private final static String planeImage = "C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\plane.png";
    private int x;
    private int y;
    private final int startingPosition;
    private BufferedImage image;

    public Plane() {
        try {
            image = ImageIO.read(new File(planeImage));
        } catch (IOException e) {

        }
        startingPosition = GameData.getInstance().getWindowXSize() - image.getWidth();
        this.x = startingPosition;
        this.y = 10;
    }

    @Override
    public void move() {
        x -= 2;
        if (x - 2 < 0) {
            x = startingPosition;
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
