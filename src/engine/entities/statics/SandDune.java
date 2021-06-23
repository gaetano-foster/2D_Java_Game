package engine.entities.statics;

import engine.gfx.Assets;
import engine.items.ItemManager;
import engine.utils.Handler;

import java.awt.*;

public class SandDune extends StaticEntity
{
    public SandDune(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, width, height);
    }

    @Override
    public void die()
    {
        handler.getWorld().getItemManager().addItem(ItemManager.sandItem.createNew((int) (x), (int) (y), 1));
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(Assets.sandDune, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
    }
}
