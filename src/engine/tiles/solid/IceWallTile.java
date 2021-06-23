package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;

import java.awt.image.BufferedImage;

public class IceWallTile extends Tile
{
    public IceWallTile(int ID)
    {
        super(Assets.iceWall, ID);
        solid = true;
    }
}
