package engine.tiles.solid;

import engine.gfx.Assets;
import engine.gfx.Camera;
import engine.tiles.Tile;
import engine.utils.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DoorTile extends Tile
{
    public DoorTile(Handler handler, int ID)
    {
        super(Assets.door, handler, ID);
        solid = true;
        transparent = true;
    }

    @Override
    public void render(Graphics g, int x, int y)
    {
        super.render(g, x, y);
    }
}