package engine.entities.creatures;

import engine.Game;
import engine.entities.Entity;
import engine.tiles.TileManager;
import engine.utils.Handler;
import engine.tiles.Tile;

public abstract class Creature extends Entity
{
    public static final float DEFAULT_STAMINA = 15;
    public static final float DEFAULT_SPEED = 2.5f;
    public static final int DEFAULT_WIDTH = 50;
    public static final int DEFAULT_HEIGHT = 50;

    protected float stamina;
    protected float speed;
    protected float xMove, yMove;
    protected float halfSpeed;

    public Creature(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        stamina = DEFAULT_STAMINA;
        xMove = 0;
        yMove = 0;
    }

    public void move()
    {
        if(xMove != 0 && yMove != 0)
        {
            xMove *= 0.7;
            yMove *= 0.7;
        }

        if (!checkEntityCollisions(xMove, 0f))
            moveX();
        if (!checkEntityCollisions(0f, yMove))
            moveY();
    }

    protected abstract void ai();

    public void moveX()
    {
        if (xMove > 0)
        {
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.SIZE;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.SIZE) &&
            !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.SIZE))
            {
                x += xMove * Game.deltaTime;
            }
            else
            {
                if (handler.getWorld().getTile(tx, (int) (y + bounds.y) / Tile.SIZE) == TileManager.waterTile)
                    speed =

                x = tx * Tile.SIZE - bounds.x - bounds.width - 1;
            }
        }
        else if (xMove < 0)
        {
            int tx = (int) (x + xMove + bounds.x) / Tile.SIZE;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.SIZE) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.SIZE))
            {
                x += xMove * Game.deltaTime;
            }
            else
            {
                x = tx * Tile.SIZE + Tile.SIZE - bounds.x;
            }
        }
    }

    public void moveY()
    {
        if (yMove < 0)
        {
            int ty = (int) (y + yMove + bounds.y) / Tile.SIZE;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.SIZE, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.SIZE, ty))
            {
                y += yMove * Game.deltaTime;
            }
            else
            {
                y = ty * Tile.SIZE + Tile.SIZE - bounds.y;
            }
        }
        else if (yMove > 0)
        {
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.SIZE;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.SIZE, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.SIZE, ty))
            {
                y += yMove * Game.deltaTime;
            }
            else
            {
                y = ty * Tile.SIZE - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y)
    {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    public float getXMove()
    {
        return xMove;
    }

    public void setXMove(float xMove)
    {
        this.xMove = xMove;
    }

    public float getYMove()
    {
        return yMove;
    }

    public void setYMove(float yMove)
    {
        this.yMove = yMove;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public float getSpeed()
    {
        return speed;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public float getStamina()
    {
        return stamina;
    }

    public void setStamina(float stamina)
    {
        this.stamina = stamina;
    }
}
