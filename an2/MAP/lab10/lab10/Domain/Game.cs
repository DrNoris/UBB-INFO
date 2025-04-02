namespace lab10.Domain;

public class Game : Entity<int>
{
    public Team team1 { get; set; }
    public Team team2 { get; set; }
    public DateTime date { get; set; }
    
    public Game(int id, Team team1, Team team2, DateTime date) : base(id)
    {
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
    }
}