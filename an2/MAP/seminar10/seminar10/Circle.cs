namespace seminar10;

public class Circle : Shape
{
    public double radius { get; set; }

    public Circle(double radius)
    {
        this.radius = radius; // Setter with validation
    }

    public double computeArea()
    {
        return Double.Pi * radius * radius;
    }
}