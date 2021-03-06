package engine.entities;

import engine.utils.Handler;

import java.awt.*;

public abstract class Entity
{
    protected static final int DEFAULT_MAX_HEALTH = 10;
    protected static final int DEFAULT_POWER = 2;
    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean active = true;
    protected int health;
    protected int maxHealth;
    protected int power;

    public Entity(Handler handler, float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.handler = handler;
        maxHealth = DEFAULT_MAX_HEALTH;
        health = maxHealth;

        bounds = new Rectangle(0, 0, width, height);
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset)
    {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset)
    {
        for (Entity e : handler.getWorld().getEntityManager().getEntities())
        {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getHealth()
    {
        return health;
    }

    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
    }

    public abstract void die();

    public void hurt(int amt)
    {
        health -= amt;
        if (health <= 0)
        {
            active = false;
            die();
        }
    }

    public void heal(int amt)
    {
        health += amt;
        if (health > maxHealth)
            health = maxHealth;
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setPower(int power)
    {
        this.power = power;
    }
}
