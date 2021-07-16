package engine.tiles;

import engine.tiles.notsolid.*;
import engine.tiles.solid.*;
import engine.utils.Handler;

public class TileManager
{
    private Handler handler;

    public Tile[] tiles;

    public Tile grassTile;
    public Tile dirtTile;
    public Tile stoneTile;
    public Tile stoneWallTile;
    public Tile woodTile;
    public Tile woodWallTile;
    public Tile waterTile;
    public Tile lavaTile;
    public Tile sandTile;
    public Tile grassWallTile;
    public Tile dirtWallTile;
    public Tile sandWallTile;
    public Tile iceTile;
    public Tile iceWallTile;
    public Tile obsidianTile;
    public Tile obsidianWallTile;
    public Tile doorTile;
    public Tile openDoorTile;
    public Tile glassTile;


    public TileManager()
    {

    }

    public void reloadTiles()
    {
        tiles = new Tile[256];

        grassTile = new GrassTile(handler,0);
        dirtTile = new DirtTile(handler,1);
        stoneTile = new StoneFloorTile(handler,2);
        stoneWallTile = new StoneWallTile(handler,3);
        woodTile = new WoodFloorTile(handler,4);
        woodWallTile = new WoodWallTile(handler,5);
        waterTile = new WaterTile(handler,6);
        lavaTile = new LavaTile(handler,7);
        sandTile = new SandTile(handler,8);
        grassWallTile = new GrassWallTile(handler,9);
        dirtWallTile = new DirtWallTile(handler,10);
        sandWallTile = new SandWallTile(handler,11);
        iceTile = new IceTile(handler,12);
        iceWallTile = new IceWallTile(handler,13);
        obsidianTile = new ObsidianTile(handler,14);
        obsidianWallTile = new ObsidianWallTile(handler,15);
        doorTile = new DoorTile(handler, 16);
        openDoorTile = new OpenDoorTile(handler, 17);
        glassTile = new GlassTile(handler, 18);
    }

    public Handler getHandler()
    {
        return handler;
    }

    public Tile getTileByID(int id)
    {
        return tiles[id];
    }

    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }
}
