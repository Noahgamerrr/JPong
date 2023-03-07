package at.noah.arsic.pong;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 1000, HEIGHT = WIDTH / 4 * 3;
    private Thread thread;
    private boolean running = false;
    private final Handler handler;
    private final HUD hud;
    private final Menu menu;
    public EndOfGame end;

    public enum STATE {
        Menu,
        Game,
        EndOfGame
    }

    public STATE gameState = STATE.Menu;

    public Game() {
        handler = new Handler();
        menu = new Menu(this, handler);
        end = new EndOfGame(this, handler);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
        new Window(WIDTH, HEIGHT, "Pong", this);
        hud = new HUD(handler);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        double maxFPS = 60.0;
        long renderLastTime = System.nanoTime();
        double renderNs = 1000000000 / maxFPS;
        double renderDelta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            now = System.nanoTime();
            renderDelta += (now - renderLastTime) / renderNs;
            renderLastTime = now;
            while(running && renderDelta >= 1){
                render();
                frames++;
                renderDelta--;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                hud.setFps(frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        if (gameState == STATE.Game) {
            hud.tick();
        } else if (gameState == STATE.Menu){
            menu.tick();
        } else {
            end.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        handler.render(g);
        if (gameState == STATE.Game) {
            hud.render(g);
        } else if (gameState == STATE.Menu){
            menu.render(g);
        } else {
            end.render(g);
        }
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }
}
