package emadoki.flashybutton.app.object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import emadoki.flashybutton.app.Vector2D;
import static emadoki.flashybutton.app.views.FireworkView.*;

public class FireworkBall extends BaseEntity
{
    private Paint paint;
    private Vector2D position;
    private Vector2D center;

    private float radius;
    private int id;
    private boolean isSecondary;

    public FireworkBall(int id)
    {
        this.id = id;
        paint = new Paint();
        paint.setColor(Color.TRANSPARENT);

        position = new Vector2D();
        center = new Vector2D();

        isSecondary = false;
    }

    public FireworkBall(int id, boolean isSecondary)
    {
        this(id);
        this.isSecondary = isSecondary;
    }

    @Override
    public void update(float progress)
    {
        if (isSecondary)
        {
            if (progress > 0.3f)
                radius = MAX_RADIUS * (1f - progress);
        }
        else
            radius = MAX_RADIUS * (1f - (0.4f * progress));

        int extra = isSecondary ? 15 : 0;
        double rad = Math.toRadians((360 / AMOUNT * id) + extra);
        float x = (float) (center.x + (MAX_DISTANCE * progress) * Math.cos(rad));
        float y = (float) (center.y + (MAX_DISTANCE * progress) * Math.sin(rad));

        position.set(x, y);
    }

    @Override
    public void render(Canvas canvas)
    {
        canvas.drawCircle(position.x, position.y, radius, paint);
    }

    @Override
    public void setColor(int color)
    {
        paint.setColor(color);
    }

    public void setCenter(float x, float y)
    {
        this.center.set(x, y);
    }
}
