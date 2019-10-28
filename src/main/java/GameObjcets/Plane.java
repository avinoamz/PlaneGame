/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjcets;

import GameData.GameData;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author avino
 */
public class Plane implements GameObject {

    private int x;
    private int y;
    private BufferedImage image;

    public Plane() {
        try {
            image = ImageIO.read(new File("C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\plane.png"));
        } catch (IOException e) {

        }
        this.x = GameData.getWindowXSize() - image.getWidth();
        this.y = 10;
    }

    @Override
    public void move() {
        x -= 2;
        if (x - 2 < 30) {
            x = GameData.getWindowXSize();
        }
    }

    @Override
    public void draw(Graphics2D g, ImageObserver o) {
        g.drawImage(image, this.x, this.y, o);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
