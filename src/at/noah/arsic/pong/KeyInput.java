package at.noah.arsic.pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;
        Arrays.fill(keyDown, false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) {tempObject.setVelY(-5); keyDown[0] = true;}
                if (key == KeyEvent.VK_S) {tempObject.setVelY(5); keyDown[1] = true;}
            }
            if (tempObject.getId() == ID.Player2) {
                if (key == KeyEvent.VK_UP) {tempObject.setVelY(-5); keyDown[2] = true;}
                if (key == KeyEvent.VK_DOWN) {tempObject.setVelY(5); keyDown[3] = true;}
            }
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(0);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) keyDown[0] = false;
                if (key == KeyEvent.VK_S) keyDown[1] = false;
                if (!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
            }
            if (tempObject.getId() == ID.Player2) {
                if (key == KeyEvent.VK_UP) keyDown[2] = false;
                if (key == KeyEvent.VK_DOWN) keyDown[3] = false;
                if (!keyDown[2] && !keyDown[3]) tempObject.setVelY(0);
            }
        }
    }
}
