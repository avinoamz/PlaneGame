/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import GameData.GameData;
import java.awt.Rectangle;

/**
 *
 * @author avino
 */
public abstract class InteractableObject {

    public abstract Rectangle getBounds();

    public abstract boolean isCollision(InteractableObject interactableObject);

    public String parachutistCollideWith(InteractableObject interactableObject) {
        if (interactableObject instanceof Boat) {
            return GameData.PARACHUTIST_CAUGHT;
        }
        return null;
    }

}
