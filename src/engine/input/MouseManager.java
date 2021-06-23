package engine.input;

import engine.ui.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener
{
    private boolean leftPressed, rightPressed, leftJustPressed, rightJustPressed, leftCantPress, rightCantPress;
    private int mouseX, mouseY;
    private UIManager uiManager;

    public void update()
    {
        if (leftCantPress && !leftPressed)
        {
            leftCantPress = false;
        } else if (leftJustPressed)
        {
            leftCantPress = true;
            leftJustPressed = false;
        }
        if (!leftCantPress && leftPressed)
        {
            leftJustPressed = true;
        }

        if (rightCantPress && !rightPressed)
        {
            rightCantPress = false;
        } else if (rightJustPressed)
        {
            rightCantPress = true;
            rightJustPressed = false;
        }
        if (!rightCantPress && rightPressed)
        {
            rightJustPressed = true;
        }
    }

    public MouseManager()
    {

    }

    public void setUIManager(UIManager uiManager)
    {
        this.uiManager = uiManager;
    }

    public boolean isLeftJustPressed()
    {
        return leftJustPressed;
    }

    public boolean isRightJustPressed()
    {
        return rightJustPressed;
    }

    public boolean isLeftPressed()
    {
        return leftPressed;
    }

    public boolean isRightPressed()
    {
        return rightPressed;
    }

    public int getMouseX()
    {
        return mouseX;
    }

    public int getMouseY()
    {
        return mouseY;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
            leftPressed = true;
        else if (e.getButton() == MouseEvent.BUTTON3)
            rightPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
            leftPressed = false;
        else if (e.getButton() == MouseEvent.BUTTON3)
            rightPressed = false;

        if (uiManager != null)
            uiManager.onMouseRelease(e);
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();

        if (uiManager != null)
            uiManager.onMouseMove(e);
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();

        if (uiManager != null)
            uiManager.onMouseMove(e);
    }
}
