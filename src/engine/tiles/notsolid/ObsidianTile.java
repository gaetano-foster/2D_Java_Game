package engine.tiles.notsolid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

import java.awt.image.BufferedImage;

public class ObsidianTile extends Tile
{
    public ObsidianTile(Handler handler, int ID)
    {
        super(Assets.obsidian, handler, ID);
    }
}
