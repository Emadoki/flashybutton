package emadoki.flashybutton.app.views;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;

import emadoki.flashybutton.app.animator.ExplodeAnimation;
import emadoki.flashybutton.app.object.ExplodeBall;

public class ExplodeView extends BaseView
{
    private ArrayList<ExplodeBall> list;

    public static final int AMOUNT = 10;
    public static float MAX_RADIUS;

    public ExplodeView(Context context)
    {
        super(context);

        list = new ArrayList<ExplodeBall>();
        for (int i = 0; i < AMOUNT; i++)
            list.add(new ExplodeBall(i));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        MAX_RADIUS = Math.min(w / 4, h / 4);

        for (ExplodeBall ball: list)
            ball.prepare(w, h);
    }

    @Override
    public void update(float progress)
    {
        for (ExplodeBall ball: list)
            ball.update(progress);
    }

    @Override
    public void render(Canvas canvas)
    {
        if (!ExplodeAnimation.PLAY)
            return;

        for (ExplodeBall ball: list)
            ball.render(canvas);
    }

    @Override
    public void setPrimaryColor(int color)
    {
        if (list == null)
            return;

        for (int i = 0; i < list.size(); i++)
            if (i % 2 == 0)
                list.get(i).setColor(color);
    }

    @Override
    public void setSecondaryColor(int color)
    {
        if (list == null)
            return;

        for (int i = 0; i < list.size(); i++)
            if (i % 2 != 0)
                list.get(i).setColor(color);
    }
}
