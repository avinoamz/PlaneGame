/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjcets;

import GameData.GameData;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author avino
 */
public class Parachutist implements GameObject {

    private BufferedImage image;
    private Boat boat;
    private int x;
    private int y;
    private int width;
    private int height;
    private int fallSpeed;
    private boolean isAlive;
    private int seaHeight;
    //replace image
    BufferedImage bufferedImage = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);

    public Parachutist(int x, int y, Boat boat, int seaHeight) {

        try {
            image = ImageIO.read(new File("C:\\\\Users\\\\avino\\\\Documents\\\\NetBeansProjects\\\\mavenproject1\\\\Matific\\\\src\\\\main\\\\java\\\\resources\\\\parachutist.png"));
        } catch (IOException e) {

        }
        this.boat = boat;
        this.x = x;
        this.y = y;
        this.width = 5;
        this.height = 5;
        this.fallSpeed = 1;
        this.isAlive = true;
        this.seaHeight = seaHeight;
    }

    @Override
    public void move() {
        if (y > 0) {
            y += fallSpeed;
        }
        if (isCollision()) {
            isAlive = false;
            GameData.setScore(GameData.getScore() + 10);
            System.out.println(GameData.getScore());
        } else if (y > (GameData.getWindowYSize() - seaHeight)) {
            isAlive = false;
            GameData.setLives(GameData.getLives() - 1);
            System.out.println("dieded");
            System.out.println(seaHeight);
        }
    }

    @Override
    public void draw(Graphics2D g, ImageObserver o) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(image, this.x, this.y, o);
    }

    private boolean isCollision() {
        return (boat.getBounds().intersects(getBounds()));
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
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

    public int getFallSpeed() {
        return fallSpeed;
    }

    public void setFallSpeed(int fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

}
