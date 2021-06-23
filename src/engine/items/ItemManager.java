package engine.items;

import engine.gfx.Assets;
import engine.utils.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager
{
    // handler

    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.woodItem, "Wood", 0);
    public static Item rockItem = new Item(Assets.rock, "Rock", 1);
    public static Item sandItem = new Item(Assets.sandDune, "Sand", 2);
    public static Item iceItem = new Item(Assets.iceCubes, "Ice", 3);
    public static Item obsidianItem = new Item(Assets.obsidianShard, "Obsidian", 4);

    // rest

    private Handler handler;
    private ArrayList<Item> itemsList;

    public ItemManager(Handler handler)
    {
        this.handler = handler;
        itemsList = new ArrayList<Item>();
    }

    public void update()
    {
        Iterator<Item> it = itemsList.iterator();
        while(it.hasNext())
        {
            Item i = it.next();
            i.update();
            if (i.isPickedUp())
                it.remove();
        }
    }

    public void render(Graphics g)
    {
        for (Item i : itemsList)
            i.render(g);
    }

    public void addItem(Item i)
    {
        i.setHandler(handler);
        itemsList.add(i);
    }

    public Handler getHandler()
    {
        return handler;
    }

    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }

    public ArrayList<Item> getItems()
    {
        return itemsList;
    }

    public void setItems(ArrayList<Item> items)
    {
        this.itemsList = items;
    }
}
