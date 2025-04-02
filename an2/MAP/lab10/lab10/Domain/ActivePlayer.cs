namespace lab10.Domain;

public class ActivePlayer : Entity<int>
{
    public int playerId { get; set; }
    public int gameId { get; set; }
    public int points { get; set; }
    public PlayerType type { get; set; }

    public ActivePlayer(int id, int playerId, int gameId, PlayerType type, int points) : base(id)
    {
        this.playerId = playerId;
        this.gameId = gameId;
        this.type = type;
        this.points = points;
    }
}