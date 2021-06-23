package engine.entities.statics;

import engine.gfx.Assets;
import engine.items.ItemManager;
import engine.utils.Handler;

import java.awt.*;
import java.util.Random;

public class PalmTree extends StaticEntity
{

    public PalmTree(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, width, height);
        bounds.y = 35;
        bounds.height /= 4;
    }

    @Override
    public void die()
    {
        Random random = new Random();
        int amt = random.nextInt(8);
        if (amt < 1)
            amt = 1;

        handler.getWorld().getItemManager().addItem(ItemManager.woodItem.createNew((int) (x), (int) (y), amt));
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(Assets.palm, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
    }
}
