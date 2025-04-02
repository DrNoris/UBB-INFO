using System.Runtime.InteropServices.JavaScript;

namespace seminar10;

public class StackContainer : Container
{
    private List<Task> elements;
    
    public StackContainer()
    {
        elements = new List<Task>();
    }

    public Task remove()
    {
        Task e = elements.ElementAt(elements.Count - 1);
        elements.RemoveAt(elements.Count - 1);
        return e;
    }

    public void add(Task task)
    {
        elements.Add(task);
    }

    public int size()
    {
        return elements.Count;
    }

    public bool isEmpty()
    {
        return elements.Count == 0;
    }
}