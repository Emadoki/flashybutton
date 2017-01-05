package emadoki.flashybutton.app.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.ViewGroup;

import emadoki.flashybutton.app.views.ExplodeView;

public class ExplodeAnimation extends BaseAnimation
{
    private AnimatorSet animatorSet;

    private ExplodeView explodeView;

    public static boolean PLAY = false;

    public ExplodeAnimation(Context context, ViewGroup parent)
    {
        animatorSet = new AnimatorSet();

        explodeView = new ExplodeView(context);

        animatorSet.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                refresh();
            }
        });

        parent.addView(explodeView);
    }

    @Override
    public void refresh()
    {
        this.PLAY = false;

        if (explodeView != null)
            explodeView.setProgress(0);
    }

    @Override
    public void setPrimaryColor(int color)
    {
        if (explodeView != null)
            explodeView.setPrimaryColor(color);
    }

    @Override
    public void setSecondaryColor(int color)
    {
        if (explodeView != null)
            explodeView.setSecondaryColor(color);
    }

    @Override
    public void play()
    {
        cancel();

        this.PLAY = true;

        ObjectAnimator animator = ObjectAnimator.ofFloat(explodeView, ExplodeView.PROGRESS, 0f, 1f);
        animator.setDuration(1200);

        animatorSet.play(animator);
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
