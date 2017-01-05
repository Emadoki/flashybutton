package emadoki.flashybutton.app.object;

import android.graphics.Canvas;

public abstract class BaseEntity
{
    public abstract void update(float progress);
    public abstract void render(Canvas canvas);
    public abstract void setColor(int color);
}
