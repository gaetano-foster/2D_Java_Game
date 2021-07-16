package engine.inventory;

import engine.gfx.Assets;
import engine.gfx.Text;
import engine.items.Item;
import engine.items.ItemManager;
import engine.utils.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CraftingInterface
{
    private Inventory inventory;

    private Handler handler;
    private boolean active = false;
    private int y;
    private int listCenterX, listCenterY;
    private ArrayList<CraftingRecipe> recipesList;
    private int selectedItem = 0;

    public CraftingInterface(Handler handler, Inventory inventory)
    {
        this.handler = handler;
        this.inventory = inventory;
        recipesList = new ArrayList<CraftingRecipe>();
        y = handler.getGame().getHeight() - 1;
        recipesList.add(new CraftingRecipe(ItemManager.iceCubeItem.createNew(2), ItemManager.iceCubeItem.createNew(2), ItemManager.iceItem.createNew(1)));
        recipesList.add(new CraftingRecipe(ItemManager.obsidianShardItem.createNew(2), ItemManager.obsidianShardItem.createNew(2), ItemManager.obsidianItem.createNew(1)));
        recipesList.add(new CraftingRecipe(ItemManager.woodItem.createNew(3), ItemManager.woodItem.createNew(3), ItemManager.doorItem.createNew(3)));
        recipesList.add(new CraftingRecipe(ItemManager.sandItem.createNew(1), ItemManager.coalItem.createNew(1), ItemManager.glassItem.createNew(1)));
        recipesList.add(new CraftingRecipe(ItemManager.rawIronItem.createNew(3), ItemManager.coalItem.createNew(1), ItemManager.ironItem.createNew(1)));
        recipesList.add(new CraftingRecipe(ItemManager.woodItem.createNew(1), ItemManager.woodItem.createNew(1), ItemManager.stickItem.createNew(4)));
        recipesList.add(new CraftingRecipe(ItemManager.stickItem.createNew(1), ItemManager.ironItem.createNew(2), ItemManager.swordItem.createNew(1)));
    }

    public void update()
    {
        if (handler.getKeyManager().keyJustDown(KeyEvent.VK_R))
            active = !active;

        if (!active)
            return;

        listCenterX = 11 + (20 * ((handler.getGame().getWidth()) - 20) / 197);
        listCenterY = y + (75 * ((handler.getGame().getHeight()) - 20) / 148);

        if (handler.getKeyManager().keyJustDown(KeyEvent.VK_SPACE))
        {
            craft(recipesList.get(selectedItem));
        }
        if (handler.getKeyManager().keyJustDown(KeyEvent.VK_W))
            selectedItem--;
        if (handler.getKeyManager().keyJustDown(KeyEvent.VK_S))
            selectedItem++;
    }

    public void craft(CraftingRecipe recipe)
    {
        Item item = null;
        Item item2 = null;

        for (Item i : inventory.getInventoryItems())
        {
            if (i.getID() == recipe.getItem1().getID() && i.getCount() >= recipe.getItem1().getCount())
            {
                for (Item t : inventory.getInventoryItems())
                {
                    if (t.getID() == recipe.getItem2().getID() && t.getCount() >= recipe.getItem2().getCount())
                    {
                        item = i;
                        item2 = t;
                        break;
                    }
                }
            }
        }

        if (item != null && item2 != null)
        {
            item.setCount(item.getCount() - recipe.getItem1().getCount());
            item2.setCount(item2.getCount() - recipe.getItem2().getCount());
            inventory.addItem(recipe.getProduct().createNew(recipe.getCount()));
            return;
        }
    }

    public void render(Graphics g)
    {
        if (!active)
        {
            if (y < handler.getGame().getHeight() - 1)
            {
                y += 30;
                g.drawImage(Assets.craftingScreen, 10, y, handler.getGame().getWidth() - 20, handler.getGame().getHeight() - 20, null);
            }
            if (y > handler.getGame().getHeight() - 1)
                y = handler.getGame().getHeight() - 1;
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

        g.drawImage(Assets.craftingScreen, 10, y, handler.getGame().getWidth() - 20, handler.getGame().getHeight() - 20, null);

        int len = recipesList.size();

        if (len == 0)
            return;

        for (int i = -4; i < 5; i++)
        {
            if (selectedItem + i < 0 || selectedItem + i >= len)
                continue;
            if (i == 0)
            {
                Text.drawString(g, "> " + recipesList.get(selectedItem + i).getName() + " <", listCenterX, listCenterY + i * (12 * ((handler.getGame().getHeight()) - 20) / 148), false, Color.YELLOW, Assets.font56);
            }
            else
            {
                Text.drawString(g, "  " + recipesList.get(selectedItem + i).getName(), listCenterX, listCenterY + i * (12 * ((handler.getGame().getHeight()) - 20) / 148), false, Color.BLACK, Assets.font56);
            }
        }

        if (selectedItem < 0)
            selectedItem = recipesList.size() - 1;
        else if (selectedItem >= recipesList.size())
            selectedItem = 0;

        g.drawImage(recipesList.get(selectedItem).getItem1().getTexture(),  150 * ((handler.getGame().getWidth()) - 100) / 197, y + 27 * ((handler.getGame().getHeight()) - 20) / 148, 75, 75, null);
        g.drawImage(recipesList.get(selectedItem).getItem2().getTexture(), 10 + 150 * ((handler.getGame().getWidth()) + 150) / 197, y + 27 * ((handler.getGame().getHeight()) - 20) / 148, 75, 75, null);
        g.drawImage(recipesList.get(selectedItem).getProduct().getTexture(), 10 + 150 * ((handler.getGame().getWidth())) / 197, y + 75 * ((handler.getGame().getHeight()) - 20) / 148, 100, 100, null);
        Text.drawString(g, Integer.toString(recipesList.get(selectedItem).getItem1().getCount()), 10 + 155 * ((handler.getGame().getWidth()) - 120) / 197, y + 64 * ((handler.getGame().getHeight()) - 115) / 148, false, Color.BLACK, Assets.font28);
        Text.drawString(g, Integer.toString(recipesList.get(selectedItem).getItem2().getCount()), 173 * ((handler.getGame().getWidth())) / 197, y + 64 * ((handler.getGame().getHeight()) - 115) / 148, false, Color.BLACK, Assets.font28);
        Text.drawString(g, Integer.toString(recipesList.get(selectedItem).getProduct().getCount()), 155 * ((handler.getGame().getWidth())) / 197, y + 106 * ((handler.getGame().getHeight())) / 148, false, Color.BLACK, Assets.font56);
    }

    // getters n setters

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
}
