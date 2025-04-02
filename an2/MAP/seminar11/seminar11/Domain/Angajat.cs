namespace seminar11.Domain;

public class Angajat : Entity<string>
{
    public string nume { get; set; }
    public double venitPeOra { get; set; }
    public Nivel nivel { get; set; }

    public override string ToString()
    {
        return $"{Id}, {nume}, {venitPeOra}, {nivel}";
    }
}