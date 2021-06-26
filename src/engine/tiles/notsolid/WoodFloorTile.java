package engine.tiles.notsolid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

public class WoodFloorTile extends Tile
{
    public WoodFloorTile(Handler handler, int ID)
    {
        super(Assets.woodFloor, handler, ID);
    }
}
