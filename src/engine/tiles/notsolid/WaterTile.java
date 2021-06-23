package engine.tiles.notsolid;

import engine.gfx.Assets;
import engine.tiles.Tile;

public class WaterTile extends Tile
{
    public WaterTile(int ID)
    {
        super(Assets.water, ID);
    }

    @Override
    public void update()
    {
        super.update();
    }
}
