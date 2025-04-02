using System.Collections.Immutable;
using seminar11.Domain;
using seminar11.Repository;

namespace seminar11.Service;

public class ServiceAngajati : IRepository<string, Angajat>
{
    private readonly FileRepositoryAngajati repository;
    
    public ServiceAngajati(FileRepositoryAngajati repository)
    {
        this.repository = repository;
    }

    public IEnumerable<Angajat> FindAll()
    {
        return repository.FindAll();
    }

    public Angajat FindOne(string key)
    {
        return repository.FindAll().FirstOrDefault(x => x.Id == key);
    }

    public IEnumerable<Angajat> FindAllOrderedMethod()
    {
        return repository.FindAll().OrderBy(angajat => angajat.nivel).ThenByDescending(angajat => angajat.venitPeOra);
    }

    public IEnumerable<Angajat> FindAllOrderedSQL()
    {
        return (from e in repository.FindAll()
            orderby e.nivel, e.venitPeOra descending
            select e).ToImmutableArray();
    }
}