package engine.entities.statics;

import engine.gfx.Assets;
import engine.items.ItemManager;
import engine.utils.Handler;

import java.awt.*;
import java.util.Random;

public class CoalOre extends StaticEntity
{
    public CoalOre(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, width, height);
    }

    @Override
    public void die()
    {
        Random random = new Random();
        int amt = random.nextInt(2);
        if (amt < 1)
            amt = 1;

        handler.getWorld().getItemManager().addItem(ItemManager.coalItem.createNew((int)(x), (int)(y), amt));
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(Assets.coalOre, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
    }
}
