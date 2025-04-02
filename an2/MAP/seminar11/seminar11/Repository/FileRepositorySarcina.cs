using seminar11.Domain;

namespace seminar11.Repository;

public class FileRepositorySarcina : InFileRepository<string, Sarcina>
{
    public FileRepositorySarcina(string filePath) : base(filePath)
    {
    }

    protected override Sarcina? ParseEntity(string line)
    {
        var parts = line.Split(',');
        if (parts.Length != 3)
            return null; 

        try
        {
            var id = parts[0];
            var dificultate = Enum.Parse<Dificultate>(parts[1]);
            var ore = int.Parse(parts[2]);

            return new Sarcina
            {
                Id = id,
                dificultate = dificultate,
                nrOreEstimate = ore
            };
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error parsing line: {line}. Exception: {ex.Message}");
            return null; // Return null if any parsing fails
        }
    }

    protected override string SerializeEntity(Sarcina entity)
    {
        return $"{entity.Id},{entity.dificultate},{entity.nrOreEstimate}";
    }
}