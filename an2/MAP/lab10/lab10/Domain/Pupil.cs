namespace lab10.Domain;

public class Pupil : Entity<int>
{
    public string name { get; set; }
    public string school { get; set; }
    
    public Pupil(int id, string name, string school) : base(id)
    {
        this.name = name;
        this.school = school;
    }
}