package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

public class StoneWallTile extends Tile
{
    public StoneWallTile(Handler handler, int ID)
    {
        super(Assets.stoneWall, handler, ID);
        solid = true;
    }

}
