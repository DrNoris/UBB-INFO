namespace lab10.Domain;

public class Player : Pupil
{
    public Team team { get; set; }
    
    public Player(int id, string name, string school, Team team) : base(id, name, school)
    {
        this.team = team;
    }
}