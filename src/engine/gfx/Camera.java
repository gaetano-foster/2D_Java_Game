package engine.gfx;

import engine.entities.Entity;
import engine.utils.Handler;
import engine.tiles.Tile;

public class Camera
{
    private Handler handler;
    private float xOffset, yOffset;

    public Camera(Handler handler, float xOffset, float yOffset)
    {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void checkBlankSpace()
    {
        if (xOffset < 0)
        {
            xOffset = 0;
        }

        if (xOffset > handler.getWorld().getWidth() * Tile.SIZE - handler.getWidth())
        {
            xOffset = handler.getWorld().getWidth() * Tile.SIZE - handler.getWidth();
        }

        if (yOffset < 0)
        {
            yOffset = 0;
        }

        if (yOffset > handler.getWorld().getHeight() * Tile.SIZE - handler.getHeight())
        {
            yOffset = handler.getWorld().getHeight() * Tile.SIZE - handler.getHeight();
        }

    }

    public void centerOnEntity(Entity e)
    {
        setPos(e.getX() - handler.getWidth() / 2 + e.getWidth() / 2, e.getY() - handler.getHeight() / 2 + e.getHeight() / 2);
        checkBlankSpace();
    }

    public void move(float xAmt, float yAmt)
    {
        xOffset += xAmt;
        yOffset += yAmt;
        //checkBlankSpace();
    }

    public void setPos(float xAmt, float yAmt)
    {
        xOffset = xAmt;
        yOffset = yAmt;
        //checkBlankSpace();
    }

    public float getxOffset()
    {
        return xOffset;
    }

    public void setxOffset(float xOffset)
    {
        this.xOffset = xOffset;
    }

    public float getyOffset()
    {
        return yOffset;
    }

    public void setyOffset(float yOffset)
    {
        this.yOffset = yOffset;
    }
}
