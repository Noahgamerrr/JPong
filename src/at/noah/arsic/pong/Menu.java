package at.noah.arsic.pong;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (mouseOver(mx, my, Game.WIDTH / 2 - 109, 300, 200, 64)) {
            game.gameState = Game.STATE.Game;
            game.removeMouseListener(this);
            handler.addObject(new Player(10, (Game.HEIGHT - Player.HEIGHT) / 2, ID.Player));
            handler.addObject(new Player(960 , (Game.HEIGHT - Player.HEIGHT) / 2, ID.Player2));
            handler.addObject(new Ball(Game.WIDTH / 2, Game.HEIGHT / 2, ID.Ball, game, handler));
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            return my > y && my < y + height;
        }
        return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        Font fnt = new Font("arial", Font.BOLD, 60);
        Font fnt2 = new Font("arial", Font.BOLD, 30);
        g.setColor(Color.white);
        g.setFont(fnt);
        g.drawString("Pong", Game.WIDTH / 2 - 79, Game.HEIGHT / 2 - 200);
        g.setFont(fnt2);
        g.drawRect(Game.WIDTH / 2 - 109, 300, 200, 64);
        g.drawString("Play", Game.WIDTH / 2 - 40, Game.HEIGHT / 2 - 30);
    }
}
