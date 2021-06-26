package engine.utils;

import engine.Game;
import engine.entities.creatures.Player;
import engine.gfx.Camera;
import engine.input.KeyManager;
import engine.input.MouseManager;
import engine.tiles.TileManager;
import engine.worlds.World;

public class Handler
{
    private Game game;
    private World world;

    public Handler(Game game)
    {
        this.game = game;
    }

    public Camera getCamera()
    {
        return game.getCamera();
    }

    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager()
    {
        return game.getMouseManager();
    }

    public int getWidth()
    {
        return game.getWidth();
    }

    public int getHeight()
    {
        return game.getHeight();
    }

    public Game getGame()
    {
        return game;
    }

    public TileManager getTileManager()
    {
        return game.getTileManager();
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public World getWorld()
    {
        return world;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

    public Player getPlayer()
    {
        return world.getPlayer();
    }
}
