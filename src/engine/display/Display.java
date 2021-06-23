package engine.display;

import javax.swing.*;
import java.awt.*;

public class Display
{
    private JFrame frame;
    private Canvas canvas;

    private int width, height;
    private String title;

    public Display(int width, int height, String title)
    {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay()
    {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();

        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public void setTitle(String newTitle)
    {
        frame.setTitle(newTitle);
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public String getTitle()
    {
        return title;
    }

    public JFrame getFrame()
    {
        return frame;
    }
}
