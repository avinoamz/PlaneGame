
package GameObjects;

import GameData.GameData;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author avino
 */
public class Sea extends InteractableObject {

    private BufferedImage image;
    private final int seaHeight;
    private final int seaWidth;

    public Sea() {
        try {
            image = ImageIO.read(Boat.class.getResourceAsStream(GameData.SEA_IMAGE));
        } catch (IOException e) {

        }
        this.x = 0;
        this.y = GameData.WINDOW_Y_SIZE - GameData.SEA_HEIGHT / 2;
        this.seaHeight = image.getHeight();
        this.seaWidth = image.getWidth();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, seaWidth, seaHeight);
    }

    @Override
    public boolean isCollision(InteractableObject interactableObject) {
        return (interactableObject.getBounds().intersects(getBounds()));
    }

}
