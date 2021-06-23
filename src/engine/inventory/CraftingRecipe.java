package engine.inventory;

import engine.items.Item;

public class CraftingRecipe
{
    private Item item1;
    private Item item2;
    private Item product;

    public CraftingRecipe(Item item1, Item item2, Item product)
    {
        this.item1 = item1;
        this.item2 = item2;
        this.product = product;
    }

    public String getName()
    {
        return product.getName();
    }

    public int getCount()
    {
        return product.getCount();
    }

    public Item getItem1()
    {
        return item1;
    }

    public Item getItem2()
    {
        return item2;
    }

    public Item getProduct()
    {
        return product;
    }
}
