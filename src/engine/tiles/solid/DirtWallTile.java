package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;

import java.awt.image.BufferedImage;

public class DirtWallTile extends Tile
{

    public DirtWallTile(int ID)
    {
        super(Assets.dirtWall, ID);
        solid = true;
    }
}
