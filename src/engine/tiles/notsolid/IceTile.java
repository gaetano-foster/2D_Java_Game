package engine.tiles.notsolid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

import java.awt.image.BufferedImage;

public class IceTile extends Tile
{

    public IceTile(Handler handler, int ID)
    {
        super(Assets.ice, handler, ID);
    }
}
