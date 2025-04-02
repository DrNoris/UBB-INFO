using lab10.Domain;
using Npgsql;

namespace lab10.Repository;

public class GameRepository : IRepository<int, Game>
{
    private readonly string connectionString = "Host=localhost;Port=5432;Database=nba;Username=postgres;Password=noris2580;";
    private TeamRepository teamRepository;

    public GameRepository(TeamRepository teamRepository)
    {
        this.teamRepository = teamRepository;
        CreateTable();
    }

    private void CreateTable()
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"CREATE TABLE IF NOT EXISTS Games (
                                Id SERIAL PRIMARY KEY,
                                Team1 int,
                                Team2 int,
                                Date DATE,
                                CONSTRAINT FK_Team1 FOREIGN KEY (Team1) REFERENCES Teams (Id),
                                CONSTRAINT FK_Team2 FOREIGN KEY (Team2) REFERENCES Teams (Id)
                             )";
        using var command = new NpgsqlCommand(query, connection);

        connection.Open();
        command.ExecuteNonQuery();
        connection.Close();
    }
    
    private Game Parsing(NpgsqlDataReader reader)
    {
        return new Game(reader.GetInt32(0), 
            teamRepository.FindOne(reader.GetInt32(1)), 
            teamRepository.FindOne(reader.GetInt32(2)), 
            reader.GetDateTime(reader.GetOrdinal("Date")));
    }

    public Game FindOne(int id)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"SELECT * FROM Games WHERE Id = @Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Id", id);

        try
        {
            connection.Open();
            using var reader = command.ExecuteReader();
            if (reader.Read())
                return Parsing(reader);
        }
        catch (NpgsqlException ex)
        {
            Console.WriteLine(ex.Message);
        }
        return null;
    }

    public IEnumerable<Game> FindAll()
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"SELECT * FROM Games";
        using var command = new NpgsqlCommand(query, connection);

        try
        {
            connection.Open();
            using var reader = command.ExecuteReader();

            var games = new List<Game>();
            while (reader.Read())
            {
                games.Add(Parsing(reader));
            }

            return games;
        }
        catch (NpgsqlException ex)
        {
            Console.WriteLine(ex.Message);
        }
        return new List<Game>();
    }

    public Game Save(Game entity)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"INSERT INTO Games (Team1, Team2, Date) VALUES (@Team1, @Team2, @Date) RETURNING Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Team1", entity.team1.Id);
        command.Parameters.AddWithValue("@Team2", entity.team2.Id);
        command.Parameters.AddWithValue("@Date", entity.date);
        
        connection.Open();
        entity.Id = (int)command.ExecuteScalar();
        connection.Close();
        
        return entity;
    }

    public Game Delete(Game entity)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"DELETE FROM Games WHERE Id = @Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Id", entity.Id);
        
        connection.Open();
        command.ExecuteNonQuery();
        connection.Close();
        return entity;
    }

    public Game Update(Game entity)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"UPDATE Games SET Team1 = @Team1, Team2 = @Team2, Date = @Date WHERE Id = @Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Id", entity.Id);
        command.Parameters.AddWithValue("@Team1", entity.team1.Id);
        command.Parameters.AddWithValue("@Team2", entity.team2.Id);
        command.Parameters.AddWithValue("@Date", entity.date);
        
        connection.Open();
        command.ExecuteNonQuery();
        connection.Close();
        
        return entity;
    }
}