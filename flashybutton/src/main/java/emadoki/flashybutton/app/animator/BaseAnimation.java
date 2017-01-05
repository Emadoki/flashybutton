package emadoki.flashybutton.app.animator;

public abstract class BaseAnimation
{
    public abstract void refresh();
    public abstract void setPrimaryColor(int color);
    public abstract void setSecondaryColor(int color);
    public abstract void play();
    public abstract void cancel();
}
