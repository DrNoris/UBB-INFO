using seminar11.Repository;
using seminar11.Service;

public class Program
{
    public static void Main()
    {
        FileRepositoryAngajati angajatiRepo =
            new FileRepositoryAngajati("/Users/noris/RiderProjects/seminar11/seminar11/Data/angajati.txt");
        FileRepositorySarcina sarcinaRepo =
            new FileRepositorySarcina("/Users/noris/RiderProjects/seminar11/seminar11/Data/sarcina.txt");
        FileRepositoryPontaj pontajRepo =
            new FileRepositoryPontaj("/Users/noris/RiderProjects/seminar11/seminar11/Data/pontaj.txt", ref angajatiRepo, ref sarcinaRepo);

        ServiceAngajati serviceAngajati = new ServiceAngajati(angajatiRepo);
        ServiceSarcina serviceSarcina= new ServiceSarcina(sarcinaRepo);
        ServicePontaj servicePontaj = new ServicePontaj(pontajRepo);
        
        UI ui = new UI(serviceAngajati, serviceSarcina, servicePontaj);


        ui.DisplayMenu();
    }
}