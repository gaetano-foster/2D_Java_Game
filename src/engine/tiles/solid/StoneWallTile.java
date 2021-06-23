package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;

public class StoneWallTile extends Tile
{
    public StoneWallTile(int ID)
    {
        super(Assets.stoneWall, ID);
        solid = true;
    }

}
