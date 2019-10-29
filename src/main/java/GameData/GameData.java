/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameData;

/**
 *
 * Responsible for holding the data of the game.
 * Singleton
 */
public class GameData {

    private static GameData instance = null;

    private int lives;
    private int score;
    private int windowXSize;
    private int windowYSize;
    private int seaHeight;
    private int maxParachuters;
    private int currentParachuters;

    private GameData() {
        this.lives = 3;
        this.score = 0;
        this.windowXSize = 1080;
        this.windowYSize = 720;
        this.seaHeight = 242;
        this.maxParachuters = 3;
        this.currentParachuters = 0;
    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    public void addParachutist() {
        currentParachuters++;
    }

    public void parachuterDrowned() {
        currentParachuters--;
        lives--;
    }

    public void parachuterCatched() {
        currentParachuters--;
        score += 10;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWindowXSize() {
        return windowXSize;
    }

    public void setWindowXSize(int windowXSize) {
        this.windowXSize = windowXSize;
    }

    public int getWindowYSize() {
        return windowYSize;
    }

    public void setWindowYSize(int windowYSize) {
        this.windowYSize = windowYSize;
    }

    public int getSeaHeight() {
        return seaHeight;
    }

    public void setSeaHeight(int seaHeight) {
        this.seaHeight = seaHeight;
    }

    public int getMaxParachuters() {
        return maxParachuters;
    }

    public void setMaxParachuters(int maxParachuters) {
        this.maxParachuters = maxParachuters;
    }

    public int getCurrentParachuters() {
        return currentParachuters;
    }

    public void setCurrentParachuters(int currentParachuters) {
        this.currentParachuters = currentParachuters;
    }

}
