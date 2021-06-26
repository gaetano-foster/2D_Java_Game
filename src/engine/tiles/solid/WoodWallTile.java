package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

public class WoodWallTile extends Tile
{
    public WoodWallTile(Handler handler, int ID)
    {
        super(Assets.woodWall, handler, ID);
        solid = true;
    }
}
