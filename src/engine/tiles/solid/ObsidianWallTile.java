package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

import java.awt.image.BufferedImage;

public class ObsidianWallTile extends Tile
{
    public ObsidianWallTile(Handler handler, int ID)
    {
        super(Assets.obsidianWall, handler, ID);
        solid = true;
    }
}
