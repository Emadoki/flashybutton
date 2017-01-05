package emadoki.flashybutton.app.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.Property;
import android.view.View;

import emadoki.flashybutton.app.Vector2D;

public class CircleView extends View
{
    private Canvas canvas;
    private Bitmap bitmap;
    private Vector2D center;

    private Paint paintCircle;
    private Paint paintClear;

    private float circleProgress;
    private float clearProgress;
    private float maxRadius;

    public CircleView(Context context)
    {
        super(context);

        center = new Vector2D();
        paintCircle = new Paint();
        paintCircle.setColor(Color.TRANSPARENT);

        paintClear = new Paint();
        paintClear.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        center.set(w / 2, h / 2);
        maxRadius = Math.min(w * 0.4f, h * 0.4f);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas c)
    {
        super.onDraw(c);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);
        canvas.drawCircle(center.x, center.y, maxRadius * circleProgress, paintCircle);
        canvas.drawCircle(center.x, center.y, maxRadius * clearProgress, paintClear);
        c.drawBitmap(bitmap, 0, 0, null);
    }

    public void setColor(int color)
    {
        this.paintCircle.setColor(color);
    }

    public void setCircleProgress(float progress)
    {
        this.circleProgress = progress;
        postInvalidate();
    }

    public void setClearProgress(float progress)
    {
        this.clearProgress = progress;
        postInvalidate();
    }

    public float getCircleProgress()
    {
        return circleProgress;
    }

    public float getClearProgress()
    {
        return clearProgress;
    }

    public static final Property<CircleView, Float> CIRCLE_PROGRESS = new
            Property<CircleView, Float>(Float.class, "circleProgress")
    {
        @Override
        public Float get(CircleView object)
        {
            return object.getCircleProgress();
        }

        @Override
        public void set(CircleView object, Float value)
        {
            object.setCircleProgress(value);
        }
    };

    public static final Property<CircleView, Float> CLEAR_PROGRESS =
            new Property<CircleView, Float>(Float.class, "clearProgress")
    {
        @Override
        public Float get(CircleView object)
        {
            return object.getClearProgress();
        }

        @Override
        public void set(CircleView object, Float value)
        {
            object.setClearProgress(value);
        }
    };
}
