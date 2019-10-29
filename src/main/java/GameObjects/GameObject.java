package GameObjects;

import java.awt.image.BufferedImage;

/**
 *
 * An interface to represent a Game Object
 */
public interface GameObject {

    public BufferedImage getImage();

    public int getX();

    public int getY();

    public void move();
    
    public boolean needToRemove();

}
