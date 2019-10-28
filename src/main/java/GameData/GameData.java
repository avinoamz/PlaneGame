/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameData;

/**
 *
 * @author avino
 */
public class GameData {

    static int lives = 3;
    static int score = 0;
    static int windowXSize = 1080;
    static int windowYSize = 720;
//    static int seaHeight = 100;

    public static int getLives() {
        return lives;
    }

    public static void setLives(int lives) {
        GameData.lives = lives;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GameData.score = score;
    }

    public static int getWindowXSize() {
        return windowXSize;
    }

    public static void setWindowXSize(int windowXSize) {
        GameData.windowXSize = windowXSize;
    }

    public static int getWindowYSize() {
        return windowYSize;
    }

    public static void setWindowYSize(int windowYSize) {
        GameData.windowYSize = windowYSize;
    } 
   

}
