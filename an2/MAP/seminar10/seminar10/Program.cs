using seminar10;
using System.Collections.Generic;
using Task = seminar10.Task;


public class Program
{
    public static void Main(string[] args)
    {
        List<Square> squares = new List<Square>();
        squares.Add(new Square(11));
        squares.Add(new Square(4));
        squares.Add(new Square(7));
        squares.Add(new Square(3));
        
        Console.WriteLine("\nPatrate");
        squares.ForEach(square => Console.WriteLine(square.computeArea()));
        
        
        List<Circle> circles = new List<Circle>();
        circles.Add(new Circle(5));
        circles.Add(new Circle(2));
        circles.Add(new Circle(1));
        circles.Add(new Circle(3));
        
        Console.WriteLine("\nCercuri");
        circles.ForEach(circle => Console.WriteLine(circle.computeArea()));
    
        
        Console.WriteLine("\nCercuri filtrate");
        circles.ForEach(circle =>
        {
            if (circle.radius < 4) Console.WriteLine(circle.computeArea());
        });

        
        StackContainer stack = new StackContainer();
        stack.add(new Task("1", "task1"));
        stack.add(new Task("2", "task2"));
        stack.add(new Task("3", "task3"));
        stack.add(new Task("4", "task4"));
        while (!stack.isEmpty())
        {
            Task t = stack.remove();
            Console.WriteLine(t.descriere);
        }
        
        
    }
}





