package emadoki.flashybutton.app;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class Util
{
    public static float dimenToPx(Context context, float dimen)
    {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimen, metrics);
    }

    /**
     * Get a attribute color
     * @param context application context
     * @param resID attribute color id
     * @return color
     */
    public static int getColor(Context context, int resID)
    {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(resID, value, true);
        return value.data;
    }

    public static AnimationType getAnimation(int position)
    {
        AnimationType type = AnimationType.FIREWORK;

        switch (position){
            case 0:
                type = AnimationType.FIREWORK;
                break;
            case 1:
                type = AnimationType.VACUUM;
                break;
            case 2:
                type = AnimationType.EXPLODE;
                break;
        }

        return type;
    }
}
