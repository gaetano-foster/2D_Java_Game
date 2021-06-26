package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

import java.awt.image.BufferedImage;

public class DirtWallTile extends Tile
{

    public DirtWallTile(Handler handler, int ID)
    {
        super(Assets.dirtWall, handler, ID);
        solid = true;
    }
}
