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

    int x, y;

    public abstract Rectangle getBounds();

    public abstract boolean isCollision(InteractableObject interactableObject);

    public String parachutistCollideWith(InteractableObject interactableObject) {
        if (interactableObject instanceof Boat) {
            return GameData.PARACHUTIST_CAUGHT;
        } else if (interactableObject instanceof Sea) {
            return GameData.PARACHUTIST_DROWNED;
        }
        return null;
    }

}
