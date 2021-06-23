package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;

import java.awt.image.BufferedImage;

public class ObsidianWallTile extends Tile
{
    public ObsidianWallTile(int ID)
    {
        super(Assets.obsidianWall, ID);
        solid = true;
    }
}
