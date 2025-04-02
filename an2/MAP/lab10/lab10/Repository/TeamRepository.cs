using lab10.Domain;
using Npgsql;
namespace lab10.Repository;

public class TeamRepository : IRepository<int, Team>
{
    private readonly string connectionString = "Host=localhost;Port=5432;Database=nba;Username=postgres;Password=noris2580;";
    
    public TeamRepository()
    {
        CreateTable();
    }

    private void CreateTable()
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"CREATE TABLE IF NOT EXISTS Teams (
                                Id SERIAL PRIMARY KEY,
                                Name VARCHAR(100) NOT NULL
                             )";
        using var command = new NpgsqlCommand(query, connection);

        connection.Open();
        command.ExecuteNonQuery();
        connection.Close();
    }

    private Team Parsing(NpgsqlDataReader reader)
    {
            return new Team(
                reader.GetInt32(0),
                reader.GetString(1));
    }
    public Team FindOne(int id)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"SELECT * FROM Teams WHERE Id = @Id";
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
        catch (Exception ex)
        {
            Console.WriteLine("Error: " + ex.Message);
        }

        return null;
    }



    public IEnumerable<Team> FindAll()
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"SELECT * FROM Teams";
        using var command = new NpgsqlCommand(query, connection);
    
        try
        {
            connection.Open();
            using var reader = command.ExecuteReader();
            var teams = new List<Team>();

            while (reader.Read()) 
            {
                teams.Add(Parsing(reader)); 
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine("Error: " + ex.Message);
        }

        return new List<Team>(); 
    }


    public Team Save(Team entity)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"INSERT INTO Teams (Name) VALUES (@Name) RETURNING Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Name", entity.name);
        
        connection.Open();
        entity.Id = (int)command.ExecuteScalar();
        connection.Close();
        
        return entity;
    }

    public Team Delete(Team entity)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"DELETE FROM Teams WHERE Id = @Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Id", entity.Id);
        
        connection.Open();
        command.ExecuteNonQuery();
        connection.Close();
        return entity;
    }

    public Team Update(Team entity)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"UPDATE Teams SET Name = @Name WHERE Id = @Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Id", entity.Id);
        command.Parameters.AddWithValue("@Name", entity.name);
        
        connection.Open();
        command.ExecuteNonQuery();
        connection.Close();
        return entity;
    }
}