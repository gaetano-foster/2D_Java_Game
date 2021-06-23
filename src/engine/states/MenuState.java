package engine.states;

import engine.gfx.Assets;
import engine.tiles.Tile;
import engine.ui.ClickListener;
import engine.ui.UIImageButton;
import engine.ui.UIManager;
import engine.utils.Handler;

import java.awt.*;

public class MenuState extends State
{
    private UIManager uiManager;

    public MenuState(Handler handler)
    {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton((float) handler.getWidth() / 2, (float) handler.getHeight() / 2, Tile.SIZE * 2, Tile.SIZE, Assets.btn_start, new ClickListener()
        {
            @Override
            public void onClick()
            {
                State.setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void update()
    {
        uiManager.update();
    }

    @Override
    public void render(Graphics g)
    {
        uiManager.render(g);
    }
}
