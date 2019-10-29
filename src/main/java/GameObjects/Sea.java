/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    private final static String SEA_IMAGE = "C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\sea.png";

    private BufferedImage image;
    private int seaHeight;
    private int seaWidth;

    public Sea() {
        try {
            image = ImageIO.read(new File(SEA_IMAGE));
        } catch (IOException e) {

        }
        GameData gameData = GameData.getInstance();
        this.x = 0;
        this.y = gameData.getWindowYSize() - gameData.getSeaHeight() / 2;
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
