
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 *
 * Responsible for moving the objects and managing thier interactions
 */
public class MovementManager {

    // move the boat once the arrow keys are pressed
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            GameData.getInstance().getBoat().setVelocity(-4);
        }
        if (key == KeyEvent.VK_RIGHT) {
            GameData.getInstance().getBoat().setVelocity(4);
        }
        moveBoat();
    }

    // moves the boat, controlled by the player
    public void moveBoat() {
        GameObject boat = GameData.getInstance().getBoat();
        int x = boat.getX();
        int velocity = boat.getVelocity();
        if (velocity > 0) {
            if (x < GameData.WINDOW_X_SIZE - ScreenManager.boatImage.getWidth()) {
                x += velocity;
            }
        } else if (velocity < 0) {
            if (x > 0) {
                x += velocity;
            }
        }
        boat.setX(x);
        GameData.getInstance().getScreenManager().repaint();
    }

    // move all the parachutists, and checks whether they are caught or drown
    public void moveParachutists() {
        GameData gameData = GameData.getInstance();
        GameObject boat = gameData.getBoat();
        Rectangle boatRectangle = new Rectangle(boat.getX(), boat.getY() + (boat.getHeight() / 2), boat.getWidth(), boat.getHeight());
        LinkedList<GameObject> parachutists = gameData.getParachutists();

        for (int i = 0; i < parachutists.size(); i++) {
            GameObject parachutist = parachutists.get(i);
            int y = parachutist.getY();
            y += parachutist.getVelocity();
            parachutist.setY(y);

            Rectangle parachutistRectangle = new Rectangle(parachutist.getX(), parachutist.getY(), parachutist.getWidth(), parachutist.getHeight());

            if (parachutistRectangle.intersects(boatRectangle)) {
                gameData.parachuterCaught(parachutist);
                i--;
            } else if (parachutist.getY() > GameData.WINDOW_Y_SIZE - GameData.SEA_HEIGHT) {
                gameData.parachuterDrowned(parachutist);
                i--;
            }
        }
    }

    // moves the plane
    public void movePlane() {
        GameObject plane = GameData.getInstance().getPlane();
        int x = plane.getX();
        x -= plane.getVelocity();
        if (x < 0) {
            x = GameData.WINDOW_X_SIZE - ScreenManager.planeImage.getWidth();
        }
        plane.setX(x);
    }

}
