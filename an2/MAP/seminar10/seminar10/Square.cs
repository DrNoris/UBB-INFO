namespace seminar10;

public class Square : Shape
{
    private double side;

    public Square(double side)
    {
        this.side = side;
    }

    public double computeArea()
    {
        return side * side;
    }
}