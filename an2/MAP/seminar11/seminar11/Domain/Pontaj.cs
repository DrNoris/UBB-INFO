namespace seminar11.Domain;

public class Pontaj : Entity<string>
{
    public Angajat angajat {get;set;}
    public Sarcina sarcina {get;set;}
    public DateTime date { get; set; }
    
    public override string ToString()
    {
        return $"{angajat}, {sarcina}, {date}";
    }
}