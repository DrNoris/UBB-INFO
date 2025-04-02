using seminar11.Domain;

namespace seminar11.Repository;

public class FileRepositoryPontaj : InFileRepository<string, Pontaj>
{
    private readonly FileRepositoryAngajati _angajatiRepository;
    private readonly FileRepositorySarcina _sarcinaRepository;
    
    public FileRepositoryPontaj(string filePath, ref FileRepositoryAngajati angajatiRepository, ref FileRepositorySarcina sarcinaRepository) : base(filePath)
    {
        _angajatiRepository = angajatiRepository;
        _sarcinaRepository = sarcinaRepository;
        LoadFromFile();
    }

    protected override Pontaj? ParseEntity(string line)
    {
        var parts = line.Split(',');
        if (parts.Length != 3)
            return null; 

        try
        {
            var idAngajat = parts[0];
            Angajat angajat = _angajatiRepository.FindAll().FirstOrDefault(x => x.Id == idAngajat);
            
            var idSarcina = parts[1];
            Sarcina sarcina = _sarcinaRepository.FindAll().FirstOrDefault(x => x.Id == idSarcina);
            
            DateTime date = DateTime.Parse(parts[2]);
            
            return new Pontaj()
            {
                Id = idAngajat + idSarcina,
                angajat = angajat,
                sarcina = sarcina,
                date = date
            };
            
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error parsing line: {line}. Exception: {ex.Message}");
            return null; // Return null if any parsing fails
        }
    }

    protected override string SerializeEntity(Pontaj entity)
    {
        return $"{entity.Id},{entity.angajat},{entity.sarcina},{entity.date}";
    }
}