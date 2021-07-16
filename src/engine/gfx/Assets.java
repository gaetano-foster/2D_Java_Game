package engine.gfx;

import engine.fonts.FontLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets
{
    private static final int size = 16;

    public static Font font28, font56, font168;

    public static BufferedImage
            grass, grassWall, dirt, dirtWall,
            water, lava, stoneFloor, stoneWall,
            woodFloor, woodWall, stick, sand, sandWall,
            ice, iceWall, obsidian, obsidianWall,
            door, openDoor, glass, furnace, litFurnace,
            rock, rock1, coalOre, ironOre, tree, palm, woodItem, sandDune,
            obsidianRock, obsidianShard, iceRock, iceCubes, coal, rawIron,
            iron, doorItem, sword, playerRight, playerLeft, playerDown, playerUp,
            playerRightStepOne, playerLeftStepOne, playerDownStepOne,
            playerUpStepOne, playerRightStepTwo, playerLeftStepTwo, playerDownStepTwo,
            playerUpStepTwo, playerRightSwim, playerLeftSwim, playerDownSwim,
            playerUpSwim, playerRightPunch, playerLeftPunch, playerDownPunch,
            playerUpPunch, trollRight, trollLeft, trollDown, trollUp,
            trollRightStepOne, trollLeftStepOne, trollDownStepOne, trollUpStepOne,
            trollRightStepTwo, trollLeftStepTwo, trollDownStepTwo, trollUpStepTwo,
            play, playHighlight, option, optionHighlight, quit, quitHighlight,
            inventoryScreen, craftingScreen, furnaceScreen, activeItemHolder;

    public static BufferedImage[] player_down;
    public static BufferedImage[] player_up;
    public static BufferedImage[] player_left;
    public static BufferedImage[] player_right;

    public static BufferedImage[] troll_down;
    public static BufferedImage[] troll_up;
    public static BufferedImage[] troll_left;
    public static BufferedImage[] troll_right;

    public static BufferedImage[] btn_start, btn_opt, btn_quit;

    public static void load()
    {
        font28 = FontLoader.loadFont("res/fonts/consolas.ttf", 28);
        font56 = FontLoader.loadFont("res/fonts/consolas.ttf", 56);
        font168 = FontLoader.loadFont("res/fonts/consolas.ttf", 168);
        SpriteSheet tiles = new SpriteSheet(ImageLoader.loadImage("/textures/tilemap.png"));
        SpriteSheet nonTileObjects = new SpriteSheet(ImageLoader.loadImage("/textures/ntomap.png"));
        SpriteSheet ui = new SpriteSheet(ImageLoader.loadImage("/textures/ui.png"));

        //tiles

        grass = tiles.crop(0, 0, size, size, size);
        grassWall = tiles.crop(0, 1, size, size, size);
        dirt = tiles.crop(1, 0, size, size, size);
        dirtWall = tiles.crop(1, 1, size, size, size);
        water = tiles.crop(2, 0, size, size, size);
        ice = tiles.crop(2, 1, size, size, size);
        iceWall = tiles.crop(3, 1, size, size, size);
        lava = tiles.crop(3, 0, size, size, size);
        obsidian = tiles.crop(4, 1, size, size, size);
        obsidianWall = tiles.crop(5, 1, size, size, size);
        door = tiles.crop(6, 1, size, size, size);
        openDoor = tiles.crop(7, 1, size, size, size);
        stoneFloor = tiles.crop(4, 0, size, size, size);
        stoneWall = tiles.crop(5, 0, size, size, size);
        woodFloor = tiles.crop(6, 0, size, size, size);
        woodWall = tiles.crop(7, 0, size, size, size);
        sand = tiles.crop(8, 0, size, size, size);
        sandWall = tiles.crop(9, 0, size, size, size);
        glass = tiles.crop(8, 1, size, size, size);
        furnace = tiles.crop(9, 1, size, size, size);
        litFurnace = tiles.crop(0, 2, size, size, size);

        //static entities & items

        rock = nonTileObjects.crop(0, 0, size, size, size);
        rock1 = nonTileObjects.crop(1, 0, size, size, size);
        tree = nonTileObjects.crop(2, 0, size, size, size);
        sandDune = nonTileObjects.crop(4, 0, size, size, size);
        obsidianRock = nonTileObjects.crop(5, 0, size, size, size);
        obsidianShard = nonTileObjects.crop(6, 0, size, size, size);
        iceRock = nonTileObjects.crop(7, 0, size, size, size);
        iceCubes = nonTileObjects.crop(8, 0, size, size, size);
        palm = nonTileObjects.crop(9, 0, size, size, size);
        woodItem = nonTileObjects.crop(3, 0, size, size, size);
        doorItem = nonTileObjects.crop(8, 1, size, size, size);
        coalOre = nonTileObjects.crop(9, 1, size, size, size);
        coal = nonTileObjects.crop(8, 2, size, size, size);
        ironOre = nonTileObjects.crop(4, 3, size, size, size);
        rawIron = nonTileObjects.crop(5, 3, size, size, size);
        iron = nonTileObjects.crop(6, 3, size, size, size);
        stick = nonTileObjects.crop(7, 3, size, size, size);
        sword = nonTileObjects.crop(9, 2, size, size, size);

        // ui

        play = ui.crop(0, 0, 32, size, 1);
        playHighlight = ui.crop(33, 0, 32, size, 1);

        option = ui.crop(66, 0, 32, size, 1);
        optionHighlight = ui.crop(100, 0, 32, size, 1);

        quit = ui.crop(132, 0, 32, size, 1);
        quitHighlight = ui.crop(166, 0, 32, size, 1);

        inventoryScreen = ui.crop(0, 17, 197, 147);
        craftingScreen = ui.crop(0, 17 + 147, 197, 147);
        activeItemHolder = ui.crop(198, 0, 16, 21);

        // player

        playerRight = nonTileObjects.crop(0, 1, size, size, size);
        playerLeft = nonTileObjects.crop(1, 1, size, size, size);
        playerDown = nonTileObjects.crop(2, 1, size, size, size);
        playerUp = nonTileObjects.crop(3, 1, size, size, size);
        playerRightStepOne = nonTileObjects.crop(0, 2, size, size, size);
        playerLeftStepOne = nonTileObjects.crop(1, 2, size, size, size);
        playerDownStepOne = nonTileObjects.crop(2, 2, size, size, size);
        playerUpStepOne = nonTileObjects.crop(3, 2, size, size, size);
        playerRightStepTwo = nonTileObjects.crop(0, 3, size, size, size);
        playerLeftStepTwo = nonTileObjects.crop(1, 3, size, size, size);
        playerDownStepTwo = nonTileObjects.crop(2, 3, size, size, size);
        playerUpStepTwo = nonTileObjects.crop(3, 3, size, size, size);
        playerRightSwim = nonTileObjects.crop(4, 1, size, size, size);
        playerLeftSwim = nonTileObjects.crop(5, 1, size, size, size);
        playerDownSwim = nonTileObjects.crop(6, 1, size, size, size);
        playerUpSwim = nonTileObjects.crop(7, 1, size, size, size);
        playerRightPunch = nonTileObjects.crop(4, 2, size, size, size);
        playerLeftPunch = nonTileObjects.crop(5, 2, size, size, size);
        playerDownPunch = nonTileObjects.crop(6, 2, size, size, size);
        playerUpPunch = nonTileObjects.crop(7, 2, size, size, size);

        // troll

        trollRight = nonTileObjects.crop(0, 4, size, size, size);
        trollLeft = nonTileObjects.crop(1, 4, size, size, size);
        trollDown = nonTileObjects.crop(2, 4, size, size, size);
        trollUp = nonTileObjects.crop(3, 4, size, size, size);
        trollRightStepOne = nonTileObjects.crop(0, 5, size, size, size);
        trollLeftStepOne = nonTileObjects.crop(1, 5, size, size, size);
        trollDownStepOne = nonTileObjects.crop(2, 5, size, size, size);
        trollUpStepOne = nonTileObjects.crop(3, 5, size, size, size);
        trollRightStepTwo = nonTileObjects.crop(0, 6, size, size, size);
        trollLeftStepTwo = nonTileObjects.crop(1, 6, size, size, size);
        trollDownStepTwo = nonTileObjects.crop(2, 6, size, size, size);
        trollUpStepTwo = nonTileObjects.crop(3, 6, size, size, size);

        // animations

        player_down = new BufferedImage[2];

        player_down[0] = playerDownStepOne;
        player_down[1] = playerDownStepTwo;

        player_up = new BufferedImage[2];

        player_up[0] = playerUpStepOne;
        player_up[1] = playerUpStepTwo;

        player_left = new BufferedImage[2];

        player_left[0] = playerLeftStepOne;
        player_left[1] = playerLeftStepTwo;

        player_right = new BufferedImage[2];

        player_right[0] = playerRightStepOne;
        player_right[1] = playerRightStepTwo;

        troll_down = new BufferedImage[2];

        troll_down[0] = trollDownStepOne;
        troll_down[1] = trollDownStepTwo;

        troll_up = new BufferedImage[2];

        troll_up[0] = trollUpStepOne;
        troll_up[1] = trollUpStepTwo;

        troll_left = new BufferedImage[2];

        troll_left[0] = trollLeftStepOne;
        troll_left[1] = trollLeftStepTwo;

        troll_right = new BufferedImage[2];

        troll_right[0] = trollRightStepOne;
        troll_right[1] = trollRightStepTwo;

        // ui

        btn_start = new BufferedImage[2];

        btn_start[0] = play;
        btn_start[1] = playHighlight;

        btn_opt = new BufferedImage[2];

        btn_opt[0] = option;
        btn_opt[1] = optionHighlight;

        btn_quit = new BufferedImage[2];

        btn_quit[0] = quit;
        btn_quit[1] = quitHighlight;
    }
}
