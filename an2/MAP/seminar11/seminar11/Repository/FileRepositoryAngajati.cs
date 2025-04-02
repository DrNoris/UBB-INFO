using seminar11.Domain;

namespace seminar11.Repository;

public class FileRepositoryAngajati : InFileRepository<string, Angajat>
{
    public FileRepositoryAngajati(string filePath) : base(filePath)
    {
    }

    protected override Angajat? ParseEntity(string line)
    {
        var parts = line.Split(',');
        if (parts.Length != 4)
            return null; 

        try
        {
            var id = parts[0];
            var nume = parts[1];
            var venitPeOra = double.Parse(parts[2]);
            var nivel = Enum.Parse<Nivel>(parts[3]);

            return new Angajat
            {
                Id = id,
                nume = nume,
                venitPeOra = venitPeOra,
                nivel = nivel
            };
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error parsing line: {line}. Exception: {ex.Message}");
            return null; // Return null if any parsing fails
        }
    }

    protected override string SerializeEntity(Angajat entity)
    {
        return $"{entity.Id},{entity.nume},{entity.venitPeOra},{entity.nivel}";
    }
}
