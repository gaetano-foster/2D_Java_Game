package engine.entities.statics;

import engine.gfx.Assets;
import engine.items.ItemManager;
import engine.tiles.Tile;
import engine.tiles.TileManager;
import engine.utils.Handler;

import java.awt.*;

public class IceRock extends StaticEntity
{
    public IceRock(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, width, height);
    }

    @Override
    public void die()
    {
        handler.getWorld().getItemManager().addItem(ItemManager.iceCubeItem.createNew((int)x, (int)y, 1));
        handler.getWorld().setTile((int)x / Tile.SIZE, (int)y / Tile.SIZE, handler.getTileManager().waterTile.getID());
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(Assets.iceRock, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
    }
}
