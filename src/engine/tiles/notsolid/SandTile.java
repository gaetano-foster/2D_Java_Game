package engine.tiles.notsolid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

public class SandTile extends Tile
{
    public SandTile(Handler handler, int ID)
    {
        super(Assets.sand, handler, ID);
    }
}
