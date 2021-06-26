package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

import java.awt.image.BufferedImage;

public class IceWallTile extends Tile
{
    public IceWallTile(Handler handler, int ID)
    {
        super(Assets.iceWall, handler, ID);
        solid = true;
    }
}
