/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author avino
 */
public class Information {

    private int lives;
    private int score;
    private int numberOfParachutists;

    public Information(int lives, int score, int numberOfParachutists) {
        this.lives = lives;
        this.score = score;
        this.numberOfParachutists = numberOfParachutists;
    }

    public void parachuterDrowned() {
        numberOfParachutists--;
        lives--;
    }

    public void parachuterCaught() {
        numberOfParachutists--;
        score += 10;
    }

    public void addParachutist() {
        numberOfParachutists++;
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

    public int getNumberOfParachutists() {
        return numberOfParachutists;
    }

    public void setNumberOfParachutists(int numberOfParachutists) {
        this.numberOfParachutists = numberOfParachutists;
    }

}
