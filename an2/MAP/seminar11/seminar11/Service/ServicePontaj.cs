using seminar11.Domain;
using seminar11.Repository;

namespace seminar11.Service;

public class ServicePontaj: IRepository<string, Pontaj>
{
    private readonly FileRepositoryPontaj fileRepositoryPontaj;
    
    public ServicePontaj(FileRepositoryPontaj repository)
    {
        fileRepositoryPontaj = repository;
    }

    public IEnumerable<Pontaj> FindAll()
    {
        return fileRepositoryPontaj.FindAll();
    }
    
    public IEnumerable<string> Top2Method()
    {
        return fileRepositoryPontaj.FindAll()
            .GroupBy(pontaj => pontaj.angajat.Id)
            .Select(group => new
            {
                Angajat = group.Key, 
                TotalOreLucrate =
                    group.Sum(p =>
                        p.sarcina.nrOreEstimate * p.angajat.venitPeOra) 
            }).OrderByDescending(result => result.TotalOreLucrate)
            .Take(2).Select(result => result.Angajat) 
            .ToList();
    } 
    
    public IEnumerable<Angajat> Top2Sql()
    {
        return (from pontaj in fileRepositoryPontaj.FindAll()
            group pontaj by pontaj.angajat.Id into groupPontaj
            let totalOreLucrate = groupPontaj.Sum(p => p.sarcina.nrOreEstimate * p.angajat.venitPeOra)
            orderby totalOreLucrate descending
                select groupPontaj.First().angajat).Take(2).ToList();
    } 
}