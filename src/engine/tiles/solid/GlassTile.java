package engine.tiles.solid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

import java.awt.image.BufferedImage;

public class GlassTile extends Tile
{
    public GlassTile(Handler handler, int ID)
    {
        super(Assets.glass, handler, ID);
        solid = true;
        transparent = true;
    }
}
