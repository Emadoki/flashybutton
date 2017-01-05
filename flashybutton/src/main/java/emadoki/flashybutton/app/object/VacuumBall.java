package emadoki.flashybutton.app.object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import emadoki.flashybutton.app.Vector2D;
import static emadoki.flashybutton.app.views.VacuumView.*;


public class VacuumBall extends BaseEntity
{
    private Paint paint;
    private Vector2D position;
    private Vector2D center;

    private float radius;
    private int id;
    private boolean isSecondary;

    public VacuumBall(int id)
    {
        this.id = id;

        paint = new Paint();
        paint.setColor(Color.TRANSPARENT);

        position = new Vector2D();
        center = new Vector2D();

        isSecondary = false;
    }

    public VacuumBall(int id, boolean isSecondary)
    {
        this(id);
        this.isSecondary = isSecondary;
    }

    @Override
    public void update(float progress)
    {
        radius = isSecondary ? MAX_RADIUS : MAX_RADIUS * (1.2f - progress);
        double factor = isSecondary ? 1.1 : 1;
        double distance = MAX_DISTANCE * (factor - progress);
        double angle = isSecondary ? 90 * progress : 180 * progress;
        double rad = Math.toRadians((360 / AMOUNT * id) + angle);
        float x = (float) (center.x + distance * Math.cos(rad));
        float y = (float) (center.y + distance * Math.sin(rad));
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
