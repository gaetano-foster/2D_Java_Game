package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

import java.awt.image.BufferedImage;

public class SandWallTile extends Tile
{
    public SandWallTile(Handler handler, int ID)
    {
        super(Assets.sandWall, handler, ID);
        solid = true;
    }
}
