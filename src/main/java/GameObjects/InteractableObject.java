/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import java.awt.Rectangle;

/**
 *
 * @author avino
 */
public abstract class InteractableObject {

    /* 
        checks the bounds of the object in order to check collosion with other units
        height is divided because parachutists land on the deck and not on top of the boat
     */
    public abstract Rectangle getBounds();

    public abstract boolean isCollision(InteractableObject interactableObject);

    public String parachutistCollideWith(InteractableObject interactableObject) {
        if (interactableObject instanceof Boat) {
            return "Caught";
        }
        return null;
    }

}
