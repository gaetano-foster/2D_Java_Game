package engine.tiles.notsolid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

public class StoneFloorTile extends Tile
{

    public StoneFloorTile(Handler handler, int ID)
    {
        super(Assets.stoneFloor, handler, ID);
    }
}
