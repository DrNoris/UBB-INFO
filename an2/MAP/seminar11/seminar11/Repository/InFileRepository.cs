using seminar11.Domain;
using seminar11.Repository;

public abstract class InFileRepository<ID, E> : InMemoryRepository<ID, E> where E : Entity<ID>
{
    protected string FilePath { get; }

    protected InFileRepository(string filePath)
    {
        FilePath = filePath;
        LoadFromFile();
    }

    protected void LoadFromFile()
    {
        if (!File.Exists(FilePath))
            return;

        foreach (var line in File.ReadLines(FilePath))
        {
            var entity = ParseEntity(line);
            if (entity != null)
            {
                Items[entity.Id] = entity;
            }
        }
    }

    public void SaveToFile()
    {
        using var writer = new StreamWriter(FilePath);
        foreach (var entity in Items.Values)
        {
            writer.WriteLine(SerializeEntity(entity));
        }
    }

    // Abstract methods to be implemented in derived classes
    protected abstract E? ParseEntity(string line);
    protected abstract string SerializeEntity(E entity);
}