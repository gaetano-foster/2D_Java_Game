package engine.tiles.notsolid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

public class WaterTile extends Tile
{
    public WaterTile(Handler handler, int ID)
    {
        super(Assets.water, handler, ID);
        transparent = true;
    }

    @Override
    public void update()
    {
        super.update();
    }
}
