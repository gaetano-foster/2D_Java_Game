package engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
    private boolean[] keys, justPressed, cantPress;
    public boolean up, down, left, right, sprint, attack, addTroll;

    public KeyManager()
    {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public void update()
    {
        for (int i = 0; i < keys.length; i++)
        {
            if (cantPress[i] && !keys[i])
            {
                cantPress[i] = false;
            } else if (justPressed[i])
            {
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if (!cantPress[i] && keys[i])
            {
                justPressed[i] = true;
            }
        }

        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        sprint = keys[KeyEvent.VK_SHIFT];
        attack = keys[KeyEvent.VK_SPACE];
        addTroll = keys[KeyEvent.VK_G];
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() < 0 || e.getKeyCode() > keys.length)
        {
            System.err.println("Key out of bounds!");
            return;
        }

        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() < 0 || e.getKeyCode() > keys.length)
        {
            System.err.println("Key out of bounds!");
            return;
        }

        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    public boolean keyJustDown(int keyCode)
    {
        if (keyCode < 0 || keyCode > keys.length)
        {
            System.err.println("Key out of bounds!");
            return false;
        }

        return justPressed[keyCode];
    }

    public boolean keyDown(int keyCode)
    {
        if (keyCode < 0 || keyCode > keys.length)
        {
            System.err.println("Key out of bounds!");
            return false;
        }

        return keys[keyCode];
    }

    public boolean[] getKeys()
    {
        return keys;
    }
}
