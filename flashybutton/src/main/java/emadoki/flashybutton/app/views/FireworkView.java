package emadoki.flashybutton.app.views;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;

import emadoki.flashybutton.app.Util;
import emadoki.flashybutton.app.animator.FireworkAnimation;
import emadoki.flashybutton.app.object.FireworkBall;

public class FireworkView extends BaseView
{
    private ArrayList<FireworkBall> listPrimary;
    private ArrayList<FireworkBall> listSecondary;

    public static final int AMOUNT = 6;
    public static float MAX_DISTANCE;
    public static float MAX_RADIUS;

    public FireworkView(Context context)
    {
        super(context);

        listPrimary = new ArrayList<FireworkBall>();
        listSecondary = new ArrayList<FireworkBall>();

        for (int i = 0; i < AMOUNT; i++)
        {
            listPrimary.add(new FireworkBall(i));
            listSecondary.add(new FireworkBall(i, true));
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        MAX_DISTANCE = Math.min(w * 0.9f, h * 0.9f);
        MAX_RADIUS = Util.dimenToPx(getContext(), 3.2f);

        for (FireworkBall point: listPrimary)
            point.setCenter(w / 2 , h / 2);

        for (FireworkBall point: listSecondary)
            point.setCenter(w / 2 , h / 2);
    }

    @Override
    public void update(float progress)
    {
        for (FireworkBall point: listPrimary)
        {
            point.update(progress);
        }

        for (FireworkBall point: listSecondary)
        {
            point.update(progress);
        }
    }

    @Override
    public void render(Canvas canvas)
    {
        if (!FireworkAnimation.PLAY)
            return;

        if (progress < 0.3)
            return;

        for (FireworkBall point: listPrimary)
            point.render(canvas);

        for (FireworkBall point: listSecondary)
            point.render(canvas);
    }

    /**
     * Color for big dots
     * @param color
     */
    @Override
    public void setPrimaryColor(int color)
    {
        for (FireworkBall point: listPrimary)
            point.setColor(color);
    }

    /**
     * Color for small dots
     * @param color
     */
    @Override
    public void setSecondaryColor(int color)
    {
        for (FireworkBall point: listSecondary)
            point.setColor(color);
    }
}
