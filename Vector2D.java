package lab4;

public class Vector2D {
    public double x;
    public double y;

    //Konstruktor domyślny
    public Vector2D()
    {
        x = 0;
        y = 0;
    }

    //Konstruktor z parametrami
    public Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2D sumaWektorow(double x, double y)
    {
        return new Vector2D(this.x + x,this.y + y);
    }

    public Vector2D roznicaWektorow(double x, double y)
    {
        return new Vector2D(this.x - x,this.y - y);
    }

    public Vector2D mnozenieWektorow(double x, double y)
    {
        return new Vector2D(this.x * x,this.y * y);
    }

    public double dlugoscWektora()
    {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public Vector2D wektorZnormalizowany()
    {
        return new Vector2D(x / dlugoscWektora(), y / dlugoscWektora());
    }
}