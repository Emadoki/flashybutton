package emadoki.flashybutton.app.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import emadoki.flashybutton.app.views.CircleView;
import emadoki.flashybutton.app.views.FireworkView;

public class FireworkAnimation extends BaseAnimation
{
    private AnimatorSet animatorSet;

    private CircleView circleView;
    private FireworkView fireworkView;

    public static boolean PLAY = false;

    public FireworkAnimation(Context context, ViewGroup parent)
    {
        animatorSet = new AnimatorSet();
        circleView = new CircleView(context);
        fireworkView = new FireworkView(context);

        animatorSet.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                refresh();
            }
        });

        parent.addView(fireworkView);
        parent.addView(circleView);
    }

    @Override
    public void refresh()
    {
        this.PLAY = false;

        if (circleView != null)
        {
            circleView.setCircleProgress(0);
            circleView.setClearProgress(0);
        }
        if (fireworkView != null)
            fireworkView.setProgress(0);
    }

    @Override
    public void setPrimaryColor(int color)
    {
        if (circleView != null)
            circleView.setColor(color);

        if (fireworkView != null)
        {
            fireworkView.setPrimaryColor(color);
        }
    }

    @Override
    public void setSecondaryColor(int color)
    {
        if (fireworkView != null)
            fireworkView.setSecondaryColor(color);
    }

    @Override
    public void play()
    {
        cancel();

        this.PLAY = true;

        ObjectAnimator circleViewAnimator = ObjectAnimator.ofFloat(circleView, CircleView.CIRCLE_PROGRESS, 0f, 1f);
        circleViewAnimator.setDuration(300);
        circleViewAnimator.setInterpolator(new DecelerateInterpolator());

        ObjectAnimator clearAnimator = ObjectAnimator.ofFloat(circleView, CircleView.CLEAR_PROGRESS, 0f, 1f);
        clearAnimator.setDuration(300);
        clearAnimator.setStartDelay(300);
        clearAnimator.setInterpolator(new DecelerateInterpolator());

        ObjectAnimator fireworkAnimator = ObjectAnimator.ofFloat(fireworkView, FireworkView.PROGRESS, 0f, 1f);
        fireworkAnimator.setDuration(820);
        fireworkAnimator.setStartDelay(120);
        fireworkAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        animatorSet.playTogether(circleViewAnimator, clearAnimator, fireworkAnimator);
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
