package engine.tiles.notsolid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

public class GrassTile extends Tile
{
    public GrassTile(Handler handler, int ID)
    {
        super(Assets.grass, handler, ID);
        handler.getKeyManager();
    }
}
