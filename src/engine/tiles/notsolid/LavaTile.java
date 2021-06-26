package engine.tiles.notsolid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

public class LavaTile extends Tile
{
    public LavaTile(Handler handler, int ID)
    {
        super(Assets.lava, handler, ID);
    }
}
