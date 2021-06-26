package engine.tiles;

import engine.tiles.notsolid.*;
import engine.tiles.solid.DirtWallTile;
import engine.tiles.solid.GrassWallTile;
import engine.tiles.solid.StoneWallTile;
import engine.tiles.solid.WoodWallTile;
import engine.utils.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile
{
    // class

    public static final int SIZE = 50;

    protected BufferedImage texture;
    protected Handler handler;
    protected final int ID;
    protected boolean solid;
    protected boolean transparent;

    public Tile(BufferedImage texture, Handler handler, int ID)
    {
        this.texture = texture;
        this.handler = handler;
        this.ID = ID;

        handler.getTileManager().tiles[ID] = this;
    }

    public void update()
    {

    }

    public void onCollision()
    {

    }

    public void render(Graphics g, int x, int y)
    {
        g.drawImage(texture, x, y, SIZE, SIZE, null);
    }

    public int getID()
    {
        return ID;
    }

    public boolean isSolid()
    {
        return solid;
    }

    public boolean isTransparent()
    {
        return transparent;
    }
}
