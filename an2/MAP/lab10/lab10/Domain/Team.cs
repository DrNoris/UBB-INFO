namespace lab10.Domain;

public class Team : Entity<int>
{
    public string name { get; set; }

    public Team(int id, string name) : base(id)
    {
        this.name = name;
    }
}