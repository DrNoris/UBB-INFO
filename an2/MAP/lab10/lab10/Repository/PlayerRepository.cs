using lab10.Domain;
using Npgsql;

namespace lab10.Repository;

public class PlayerRepository : IRepository<int, Player>
{
    private readonly string connectionString = "Host=localhost;Port=5432;Database=nba;Username=postgres;Password=noris2580;";
    private TeamRepository teamRepository;

    public PlayerRepository(TeamRepository teamRepository)
    {
        this.teamRepository = teamRepository;
    }
    
    private Player Parsing(NpgsqlDataReader reader)
    {
        return new Player(reader.GetInt32(0), 
            reader.GetString(1), 
            reader.GetString(2), 
            teamRepository.FindOne(reader.GetInt32(3)));
    }
    
    public Player FindOne(int id)
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


    public IEnumerable<Player> FindAll()
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"SELECT * FROM Pupils";
        using var command = new NpgsqlCommand(query, connection);
        
        try
        {
            connection.Open();
            using var reader = command.ExecuteReader();

            var players = new List<Player>();
            while (reader.Read())
            {
                players.Add(Parsing(reader));
            }

            return players;
        }
        catch (NpgsqlException ex)
        {
            Console.WriteLine(ex.Message);
        }
        
        return new List<Player>();
    }


    public Player Save(Player entity)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"INSERT INTO Pupils (Name, School, Team) VALUES (@Name, @School, @Team) RETURNING Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Name", entity.name);
        command.Parameters.AddWithValue("@School", entity.school);
        command.Parameters.AddWithValue("@Team", entity.team);
        
        connection.Open();
        entity.Id = (int)command.ExecuteScalar();
        connection.Close();
        
        return entity;
    }

    public Player Delete(Player entity)
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
    
    public Player Update(Player entity)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"UPDATE Pupils SET Name = @Name, School = @School, Team = @Team WHERE Id = @Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Id", entity.Id);
        command.Parameters.AddWithValue("@Name", entity.name);
        command.Parameters.AddWithValue("@School", entity.school);
        command.Parameters.AddWithValue("@Team", entity.team);
        
        connection.Open();
        command.ExecuteNonQuery();
        connection.Close();
        return entity;
    }
}