using seminar11.Domain;

public interface IRepository<ID, T> where T : Entity<ID>
{
    IEnumerable<T> FindAll();
}