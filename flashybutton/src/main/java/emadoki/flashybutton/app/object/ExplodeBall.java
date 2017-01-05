package emadoki.flashybutton.app.object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

import emadoki.flashybutton.app.Vector2D;
import static emadoki.flashybutton.app.views.ExplodeView.*;

public class ExplodeBall extends BaseEntity
{
    private Random random;
    private Paint paint;
    private Vector2D position;

    private int id;
    private int alpha;
    private float radius;
    private float delay;

    public ExplodeBall(int id)
    {
        this.id = id;

        random = new Random();
        paint = new Paint();
        paint.setColor(Color.TRANSPARENT);

        position = new Vector2D();
    }

    public void prepare(int w, int h)
    {
        delay = 0.3f / AMOUNT * id;

        float x = w / 2 + (random.nextInt(w) - (w / 2));
        float y = w / 2 + (random.nextInt(h) - (h / 2));
        position.set(x, y);
    }

    @Override
    public void update(float progress)
    {
        if (progress == 0)
        {
            radius = 0;
            alpha = 255;
        }

        if (progress > delay)
        {
            float dt = (progress - delay) / 0.7f;
            if (dt < 1)
            {
                radius = MAX_RADIUS * dt;
                if (dt > 0.7)
                    alpha = (int) (255 - (255 * dt));
            }

            paint.setAlpha(alpha);
        }
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
}
