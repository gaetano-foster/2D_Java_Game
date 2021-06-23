package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;

import java.awt.image.BufferedImage;

public class GrassWallTile extends Tile
{
    public GrassWallTile(int ID)
    {
        super(Assets.grassWall, ID);
        solid = true;
    }
}
