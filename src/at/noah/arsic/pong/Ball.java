package at.noah.arsic.pong;

import java.awt.*;
import java.util.Random;

public class Ball extends GameObject{
    public static final int RAD = 20;
    private final Game game;
    private final Handler handler;
    Random r = new Random();

    public Ball(int x, int y, ID id, Game game, Handler handler) {
        super(x, y, id);
        velX = r.nextInt(4) + 2;
        if (r.nextBoolean()) {
            velX *= -1;
        }
        velY = r.nextInt(4) + 2;
        if (r.nextBoolean()) {
            velY*= -1;
        }
        this.game = game;
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (y + velY <= 10 || y + velY >= Game.HEIGHT - RAD - 40) velY *= -1;
        velX = collisionWithPlayer();
        if (x <= 0 || x >= Game.WIDTH) {
            deleteAndCreateBall();
            setPlayerAtStartPosition();
            checkForGameEnd();
        }
    }

    public void setPlayerAtStartPosition() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player || tempObject.getId() == ID.Player2) tempObject.y = (Game.HEIGHT - Player.HEIGHT) / 2;
        }
    }

    public double collisionWithPlayer() {
        double vel = velX;
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                if (x + velX >= 0 && x + velX <= tempObject.getX() + Player.WIDTH - 2 && y + velY >= tempObject.getY() && y + velY <= tempObject.getY() + Player.HEIGHT) {
                    vel *= -1;
                    if (vel > 0) {
                        vel += 0.5;
                    } else {
                        vel -= 0.5;
                    }
                    if (velY > 0) {
                        velY += 0.5;
                    } else {
                        velY -= 0.5;
                    }
                }
                if (x >= Game.WIDTH) tempObject.setScore(tempObject.getScore() + 1);
            } else if (tempObject.getId() == ID.Player2) {
                if (x + velX <= Game.WIDTH - 40 && x + velX >= tempObject.getX() - 17 && y + velY >= tempObject.getY() && y + velY <= tempObject.getY() + Player.HEIGHT) {
                    vel *= -1;
                    if (vel > 0) {
                        vel += 0.5;
                    } else {
                        vel -= 0.5;
                    }
                    if (velY > 0) {
                        velY += 0.5;
                    } else {
                        velY -= 0.5;
                    }
                }
                if (x <= 0) tempObject.setScore(tempObject.getScore() + 1);
            }
        }
        return vel;
    }

    private void checkForGameEnd() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player || tempObject.getId() == ID.Player2) {
                if (tempObject.getScore() == 10) {
                    game.end.setP1win(tempObject.getId() == ID.Player);
                    game.gameState = Game.STATE.EndOfGame;
                    game.addMouseListener(game.end);
                    removeObjects();
                }
            }
        }
    }

    private void removeObjects() {
        for (int i = handler.object.size() - 1; i >= 0; i--) {
            handler.removeObject(handler.object.get(i));
        }
    }

    public void deleteAndCreateBall() {
        handler.removeObject(this);
        handler.addObject(new Ball(Game.WIDTH / 2, Game.HEIGHT / 2, ID.Ball, game, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillOval(this.x, this.y, RAD, RAD);
    }
}
