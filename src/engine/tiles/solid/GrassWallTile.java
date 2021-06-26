package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

import java.awt.image.BufferedImage;

public class GrassWallTile extends Tile
{
    public GrassWallTile(Handler handler, int ID)
    {
        super(Assets.grassWall, handler, ID);
        solid = true;
    }
}
