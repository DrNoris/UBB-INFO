using lab10.Domain;
using Npgsql;
namespace lab10.Repository;

public class PupilRepository : IRepository<int, Pupil>
{
    private readonly string connectionString = "Host=localhost;Port=5432;Database=nba;Username=postgres;Password=noris2580;";
    
    public PupilRepository()
    {
        CreateTable();
    }

    private void CreateTable()
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"CREATE TABLE IF NOT EXISTS Pupils (
                                Id SERIAL PRIMARY KEY,
                                Name VARCHAR(100) NOT NULL,
                                School VARCHAR(100) NOT NULL,
                                Team int,
                                CONSTRAINT FK_Pupils_Teams FOREIGN KEY (Team) REFERENCES Teams (Id)
                             )";
        using var command = new NpgsqlCommand(query, connection);

        connection.Open();
        command.ExecuteNonQuery();
        connection.Close();
    }

    private Pupil Parsing(NpgsqlDataReader reader)
    {
        return new Pupil(reader.GetInt32(0), 
            reader.GetString(1), 
            reader.GetString(2));
    }
    
    public Pupil FindOne(int id)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"SELECT * FROM Pupils WHERE Id = @Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Id", id);

        try
        {
            connection.Open();
            using var reader = command.ExecuteReader();

            if (reader.Read())
            {
                return Parsing(reader);
            }
        }
        catch (NpgsqlException ex)
        {
            Console.WriteLine(ex.Message);
        }
        return null;
    }

    public IEnumerable<Pupil> FindAll()
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"SELECT * FROM Pupils";
        using var command = new NpgsqlCommand(query, connection);

        try
        {
            connection.Open();
            using var reader = command.ExecuteReader();

            var pupils = new List<Pupil>();
            while (reader.Read())
            {
                pupils.Add(Parsing(reader));
            }

            return pupils;
        }
        catch (NpgsqlException ex)
        {
            Console.WriteLine(ex.Message);
        }

        return new List<Pupil>();
    }

    public Pupil Save(Pupil entity)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"INSERT INTO Pupils (Name, School) VALUES (@Name, @School) RETURNING Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Name", entity.name);
        command.Parameters.AddWithValue("@School", entity.school);
        
        connection.Open();
        entity.Id = (int)command.ExecuteScalar();
        connection.Close();
        
        return entity;
    }

    public Pupil Delete(Pupil entity)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"DELETE FROM Pupils WHERE Id = @Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Id", entity.Id);
        
        connection.Open();
        command.ExecuteNonQuery();
        connection.Close();
        return entity;
    }

    public Pupil Update(Pupil entity)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"UPDATE Pupils SET Name = @Name, School = @School WHERE Id = @Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Id", entity.Id);
        command.Parameters.AddWithValue("@Name", entity.name);
        command.Parameters.AddWithValue("@School", entity.school);
        
        connection.Open();
        command.ExecuteNonQuery();
        connection.Close();
        return entity;
    }
}