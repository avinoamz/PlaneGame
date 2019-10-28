/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjcets;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

/**
 *
 * @author avino
 */
interface GameObject {

    public void draw(Graphics2D g, ImageObserver o);

    public void move();

}
