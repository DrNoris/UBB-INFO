namespace seminar11.Domain;

public class Sarcina : Entity<string>
{
    public Dificultate dificultate { get; set; }
    public int nrOreEstimate { get; set; }
    
    public override string ToString()
    {
        return $"{Id}, {dificultate}, {nrOreEstimate}";
    }
}