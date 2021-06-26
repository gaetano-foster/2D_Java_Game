package engine.tiles.notsolid;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.tiles.solid.DoorTile;
import engine.utils.Handler;

import java.awt.*;

public class OpenDoorTile extends Tile
{
    public OpenDoorTile(Handler handler, int ID)
    {
        super(Assets.openDoor, handler, ID);
        solid = false;
        transparent = true;
    }

    @Override
    public void render(Graphics g, int x, int y)
    {
        super.render(g, x, y);
    }
}
