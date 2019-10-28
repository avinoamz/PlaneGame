
import GameObjcets.Boat;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author avino
 */
public class KeyboardListener implements KeyListener {

    Boat boat;

    public KeyboardListener(Boat boat) {
        this.boat = boat;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boat.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        boat.keyReleased(e);
    }

}
