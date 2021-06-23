package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;

import java.awt.image.BufferedImage;

public class SandWallTile extends Tile
{
    public SandWallTile(int ID)
    {
        super(Assets.sandWall, ID);
        solid = true;
    }
}
