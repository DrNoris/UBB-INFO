using seminar11.Domain;
using seminar11.Repository;

namespace seminar11.Service;

public class ServiceSarcina: IRepository<string, Sarcina>
{
    private readonly FileRepositorySarcina fileRepositorySarcina;
    
    public ServiceSarcina(FileRepositorySarcina repository)
    {
        fileRepositorySarcina = repository;
    }

    public IEnumerable<Sarcina> FindAll()
    {
        return fileRepositorySarcina.FindAll();
    }

    public List<Tuple<Dificultate, double>> CalculateAverageMethod()
    {
         return fileRepositorySarcina.FindAll()
             .GroupBy(Sarcina => Sarcina.dificultate)
             .Select(group => new Tuple<Dificultate, double>
                 (group.Key, group.Average(s => s.nrOreEstimate))).ToList();
    }
        
    public List<Tuple<Dificultate, double>> CalculateAverageSQL()
    {
        return (from sarcina in fileRepositorySarcina.FindAll()
            group sarcina by sarcina.dificultate into g
                select new Tuple<Dificultate, double>
                    (g.Key, g.Average(s => s.nrOreEstimate))).ToList();
    }
    
}