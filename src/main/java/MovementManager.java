
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 *
 * Responsible for moving the objects and managing their interactions
 */
public class MovementManager {

    public void moveBoatLeft() {
        setBoatVelocityAndMove(-GameData.BOAT_MOVEMENT_SPEED);
    }

    public void moveBoatRight() {
        setBoatVelocityAndMove(GameData.BOAT_MOVEMENT_SPEED);
    }

    private void setBoatVelocityAndMove(int velocity) {
        GameData.getInstance().getBoat().setVelocity(velocity);
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
        LinkedList<GameObject> parachutists = GameData.getInstance().getParachutists();

        for (int i = 0; i < parachutists.size(); i++) {
            GameObject parachutist = parachutists.get(i);
            parachutist.setY(parachutist.getY() + parachutist.getVelocity());

            if (needToRemove(parachutist)) {
                i--;
            }
        }
    }

    // checks whether the parachutists landed on the boat or drowned
    private boolean needToRemove(GameObject parachutist) {
        GameData gameData = GameData.getInstance();
        GameObject boat = gameData.getBoat();
        boolean needToRemove = false;

        Rectangle boatRectangle = new Rectangle(boat.getX(), boat.getY() + (boat.getHeight() / 2), boat.getWidth(), boat.getHeight());
        Rectangle parachutistRectangle = new Rectangle(parachutist.getX(), parachutist.getY(), parachutist.getWidth(), parachutist.getHeight());

        if (parachutistRectangle.intersects(boatRectangle)) {
            gameData.removeCaughtParachutist(parachutist);
            needToRemove = true;
        } else if (parachutist.getY() > GameData.WINDOW_Y_SIZE - GameData.SEA_HEIGHT) {
            gameData.removeDrownedParachutist(parachutist);
            needToRemove = true;
        }
        return needToRemove;
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
