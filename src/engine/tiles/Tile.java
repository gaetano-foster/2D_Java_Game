package engine.tiles;

import engine.tiles.notsolid.*;
import engine.tiles.solid.DirtWallTile;
import engine.tiles.solid.GrassWallTile;
import engine.tiles.solid.StoneWallTile;
import engine.tiles.solid.WoodWallTile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile
{
    // class

    public static final int SIZE = 50;

    protected BufferedImage texture;
    protected final int ID;
    protected boolean solid;

    public Tile(BufferedImage texture, int ID)
    {
        this.texture = texture;
        this.ID = ID;

        TileManager.tiles[ID] = this;
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
}
