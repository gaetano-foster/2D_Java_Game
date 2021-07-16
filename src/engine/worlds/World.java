package engine.worlds;

import engine.entities.Entity;
import engine.entities.EntityManager;
import engine.entities.creatures.enemies.Troll;
import engine.entities.statics.*;
import engine.gfx.FastNoiseLite;
import engine.items.ItemManager;
import engine.utils.Handler;
import engine.entities.creatures.Player;
import engine.tiles.Tile;
import engine.utils.Utils;

import java.awt.*;
import java.util.Random;

public class World
{

    private Handler handler;
    private Player player;
    private static int width;
    private static int height;
    private static int spawnX = 0;
    private static int spawnY = 0;
    private static int[][] chunk;
    private static boolean[][] transChunk;
    private static Tile[][] visibleBelowChunk;
    private static int[][] belowChunk;
    private static int[][] defBelowChunk;
    private Random random = new Random();
    private FastNoiseLite noise = new FastNoiseLite();
    public int xStart;
    public int xEnd;
    public int yStart;
    public int yEnd;
    private EntityManager entityManager;
    private ItemManager itemManager;

    public World(Handler handler, String path)
    {
        noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        this.handler = handler;
        player = new Player(handler, 0, 0);
        entityManager = new EntityManager(handler, player);
        itemManager = new ItemManager(handler);

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
        entityManager.addEntity(new Troll(handler, 20 * Tile.SIZE, 20 * Tile.SIZE, Tile.SIZE, Tile.SIZE));

        xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.SIZE);
        xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.SIZE + 1);
        yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.SIZE);
        yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.SIZE + 1);
    }

    public World(Handler handler)
    {
        this.handler = handler;

        player = new Player(handler, 0, 0);
        entityManager = new EntityManager(handler, player);
        itemManager = new ItemManager(handler);

        constructWorld();

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
        entityManager.addEntity(new Troll(handler, 20 * Tile.SIZE, 20 * Tile.SIZE, Tile.SIZE, Tile.SIZE));

        xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.SIZE);
        xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.SIZE + 1);
        yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.SIZE);
        yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.SIZE + 1);

        transChunk = new boolean[chunk.length][chunk.length];
        visibleBelowChunk = new Tile[chunk.length][chunk.length];

        updateWorld();
    }

    public void update()
    {
        xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.SIZE);
        xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.SIZE + 1);
        yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.SIZE);
        yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.SIZE + 1);
        itemManager.update();
        entityManager.update();
    }

    public void render(Graphics g)
    {
        for (int y = yStart; y < yEnd; y++)
        {
            for (int x = xStart; x < xEnd; x++)
            {

                if (visibleBelowChunk[x][y] != null)
                    visibleBelowChunk[x][y].render(g, (int) (x * Tile.SIZE - handler.getCamera().getxOffset()), (int) (y * Tile.SIZE - handler.getCamera().getyOffset()));

                getTile(x, y).render(g, (int) (x * Tile.SIZE - handler.getCamera().getxOffset()), (int) (y * Tile.SIZE - handler.getCamera().getyOffset()));
            }
        }

        itemManager.render(g);
        entityManager.render(g);
    }

    public int getTileID(int x, int y)
    {
        if (x < 0 || y < 0 || x >= width || y >= width)
        {
            return handler.getTileManager().grassTile.getID();
        }

        return chunk[x][y];
    }

    public Tile getTile(int x, int y)
    {
        if (x < 0 || y < 0 || x >= width || y >= width)
        {
            return handler.getTileManager().grassTile;
        }

        Tile t = handler.getTileManager().tiles[chunk[x][y]];
        if (t == null)
        {
            return handler.getTileManager().grassTile;
        }
        return t;
    }

    public Tile getBelowTile(int x, int y)
    {
        if (x < 0 || y < 0 || x >= width || y >= width)
        {
            return handler.getTileManager().dirtTile;
        }

        Tile t = handler.getTileManager().tiles[belowChunk[x][y]];
        if (t == null)
        {
            return handler.getTileManager().dirtTile;
        }
        return t;
    }

    public Tile getDefBelowTile(int x, int y)
    {
        if (x < 0 || y < 0 || x >= width || y >= width)
        {
            return handler.getTileManager().dirtTile;
        }

        Tile t = handler.getTileManager().tiles[defBelowChunk[x][y]];
        if (t == null)
        {
            return handler.getTileManager().dirtTile;
        }

        return t;
    }

    public void setBelowTile(int x, int y, int ID)
    {
        belowChunk[x][y] = ID;
    }

    public void setTile(int x, int y, int ID)
    {
        chunk[x][y] = ID;
    }

    public void constructWorld()
    {
        width = 256;
        height = 256;
        spawnX = (width * Tile.SIZE) / 2;
        spawnY = (height * Tile.SIZE) / 2;
        chunk = new int[256][256];
        belowChunk = new int[256][256];
        defBelowChunk = new int[256][256];
        Random random = new Random();
        int seed = random.nextInt();
        noise.SetSeed(seed);
        System.out.println("Seed: " + noise.GetSeed());

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                // TODO: create a "biome map" based on a noise function which affects the type of tiles to be placed

                float noiseValue = noise.GetNoise(x, y);

                if (noiseValue > 0.4f && noiseValue < 0.8f)
                    noiseValue = 8;
                else if (noiseValue < .3 && noiseValue > 0.0000000000000000000000000000000000000001)
                    noiseValue = 2;
                else if (noiseValue > 0.0000000000000000000000000000000001f && noiseValue < 0.4f)
                    noiseValue = 0;
                else if (noiseValue > 0.8f)
                    noiseValue = 6;


                chunk[x][y] = (int)noiseValue;
                if (noiseValue == 6)
                {
                    belowChunk[x][y] = 8;
                    defBelowChunk[x][y] = 8;
                }
                else
                {
                    belowChunk[x][y] = 1;
                    defBelowChunk[x][y] = 1;
                }
            }
        }

        // TODO: generate trees and other static entities based on noise function as well

        for (int i = 0; i < 1000; i++)
        {
            if (i % 2 == 0)
            {
                int x = random.nextInt(width);
                int y = random.nextInt(height);

                if (chunk[(int) x][(int) y] == 8)
                    entityManager.addEntity(new SandDune(handler, x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE));
            }
        }

        for (int i = 0; i < 8000; i++)
        {
            if (i % 2 == 0)
            {
                int x = random.nextInt(width);
                int y = random.nextInt(height);

                if (chunk[(int) x][(int) y] == 0)
                    entityManager.addEntity(new Tree(handler, x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE));
                if (chunk[x][y] == 8)
                {
                    boolean exists = random.nextBoolean();
                    if (exists)
                        entityManager.addEntity(new PalmTree(handler, x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE));
                }
            }
        }

        for (int i = 0; i < 1000; i++)
        {
            if (i % 2 == 0)
            {
                int x = random.nextInt(width);
                int y = random.nextInt(height);

                if (chunk[(int) x][(int) y] != 6 && chunk[(int) x][(int) y] != 8 && chunk[x][y] != 2)
                    entityManager.addEntity(new GreyRock(handler, x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE));
                if (chunk[x][y] == 8)
                {
                    boolean exists = random.nextBoolean();
                    if (exists)
                        entityManager.addEntity(new GreyRock(handler, x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE));
                }
                if (chunk[x][y] == 2)
                {
                    boolean exists = random.nextBoolean();
                    if (exists)
                        entityManager.addEntity(new CoalOre(handler, x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE));
                }
            }
        }

        for (int i = 0; i < 960; i++)
        {
            if (i % 2 == 0)
            {
                int x = random.nextInt(width);
                int y = random.nextInt(height);

                if (chunk[(int) x][(int) y] != 6 && chunk[x][y] != 2)
                    entityManager.addEntity(new BrownRock(handler, x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE));

                if (chunk[x][y] == 2)
                {
                    entityManager.addEntity(new IronOre(handler, x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE));
                }
            }
        }
    }

    public void updateChunk()
    {
        for (int y = yStart; y < yEnd; y++)
        {
            for (int x = xStart; x < xEnd; x++)
            {
                transChunk[x][y] = handler.getTileManager().tiles[chunk[x][y]].isTransparent();

                if (!transChunk[x][y])
                    visibleBelowChunk[x][y] = null;
                else
                {
                    visibleBelowChunk[x][y] = handler.getTileManager().getTileByID(belowChunk[x][y]);
                }

                getTile(x, y).update();
            }
        }
    }

    public void updateWorld()
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                transChunk[x][y] = handler.getTileManager().tiles[chunk[x][y]].isTransparent();

                if (!transChunk[x][y])
                    visibleBelowChunk[x][y] = null;
                else
                {
                    visibleBelowChunk[x][y] = handler.getTileManager().getTileByID(belowChunk[x][y]);
                }

                getTile(x, y).update();
            }
        }
    }

    private void loadWorld(String path)
    {
        String file = Utils.loadFileAsString(path);

        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]) * Tile.SIZE;
        spawnY = Utils.parseInt(tokens[3]) * Tile.SIZE;

        chunk = new int[width][height];
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                chunk[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }

        for (int i = 0; i < 50; i++)
        {
            if (i % 2 == 0)
            {
                float x = random.nextFloat() * width * Tile.SIZE;
                float y = random.nextFloat() * height * Tile.SIZE;

                if (getTile((int)x - Tile.SIZE, (int)y - Tile.SIZE).getID() == 0 && getTile((int)x + Tile.SIZE, (int)y - Tile.SIZE).getID() == 0 && getTile((int)x + Tile.SIZE, (int)y + Tile.SIZE).getID() == 0 && getTile((int)x - Tile.SIZE, (int)y + Tile.SIZE).getID() == 0 && getTile((int)x, (int)y).getID() == 0 && getTile((int)x + Tile.SIZE, (int)y).getID() == 0 && getTile((int)x + Tile.SIZE, (int)y + Tile.SIZE).getID() == 0 && getTile((int)x, (int)y + Tile.SIZE).getID() == 0)
                    entityManager.addEntity(new Tree(handler, x, y, Tile.SIZE, Tile.SIZE));
            }
        }

        for (int i = 0; i < 25; i++)
        {
            if (i % 2 == 0)
            {
                float x = random.nextFloat() * width * Tile.SIZE;
                float y = random.nextFloat() * height * Tile.SIZE;

                if (getTile((int)x - Tile.SIZE, (int)y - Tile.SIZE).getID() == 0 && getTile((int)x + Tile.SIZE, (int)y - Tile.SIZE).getID() == 0 && getTile((int)x + Tile.SIZE, (int)y + Tile.SIZE).getID() == 0 && getTile((int)x - Tile.SIZE, (int)y + Tile.SIZE).getID() == 0 && getTile((int)x, (int)y).getID() == 0 && getTile((int)x + Tile.SIZE, (int)y).getID() == 0 && getTile((int)x + Tile.SIZE, (int)y + Tile.SIZE).getID() == 0 && getTile((int)x, (int)y + Tile.SIZE).getID() == 0)
                    entityManager.addEntity(new GreyRock(handler, x, y, Tile.SIZE, Tile.SIZE));
            }
        }

        for (int i = 0; i < 12; i++)
        {
            if (i % 2 == 0)
            {
                float x = random.nextFloat() * width * Tile.SIZE;
                float y = random.nextFloat() * height * Tile.SIZE;

                if (getTile((int)x - Tile.SIZE, (int)y - Tile.SIZE).getID() == 0 && getTile((int)x + Tile.SIZE, (int)y - Tile.SIZE).getID() == 0 && getTile((int)x + Tile.SIZE, (int)y + Tile.SIZE).getID() == 0 && getTile((int)x - Tile.SIZE, (int)y + Tile.SIZE).getID() == 0 && getTile((int)x, (int)y).getID() == 0 && getTile((int)x + Tile.SIZE, (int)y).getID() == 0 && getTile((int)x + Tile.SIZE, (int)y + Tile.SIZE).getID() == 0 && getTile((int)x, (int)y + Tile.SIZE).getID() == 0)
                    entityManager.addEntity(new BrownRock(handler, x, y, Tile.SIZE, Tile.SIZE));
            }
        }
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getSpawnX()
    {
        return spawnX;
    }

    public int getSpawnY()
    {
        return spawnY;
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Handler getHandler()
    {
        return handler;
    }

    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }

    public ItemManager getItemManager()
    {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager)
    {
        this.itemManager = itemManager;
    }
}
