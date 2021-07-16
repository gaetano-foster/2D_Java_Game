package engine.entities;

import engine.entities.creatures.Player;
import engine.utils.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager
{
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = (a, b) ->
    {
        if (a.getY() < b.getY())
            return -1;
        return 1;
    };

    public EntityManager(Handler handler, Player player)
    {
        this.handler = handler;
        this.player = player;

        entities = new ArrayList<>();
        entities.add(player);
    }

    public void update()
    {
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext())
        {
            Entity e = it.next();

            if ((e.getX() - handler.getCamera().getxOffset() > -50 && e.getY() - handler.getCamera().getyOffset() > -50 && e.getX() - handler.getCamera().getxOffset() < handler.getGame().getWidth() && e.getY() - handler.getCamera().getyOffset() < handler.getGame().getHeight()) || e == handler.getPlayer())
                e.update();
            if(!e.isActive())
            {
                it.remove();
            }
        }
        try
        {
            entities.sort(renderSorter);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Error: Illegal arguments. \nTrying to sort again...");
        }
    }

    public void render(Graphics g)
    {
        for (Entity e : entities)
        {
            if (e.getX() - handler.getCamera().getxOffset() > -50 && e.getY() - handler.getCamera().getyOffset() > -50 && e.getX() - handler.getCamera().getxOffset() < handler.getGame().getWidth() && e.getY() - handler.getCamera().getyOffset() < handler.getGame().getHeight())
                e.render(g);
        }
        player.topRender(g);
    }

    public void addEntity(Entity e)
    {
        entities.add(e);
    }

    public Handler getHandler()
    {
        return handler;
    }

    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public ArrayList<Entity> getEntities()
    {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities)
    {
        this.entities = entities;
    }
}
