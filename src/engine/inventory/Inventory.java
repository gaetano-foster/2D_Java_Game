package engine.inventory;

import engine.gfx.Assets;
import engine.gfx.Text;
import engine.items.Item;
import engine.items.ItemManager;
import engine.utils.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory
{
    private Handler handler;
    private boolean active = false;
    private boolean debug = false;
    private ArrayList<Item> inventoryItems;
    private int y;
    private int listCenterX, listCenterY;
    private int selectedItem = 0;
    private Item activeItem;

    public Inventory(Handler handler, boolean debugMode)
    {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();
        y = handler.getGame().getHeight() - 1;
        debug = debugMode;
        if (debug)
        {
            addItem(ItemManager.iceCubeItem.createNew(64));
            addItem(ItemManager.obsidianShardItem.createNew(64));
            addItem(ItemManager.woodItem.createNew(64));
            addItem(ItemManager.rockItem.createNew(64));
            addItem(ItemManager.sandItem.createNew(64));
        }
    }

    public void update()
    {
        inventoryItems.removeIf(i -> i.getCount() <= 0);

        if (handler.getKeyManager().keyJustDown(KeyEvent.VK_E))
            active = !active;
        try
        {
            if (inventoryItems.size() != 0)
                activeItem = inventoryItems.get(selectedItem);
            else
                activeItem = null;
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("no");
        }

        if (handler.getKeyManager().keyJustDown(KeyEvent.VK_RIGHT))
            selectedItem--;
        if (handler.getKeyManager().keyJustDown(KeyEvent.VK_LEFT))
            selectedItem++;

        if (selectedItem < 0)
            selectedItem = inventoryItems.size() - 1;
        else if (selectedItem >= inventoryItems.size())
            selectedItem = 0;
        if (!active)
            return;

        listCenterX = 11 + (20 * ((handler.getGame().getWidth()) - 20) / 197);
        listCenterY = y + (75 * ((handler.getGame().getHeight()) - 20) / 148);

        if (handler.getKeyManager().keyJustDown(KeyEvent.VK_W))
            selectedItem--;
        if (handler.getKeyManager().keyJustDown(KeyEvent.VK_S))
            selectedItem++;
    }

    public void render(Graphics g)
    {
        if (!active)
        {
            if (y < handler.getGame().getHeight() - 1)
            {
                y += 30;
                g.drawImage(Assets.inventoryScreen, 10, y, handler.getGame().getWidth() - 20, handler.getGame().getHeight() - 20, null);
            }
            if (y > handler.getGame().getHeight() - 1)
                y = handler.getGame().getHeight() - 1;
            g.drawImage(Assets.activeItemHolder, handler.getGame().getWidth() - 170, 20, 150, 196, null);
            if (activeItem == null)
            {
                Text.drawString(g, "?", handler.getGame().getWidth() - 145, 150, false, Color.RED, Assets.font168);
            } else
            {
                g.drawImage(activeItem.getTexture(), handler.getGame().getWidth() - 145, 50, 100, 100, null);
                Text.drawString(g, Integer.toString(activeItem.getCount()), handler.getGame().getWidth() - 120, 205, false, Color.BLACK, Assets.font56);
            }
            return;
        }

        if (y > 10)
        {
            y -= 30;
        }

        if (y < 10)
        {
            y = 10;
        }

        g.drawImage(Assets.inventoryScreen, 10, y, handler.getGame().getWidth() - 20, handler.getGame().getHeight() - 20, null);

        int len = inventoryItems.size();

        if (len == 0)
            return;

        for (int i = -4; i < 5; i++)
        {
            if (selectedItem + i < 0 || selectedItem + i >= len)
                continue;
            if (i == 0)
            {
                Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", listCenterX, listCenterY + i * (12 * ((handler.getGame().getHeight()) - 20) / 148), false, Color.YELLOW, Assets.font56);
            }
            else
            {
                Text.drawString(g, "  " + inventoryItems.get(selectedItem + i).getName(), listCenterX, listCenterY + i * (12 * ((handler.getGame().getHeight()) - 20) / 148), false, Color.BLACK, Assets.font56);
            }
        }
        g.drawImage(activeItem.getTexture(), 10 + 150 * ((handler.getGame().getWidth()) - 20) / 197, y + 12 * ((handler.getGame().getHeight()) - 20) / 148, 150, 150, null);
        Text.drawString(g, Integer.toString(activeItem.getCount()), 10 + 155 * ((handler.getGame().getWidth()) - 20) / 197, y + 64 * ((handler.getGame().getHeight()) - 20) / 148, false, Color.BLACK, Assets.font56);

        if (selectedItem < 0)
            selectedItem = inventoryItems.size() - 1;
        else if (selectedItem >= inventoryItems.size())
            selectedItem = 0;
    }

    public void addItem(Item item)
    {
        for (Item i : inventoryItems)
        {
            if (i.getID() == item.getID())
            {
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }

    // getters n setters


    public ArrayList<Item> getInventoryItems()
    {
        return inventoryItems;
    }

    public Handler getHandler()
    {
        return handler;
    }

    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public int getSelectedItem()
    {
        return selectedItem;
    }

    public Item getActiveItem()
    {
        return activeItem;
    }
}
