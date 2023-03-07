package at.noah.arsic.pong;

import java.awt.*;

public class HUD {
    private int fps;
    private final Handler handler;

    public HUD (Handler handler) {
        this.handler = handler;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        Font fnt = new Font("arial", Font.PLAIN, 30);
        g.setColor(Color.white);
        g.drawString("FPS: " +fps, 10, 20);
        g.setFont(fnt);
        g.drawString(":", Game.WIDTH / 2 - 5, 38);
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                g.drawString(String.valueOf(tempObject.getScore()), Game.WIDTH / 2 - 40, 40);
            } else if (tempObject.getId() == ID.Player2) {
                g.drawString(String.valueOf(tempObject.getScore()), Game.WIDTH / 2 + 7, 40);
            }
        }
    }
}
