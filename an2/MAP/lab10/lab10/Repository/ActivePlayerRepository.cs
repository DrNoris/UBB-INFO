using lab10.Domain;
using Npgsql;

namespace lab10.Repository;

public class ActivePlayerRepository : IRepository<int, ActivePlayer>
{
    
    private readonly string connectionString = "Host=localhost;Port=5432;Database=nba;Username=postgres;Password=noris2580;";
    private PlayerRepository playerRepository;
    private GameRepository gameRepository;

    public ActivePlayerRepository(PlayerRepository playerRepository, GameRepository gameRepository)
    {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        CreateTable();
    }

    private void CreateTable()
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"CREATE TABLE IF NOT EXISTS ActivePlayer (
                                Id SERIAL PRIMARY KEY,
                                Player int,
                                Game int,
                                Score int,
                                Type bool,
                                CONSTRAINT FK_Game FOREIGN KEY (Game) references games (Id),
                                CONSTRAINT FK_Player FOREIGN KEY (Player) references pupils (Id)
                             )";
        using var command = new NpgsqlCommand(query, connection);

        connection.Open();
        command.ExecuteNonQuery();
        connection.Close();
    }
    
    private ActivePlayer Parsing(NpgsqlDataReader reader)
    {
        Player player = playerRepository.FindOne(reader.GetInt32(1));
        Game game = gameRepository.FindOne(reader.GetInt32(2));
        PlayerType type = PlayerType.Player;
        
        if (reader.GetBoolean(4) == false)
            type = PlayerType.Reserve;
        
        return new ActivePlayer(reader.GetInt32(0), player.Id, game.Id, type, reader.GetInt32(3));
    }
    
    public ActivePlayer FindOne(int id)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"SELECT * FROM ActivePlayer WHERE Id = @Id";
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
            Console.WriteLine(ex.Message);
        }

        return null;  
    }


    public IEnumerable<ActivePlayer> FindAll()
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"SELECT * FROM ActivePlayer";
        using var command = new NpgsqlCommand(query, connection);

        try
        {
            connection.Open();
            using var reader = command.ExecuteReader();

            var activePlayers = new List<ActivePlayer>();

            while (reader.Read())  
            {
                activePlayers.Add(Parsing(reader)); 
            }

            connection.Close();
            return activePlayers;
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
        }

        return new List<ActivePlayer>(); 
    }


    public ActivePlayer Save(ActivePlayer entity)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"INSERT INTO ActivePlayer (Player, Game, Score, Type) VALUES (@Player, @Game, @Score, @Type) RETURNING Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Player", entity.playerId);
        command.Parameters.AddWithValue("@Game", entity.gameId);
        command.Parameters.AddWithValue("@Score", entity.points);
        
        bool type = entity.type == PlayerType.Player;
        command.Parameters.AddWithValue("@Type", type);
        
        connection.Open();
        entity.Id = (int)command.ExecuteScalar();
        connection.Close();
        
        return entity;
    }

    public ActivePlayer Delete(ActivePlayer entity)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"DELETE FROM ActivePlayer WHERE Id = @Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Id", entity.Id);
        
        connection.Open();
        command.ExecuteNonQuery();
        connection.Close();
        return entity;
    }

    public ActivePlayer Update(ActivePlayer entity)
    {
        using var connection = new NpgsqlConnection(connectionString);
        const string query = @"UPDATE ActivePlayer SET Player = @Player, Game = @Game, Score = @Score, Type = @Type WHERE Id = @Id";
        using var command = new NpgsqlCommand(query, connection);
        command.Parameters.AddWithValue("@Id", entity.Id);
        command.Parameters.AddWithValue("@Player", entity.playerId);
        command.Parameters.AddWithValue("@Game", entity.gameId);
        command.Parameters.AddWithValue("@Score", entity.points);
        bool type = entity.type == PlayerType.Player;
        command.Parameters.AddWithValue("@Type", type);
        
        connection.Open();
        command.ExecuteNonQuery();
        connection.Close();
        
        return entity;
    }
}