package emadoki.flashybutton.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Property;
import android.view.View;

public abstract class BaseView extends View
{
    protected float progress;

    public BaseView(Context context)
    {
        super(context);
    }

    public abstract void update(float progress);
    public abstract void render(Canvas canvas);
    public abstract void setPrimaryColor(int color);
    public abstract void setSecondaryColor(int color);

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        render(canvas);
    }

    /**
     * For animator to call
     * @param progress 0 ~ 1
     */
    public void setProgress(float progress)
    {
        this.progress = progress;
        update(progress);
        postInvalidate();
    }

    public float getProgress()
    {
        return progress;
    }

    public static final Property<BaseView, Float> PROGRESS = new Property<BaseView, Float>(Float.class, "progress")
    {
        @Override
        public Float get(BaseView object)
        {
            return object.getProgress();
        }

        @Override
        public void set(BaseView object, Float value)
        {
            object.setProgress(value);
        }
    };
}
