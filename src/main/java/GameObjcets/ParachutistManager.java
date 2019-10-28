/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjcets;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author avino
 */
public class ParachutistManager implements GameObject {

    private final LinkedList<Parachutist> parachuters;
    private final LinkedList<Parachutist> parachutersToDelete;
    private int seaHeight;
    private final Random rand;

    public ParachutistManager(int seaHeight) {
        parachuters = new LinkedList<>();
        parachutersToDelete = new LinkedList<>();
        rand = new Random();
        this.seaHeight = seaHeight;
    }

    public void createNewParachutists(int x, int y, Boat boat) {
        if (parachuters.size() < 3) {
            int n = rand.nextInt(500);
            if (n < 3) {
                Parachutist p = new Parachutist(x, y, boat, seaHeight);
                parachuters.add(p);
            }
        }
    }

    @Override
    public void draw(Graphics2D g, ImageObserver o) {
        for (Parachutist p : parachuters) {
            p.draw(g, o);
        }
    }

    @Override
    public void move() {
        for (Parachutist p : parachuters) {
            p.move();
            if (!p.isIsAlive()) {
                parachutersToDelete.add(p);
            }
        }
        parachuters.removeAll(parachutersToDelete);
        parachutersToDelete.clear();
    }

}
