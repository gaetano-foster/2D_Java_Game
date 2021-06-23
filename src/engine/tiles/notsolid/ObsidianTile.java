package engine.tiles.notsolid;

import engine.gfx.Assets;
import engine.tiles.Tile;

import java.awt.image.BufferedImage;

public class ObsidianTile extends Tile
{
    public ObsidianTile(int ID)
    {
        super(Assets.obsidian, ID);
    }
}
