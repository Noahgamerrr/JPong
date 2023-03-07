package at.noah.arsic.pong;

import java.awt.*;

public class Player extends GameObject{
    public static final int WIDTH = 15;
    public static final int HEIGHT = 100;
    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    public void tick() {
        x += velX;
        y += velY;
        y = clamp(y, 0, Game.HEIGHT - Player.HEIGHT - 35);
    }

    public static int clamp(int var, int min, int max) {
        if (var >= max) return max;
        else return Math.max(var, min);
    }

    public void render(Graphics g) {
        if (id == ID.Player) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.gray);
        }
        g.fillRect(this.x, this.y, WIDTH, HEIGHT);
    }
}
