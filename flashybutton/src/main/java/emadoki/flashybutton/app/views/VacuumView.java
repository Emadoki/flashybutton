package emadoki.flashybutton.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import emadoki.flashybutton.app.Util;
import emadoki.flashybutton.app.Vector2D;
import emadoki.flashybutton.app.animator.VacuumAnimation;
import emadoki.flashybutton.app.object.VacuumBall;

public class VacuumView extends BaseView
{
    private ArrayList<VacuumBall> listPrimary;
    private ArrayList<VacuumBall> listSecondary;

    public static final int AMOUNT = 8;

    public static float MAX_DISTANCE;
    public static float MAX_RADIUS;

    public VacuumView(Context context)
    {
        super(context);

        listPrimary = new ArrayList<VacuumBall>();
        listSecondary = new ArrayList<VacuumBall>();

        for (int i = 0; i < AMOUNT; i++)
        {
            listPrimary.add(new VacuumBall(i));
            listSecondary.add(new VacuumBall(i, true));
        }

        MAX_RADIUS = Util.dimenToPx(context, 3);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        MAX_DISTANCE = Math.min(w, h);

        for (VacuumBall ball: listPrimary)
            ball.setCenter(w / 2, h / 2);

        for (VacuumBall ball: listSecondary)
            ball.setCenter(w / 2, h / 2);
    }

    @Override
    public void update(float progress)
    {
        for (VacuumBall ball: listPrimary)
            ball.update(progress);

        for (VacuumBall ball: listSecondary)
            ball.update(progress);
    }

    @Override
    public void render(Canvas canvas)
    {
        if (!VacuumAnimation.PLAY)
            return;

        for (VacuumBall ball: listPrimary)
            ball.render(canvas);

        for (VacuumBall ball: listSecondary)
            ball.render(canvas);
    }

    @Override
    public void setPrimaryColor(int color)
    {
        for (VacuumBall ball: listPrimary)
            ball.setColor(color);
    }

    @Override
    public void setSecondaryColor(int color)
    {
        for (VacuumBall ball: listSecondary)
            ball.setColor(color);
    }
}
