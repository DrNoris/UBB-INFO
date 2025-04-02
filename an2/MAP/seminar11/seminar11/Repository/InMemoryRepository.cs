using seminar11.Domain;

namespace seminar11.Repository;

public class InMemoryRepository<ID, T> : IRepository<ID, T> where T : Entity<ID>
{
    public Dictionary<ID, T> Items { get; set; } = new();
    
    public IEnumerable<T> FindAll()
    {
        return Items.Values;
    }
}