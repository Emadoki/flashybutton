package emadoki.flashybutton.app;

public class Vector2D
{
    public float x;
    public float y;

    public Vector2D()
    {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2D set(float x, float y)
    {
        this.x = x;
        this.y = y;

        return new Vector2D(x, y);
    }

    public Vector2D add(float value)
    {
        this.x += value;
        this.y += value;

        return new Vector2D(x, y);
    }

    public Vector2D add(float dx, float dy)
    {
        this.x += dx;
        this.y += dy;

        return new Vector2D(x, y);
    }

    public Vector2D substract(float value)
    {
        this.x -= value;
        this.y -= value;

        return new Vector2D(x, y);
    }

    public Vector2D substract(float dx, float dy)
    {
        this.x -= dx;
        this.y -= dy;

        return new Vector2D(x, y);
    }

    public Vector2D multiply(float value)
    {
        this.x *= value;
        this.y *= value;

        return new Vector2D(x, y);
    }

    public Vector2D multiply(float dx, float dy)
    {
        this.x *= dx;
        this.y *= dy;

        return new Vector2D(x, y);
    }
}
