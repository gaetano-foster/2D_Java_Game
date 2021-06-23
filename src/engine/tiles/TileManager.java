package engine.tiles;

import engine.tiles.notsolid.*;
import engine.tiles.solid.*;
import engine.utils.Handler;

public class TileManager
{
    public static Tile[] tiles = new Tile[256];

    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile stoneTile = new StoneFloorTile(2);
    public static Tile stoneWallTile = new StoneWallTile(3);
    public static Tile woodTile = new WoodFloorTile(4);
    public static Tile woodWallTile = new WoodWallTile(5);
    public static Tile waterTile = new WaterTile(6);
    public static Tile lavaTile = new LavaTile(7);
    public static Tile sandTile = new SandTile(8);
    public static Tile grassWallTile = new GrassWallTile(9);
    public static Tile dirtWallTile = new DirtWallTile(10);
    public static Tile sandWallTile = new SandWallTile(11);
    public static Tile iceTile = new IceTile(12);
    public static Tile iceWallTile = new IceWallTile(13);
    public static Tile obsidianTile = new ObsidianTile(14);
    public static Tile obsidianWallTile = new ObsidianWallTile(15);
}
