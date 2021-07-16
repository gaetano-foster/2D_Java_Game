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
    public static Item iceCubeItem = new Item(Assets.iceCubes, "Ice Cube", 3);
    public static Item obsidianShardItem = new Item(Assets.obsidianShard, "Obsidian Shard", 4, 2);
    public static Item iceItem = new Item(Assets.iceRock, "Ice", 5);
    public static Item obsidianItem = new Item(Assets.obsidianRock, "Obsidian", 6);
    public static Item doorItem = new Item(Assets.doorItem, "Door", 7);
    public static Item coalItem = new Item(Assets.coal, "Coal", 8);
    public static Item glassItem = new Item(Assets.glass, "Glass", 9);
    public static Item swordItem = new Item(Assets.sword, "Sword", 10, 7, false);
    public static Item rawIronItem = new Item(Assets.rawIron, "Raw Iron", 11);
    public static Item ironItem = new Item(Assets.iron, "Iron", 12);
    public static Item stickItem = new Item(Assets.stick, "Stick", 13, 1);

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
            if (i.getX() - handler.getCamera().getxOffset() > -16 && i.getY() - handler.getCamera().getyOffset() > -16 && i.getX() - handler.getCamera().getxOffset() < handler.getGame().getWidth() && i.getY() - handler.getCamera().getyOffset() < handler.getGame().getHeight())
                i.update();
            if (i.isPickedUp())
                it.remove();
        }
    }

    public void render(Graphics g)
    {
        for (Item i : itemsList)
        {
            if (i.getX() - handler.getCamera().getxOffset() > -16 && i.getY() - handler.getCamera().getyOffset() > -16 && i.getX() - handler.getCamera().getxOffset() < handler.getGame().getWidth() && i.getY() - handler.getCamera().getyOffset() < handler.getGame().getHeight())
                i.render(g);
        }
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
