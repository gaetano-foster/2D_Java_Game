package engine.items;

import engine.Game;
import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item
{

    // class

    public static final int ITEM_SIZE = 25;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int ID;

    protected Rectangle bounds;

    protected int x, y, count;
    protected boolean pickedUp = false;

    public Item(BufferedImage texture, String name, int ID)
    {
        this.texture = texture;
        this.name = name;
        this.ID = ID;
        count = 1;

        bounds = new Rectangle(x, y, ITEM_SIZE, ITEM_SIZE);

        ItemManager.items[ID] = this;
    }

    public void update()
    {
        bounds.x = x;
        bounds.y = y;
        float xMove = 0, yMove = 0;

        xMove = ((handler.getPlayer().getX() - handler.getCamera().getxOffset()) - (x - handler.getCamera().getxOffset()));
        yMove = ((handler.getPlayer().getY() - handler.getCamera().getyOffset()) - (y - handler.getCamera().getyOffset()));

        float factor = 10 / (float)Math.sqrt(xMove * xMove + yMove * yMove);

        xMove *= factor;
        yMove *= factor;

        x += xMove * Game.deltaTime;
        y += yMove * Game.deltaTime;

        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(bounds))
        {
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
            pickedUp = true;
        }
    }

    public void render(Graphics g)
    {
        if (handler == null)
            return;
        render(g, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()));
    }

    public void render(Graphics g, int x, int y)
    {
        g.drawImage(texture, x, y, ITEM_SIZE, ITEM_SIZE, null);
    }

    public Item createNew(int count)
    {
        Item i = new Item(texture, name, ID);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    public Item createNew(int x, int y)
    {
        Item i = new Item(texture, name, ID);
        i.setPosition(x, y);
        return i;
    }

    public Item createNew(int x, int y, int count)
    {
        Item i = new Item(texture, name, ID);
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public Handler getHandler()
    {
        return handler;
    }

    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }

    public BufferedImage getTexture()
    {
        return texture;
    }

    public void setTexture(BufferedImage texture)
    {
        this.texture = texture;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getID()
    {
        return ID;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public boolean isPickedUp()
    {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp)
    {
        this.pickedUp = pickedUp;
    }
}
