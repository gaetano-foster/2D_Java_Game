package engine.entities.creatures.enemies;

import engine.entities.creatures.Creature;
import engine.gfx.Animation;
import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.utils.Handler;

import javax.annotation.processing.SupportedSourceVersion;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Troll extends Creature
{
    private float startingSpeed;
    private BufferedImage def = Assets.trollRight;
    private Animation animUp, animDown, animLeft, animRight;
    private long lastTime, timer;
    private int movingTime;
    private float increasedSpeed;
    private int vision = 6 * Tile.SIZE;
    private Random random;

    public Troll(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, width, height);

        setMaxHealth(20);
        random = new Random();
        bounds.x = 15;
        bounds.y = 20;
        bounds.width = 20;
        bounds.height = 29;
        speed = 3;
        startingSpeed = speed;
        increasedSpeed = speed + 1;
        movingTime = random.nextInt(1000 - 500) + 500;
        timer = movingTime;
        random = new Random();
        lastTime = System.currentTimeMillis();

        animUp = new Animation(100, Assets.troll_up);
        animDown = new Animation(100, Assets.troll_down);
        animLeft = new Animation(100, Assets.troll_left);
        animRight = new Animation(100, Assets.troll_right);
    }

    @Override
    protected void ai()
    {
       int playerX = (int)(handler.getPlayer().getX() - handler.getCamera().getxOffset()), playerY = (int)(handler.getPlayer().getY() - handler.getCamera().getyOffset());
       int a = playerY - (int)(y - handler.getCamera().getyOffset() + handler.getPlayer().getHeight() / 2);
       int b = playerX - (int)(x - handler.getCamera().getxOffset() + handler.getPlayer().getWidth() / 2);
       int aSQ = a * a;
       int bSQ = b * b;

       int distanceToPlayer = (int)Math.sqrt(aSQ + bSQ);

       if (distanceToPlayer <= vision)
       {
           xMove = ((handler.getPlayer().getX() - handler.getCamera().getxOffset()) - (x - handler.getCamera().getxOffset()));
           yMove = ((handler.getPlayer().getY() - handler.getCamera().getyOffset()) - (y - handler.getCamera().getyOffset()));

           float factor = speed / (float)Math.sqrt(xMove * xMove + yMove * yMove);

           xMove *= factor;
           yMove *= factor;
       }

       if (distanceToPlayer > vision)
       {
           if (timer > movingTime)
           {
               xMove = random.nextInt((int)speed + (int)speed) - speed;
               yMove = random.nextInt((int)speed + (int)speed) - speed;

               if (xMove > 0)
                   xMove = speed;
               else if (xMove < 0)
                   xMove = -speed;

               if (yMove > 0)
                   yMove = -speed;
               else if (yMove < 0)
                   yMove = speed;

               timer = 0;
               movingTime = random.nextInt(1000 - 500) + 500;
           }
       }
    }

    @Override
    public void die()
    {

    }

    @Override
    public void update()
    {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        move();
        ai();

        animUp.update();
        animDown.update();
        animLeft.update();
        animRight.update();
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
    }

    public BufferedImage getCurrentAnimationFrame()
    {
        if (xMove < 0)
        {
            def = Assets.trollLeft;
            return animLeft.getCurrentFrame();
        }
        else if (xMove > 0)
        {
            def = Assets.trollRight;
            return animRight.getCurrentFrame();
        }
        else if (yMove < 0)
        {
            def = Assets.trollUp;
            return animUp.getCurrentFrame();
        }
        else if (yMove > 0)
        {
            def = Assets.trollDown;
            return animDown.getCurrentFrame();
        }
        else
        {
            return def;
        }
    }
}
