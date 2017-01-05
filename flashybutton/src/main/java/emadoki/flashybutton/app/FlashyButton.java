package emadoki.flashybutton.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import emadoki.flashybutton.app.animator.BaseAnimation;
import emadoki.flashybutton.app.animator.FireworkAnimation;
import emadoki.flashybutton.app.animator.ExplodeAnimation;
import emadoki.flashybutton.app.animator.VacuumAnimation;

public class FlashyButton extends RelativeLayout
{
    private final int DEFAULT_ICON_SIZE = 36;

    private ImageView image;
    private BaseAnimation animation;
    private AnimationType type;
    private Drawable iconOn;
    private Drawable iconOff;
    private float iconSize;
    private int primaryColor;
    private int secondaryColor;

    private boolean isChecked;

    public FlashyButton(Context context)
    {
        super(context);
    }

    public FlashyButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        parseAttributes(attrs);
        build();
    }

    public FlashyButton(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        parseAttributes(attrs);
        build();
    }

    /**
     * Initialize
     */
    private void build()
    {
        RelativeLayout.LayoutParams params =
                new LayoutParams((int) iconSize, (int) iconSize);
        params.addRule(CENTER_IN_PARENT);
        image = new ImageView(getContext());
        image.setLayoutParams(params);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        image.setImageDrawable(isChecked ? iconOn : iconOff);

        switch (type){
            case FIREWORK:
                animation = new FireworkAnimation(getContext(), this);
                break;
            case VACUUM:
                animation = new VacuumAnimation(getContext(), this);
                break;
            case EXPLODE:
                animation = new ExplodeAnimation(getContext(), this);
                break;
        }

        animation.setPrimaryColor(primaryColor);
        animation.setSecondaryColor(secondaryColor);
        // remove clipping
        setClipToPadding(false);
        setClipChildren(false);
        // add children view
        addView(image);
    }

    /**
     * Read Attributes and set to variables
     * @param attrs attrs.xml
     */
    private void parseAttributes(AttributeSet attrs)
    {
        TypedArray array = getContext().getTheme()
                .obtainStyledAttributes(attrs, R.styleable.FlashyButton, 0, 0);

        try
        {
            iconOn = array.getDrawable(R.styleable.FlashyButton_iconOn);
            iconOff = array.getDrawable(R.styleable.FlashyButton_iconOff);
            iconSize = array.getDimension(R.styleable.FlashyButton_iconSize, Util.dimenToPx(getContext(), DEFAULT_ICON_SIZE));
            type = Util.getAnimation(array.getInt(R.styleable.FlashyButton_animation, 0));
            primaryColor = array.getInt(R.styleable.FlashyButton_primaryColor, Util.getColor(getContext(), R.attr.colorPrimary));
            secondaryColor = array.getInt(R.styleable.FlashyButton_secondaryColor, Util.getColor(getContext(), R.attr.colorAccent));
            isChecked = array.getBoolean(R.styleable.FlashyButton_checked, false);
        }
        finally
        {
            array.recycle();
        }
    }

    /**
     * Different type animate icon differently
     */
    private void AnimateIcon()
    {
        switch (type){
            case FIREWORK:
                image.setScaleX(0f);
                image.setScaleY(0f);
                // popup effect
                image.animate().scaleX(1).scaleY(1).setDuration(400).setStartDelay(300)
                        .setInterpolator(new OvershootInterpolator()).start();
                break;
            case VACUUM:
                image.setScaleX(0);
                image.setScaleY(0);
                image.setRotation(0);
                // popup effect
                image.animate().rotation(360).scaleX(1).scaleY(1).setDuration(600)
                        .setStartDelay(150).setInterpolator(null).start();
                break;
            case EXPLODE:
                image.setScaleX(0);
                image.setScaleY(0);
                // popup effect
                image.animate().scaleX(1).scaleY(1).setDuration(600).setStartDelay(150)
                        .setInterpolator(new BounceInterpolator()).start();
                break;
        }
    }

    public void play()
    {
        if (!isChecked)
        {
            // play animation
            animation.cancel();
            animation.play();

            image.animate().cancel();
            AnimateIcon();

            image.setImageDrawable(iconOn);
            isChecked = true;
        }
        else
        {
            animation.cancel();

            image.animate().cancel();
            image.setScaleX(0.5f);
            image.setScaleY(0.5f);
            // popup effect
            image.animate().scaleX(1).scaleY(1).setDuration(350).setStartDelay(50)
                    .setInterpolator(new OvershootInterpolator()).start();
            image.setImageDrawable(iconOff);
            isChecked = false;
        }
    }
}
