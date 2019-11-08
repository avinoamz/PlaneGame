package Movement;

import Model.GameObject;
import Model.Information;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * Responsible for moving the objects and managing their interactions
 */
public class MovementManager implements MovementInterface {

    private final GameObject plane;
    private final GameObject boat;
    private final LinkedList<GameObject> parachutists;
    private final Information information;

    public MovementManager(GameObject plane, GameObject boat, LinkedList<GameObject> parachutists, Information information) {
        this.plane = plane;
        this.boat = boat;
        this.parachutists = parachutists;
        this.information = information;
    }

    @Override
    public void moveBoatLeft() {
        setBoatVelocityAndMove(-Information.BOAT_MOVEMENT_SPEED);
    }

    @Override
    public void moveBoatRight() {
        setBoatVelocityAndMove(Information.BOAT_MOVEMENT_SPEED);
    }

    private void setBoatVelocityAndMove(int velocity) {
        boat.setVelocity(velocity);
        moveBoat();
    }

    // moves the boat, controlled by the player
    public void moveBoat() {
        int x = boat.getX();
        int velocity = boat.getVelocity();
        if (velocity > 0) {
            if (x < Information.WINDOW_X_SIZE - boat.getWidth()) {
                x += velocity;
            }
        } else if (velocity < 0) {
            if (x > 0) {
                x += velocity;
            }
        }
        boat.setX(x);
    }

    // move all the parachutists, and checks whether they are caught or drown
    @Override
    public void moveParachutists() {

        Rectangle boatRectangle = new Rectangle(boat.getX(), boat.getY() + (boat.getHeight() / 2), boat.getWidth(), boat.getHeight());

        for (int i = 0; i < parachutists.size(); i++) {
            GameObject parachutist = parachutists.get(i);
            parachutist.setY(parachutist.getY() + parachutist.getVelocity());

            Rectangle parachutistRectangle = new Rectangle(parachutist.getX(), parachutist.getY(), parachutist.getWidth(), parachutist.getHeight());

            if (parachutistRectangle.intersects(boatRectangle)) {
                information.parachuterCaught();
                parachutists.remove(parachutist);
                i--;
            } else if (parachutist.getY() > Information.WINDOW_Y_SIZE - Information.SEA_HEIGHT) {
                information.parachuterDrowned();
                parachutists.remove(parachutist);
                i--;
            }
        }
    }

    // moves the plane
    @Override
    public void movePlane() {
        int x = plane.getX();
        x -= plane.getVelocity();
        if (x < 0) {
            x = Information.WINDOW_X_SIZE - plane.getWidth();
        }
        plane.setX(x);
    }

}
