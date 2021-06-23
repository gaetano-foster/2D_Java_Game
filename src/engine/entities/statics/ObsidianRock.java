package engine.entities.statics;

import engine.gfx.Assets;
import engine.items.ItemManager;
import engine.utils.Handler;

import java.awt.*;

public class ObsidianRock extends StaticEntity
{
    public ObsidianRock(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, width, height);
    }

    @Override
    public void die()
    {
        handler.getWorld().getItemManager().addItem(ItemManager.obsidianShardItem.createNew((int)x, (int)y, 1));
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(Assets.obsidianRock, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
    }
}
