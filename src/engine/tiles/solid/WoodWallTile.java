package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;

public class WoodWallTile extends Tile
{
    public WoodWallTile(int ID)
    {
        super(Assets.woodWall, ID);
        solid = true;
    }
}
