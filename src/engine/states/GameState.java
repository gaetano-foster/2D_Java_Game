package engine.states;

import engine.utils.Handler;
import engine.worlds.World;

import java.awt.*;

public class GameState extends State
{

    private World world;

    public GameState(Handler handler)
    {
        super(handler);
        world = new World(handler);//handler, "res/worlds/world.txt");
        handler.setWorld(world);
    }

    @Override
    public void update()
    {
        world.update();
    }

    @Override
    public void render(Graphics g)
    {
        world.render(g);
    }
}
