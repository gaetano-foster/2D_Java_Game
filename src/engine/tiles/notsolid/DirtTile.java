package engine.tiles.notsolid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

public class DirtTile extends Tile
{
    public DirtTile(Handler handler, int ID)
    {
        super(Assets.dirt, handler, ID);
    }
}
