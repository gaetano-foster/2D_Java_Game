package engine;

import engine.display.Display;
import engine.items.Item;
import engine.tiles.Tile;
import engine.utils.Handler;
import engine.gfx.Assets;
import engine.gfx.Camera;
import engine.input.KeyManager;
import engine.input.MouseManager;
import engine.states.GameState;
import engine.states.MenuState;
import engine.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable
{
    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    private KeyManager keyManager;
    private MouseManager mouseManager;

    private Camera camera;
    private Handler handler;

    public State gameState;
    public State menuState;

    public Game(int width, int height, String title)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init()
    {
        display = new Display(width, height, title);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.load();

        handler = new Handler(this);
        camera = new Camera(handler, 0, 0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(gameState);
    }


    private void update()
    {
        keyManager.update();
        mouseManager.update();
        if (State.getState() != null)
        {
            State.getState().update();
        }
    }

    private void render()
    {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // clear
        g.clearRect(0, 0, width, height);
        // render

        Graphics2D g2d = (Graphics2D) g;


        if (State.getState() != null)
        {
            State.getState().render(g2d);
        }

        // stop
        bs.show();
        g.dispose();
    }

    public static float deltaTime = 1;

    public void run()
    {
        init();

        double delta = 0;
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        float ticks = 0;

        while (running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1)
            {
                update();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000)
            {
                display.setTitle(display.getTitle() + " FPS: " + ticks);
                deltaTime = (1 / ticks) * Tile.SIZE;
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public synchronized void start()
    {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop()
    {
        if (!running)
            return;
        running = false;
        try
        {
            thread.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public Camera getCamera()
    {
        return camera;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public KeyManager getKeyManager()
    {
        return keyManager;
    }

    public MouseManager getMouseManager()
    {
        return mouseManager;
    }

}
