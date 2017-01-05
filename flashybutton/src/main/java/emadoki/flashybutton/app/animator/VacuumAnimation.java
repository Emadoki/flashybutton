package emadoki.flashybutton.app.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import emadoki.flashybutton.app.views.CircleView;
import emadoki.flashybutton.app.views.VacuumView;

public class VacuumAnimation extends BaseAnimation
{
    private AnimatorSet animatorSet;
    private CircleView circleView;
    private VacuumView vacuumView;

    public static boolean PLAY = false;

    public VacuumAnimation(Context context, ViewGroup parent)
    {
        animatorSet = new AnimatorSet();
        circleView = new CircleView(context);
        vacuumView = new VacuumView(context);

        animatorSet.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                refresh();
            }
        });

        parent.addView(vacuumView);
        parent.addView(circleView);
    }

    @Override
    public void refresh()
    {
        PLAY = false;
        if (circleView != null)
        {
            circleView.setCircleProgress(0);
            circleView.setClearProgress(0);
        }
        if (vacuumView != null)
            vacuumView.setProgress(0);
    }

    @Override
    public void setPrimaryColor(int color)
    {
        if (circleView != null)
            circleView.setColor(color);

        if (vacuumView != null)
            vacuumView.setPrimaryColor(color);
    }

    @Override
    public void setSecondaryColor(int color)
    {
        if (vacuumView != null)
            vacuumView.setSecondaryColor(color);
    }

    @Override
    public void play()
    {
        cancel();

        PLAY = true;

        ObjectAnimator vacuumViewAnimator = ObjectAnimator.ofFloat(vacuumView, VacuumView.PROGRESS, 0f, 1f);
        vacuumViewAnimator.setDuration(800);
        vacuumViewAnimator.setInterpolator(new DecelerateInterpolator());

        ObjectAnimator circleAnimator = ObjectAnimator.ofFloat(circleView, CircleView.CIRCLE_PROGRESS, 0f, 1f);
        circleAnimator.setDuration(300);
        circleAnimator.setStartDelay(500);
        circleAnimator.setInterpolator(new DecelerateInterpolator());

        final ObjectAnimator clearAnimator = ObjectAnimator.ofFloat(circleView, CircleView.CLEAR_PROGRESS, 0f, 1f);
        clearAnimator.setDuration(300);
        clearAnimator.setStartDelay(750);
        clearAnimator.setInterpolator(new DecelerateInterpolator());

        animatorSet.playTogether(vacuumViewAnimator, circleAnimator, clearAnimator);
        animatorSet.start();
    }

    @Override
    public void cancel()
    {
        if (animatorSet != null)
            animatorSet.cancel();

        refresh();
    }
}
