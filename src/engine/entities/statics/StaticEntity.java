package engine.entities.statics;

import engine.entities.Entity;
import engine.utils.Handler;

public abstract class StaticEntity extends Entity
{

    public StaticEntity(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, width, height);
    }
}
