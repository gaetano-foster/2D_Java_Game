package engine.entities.statics;

import engine.items.Item;
import engine.items.ItemManager;
import engine.utils.Handler;
import engine.gfx.Assets;

import java.awt.*;
import java.util.Random;

public class BrownRock extends StaticEntity
{
    public BrownRock(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, width, height);
    }

    @Override
    public void die()
    {
        Random random = new Random();
        int amt = random.nextInt(5);
        if (amt < 1)
            amt = 1;

        handler.getWorld().getItemManager().addItem(ItemManager.rockItem.createNew((int)(x), (int)(y), amt));
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(Assets.rock1, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
    }
}
