package at.noah.arsic.pong;

import java.awt.*;

public abstract class GameObject {
    protected int x, y;
    protected ID id;
    protected double velX, velY;
    protected int score;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public ID getId() {
        return this.id;
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public int getScore() {
        return this.score;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
