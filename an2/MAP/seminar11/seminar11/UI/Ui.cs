using System;
using seminar11.Service;

public class UI
{
    private ServiceAngajati _angajatService;
    private ServiceSarcina _sarcinaService;
    private ServicePontaj _pontajService;

    public UI(ServiceAngajati angajatService, ServiceSarcina sarcinaService, ServicePontaj pontajService)
    {
        _angajatService = angajatService;
        _sarcinaService = sarcinaService;
        _pontajService = pontajService;
    }

    public void DisplayMenu()
    {
        while (true)
        {
            Console.Clear();
            Console.WriteLine("===== Main Menu =====");
            Console.WriteLine("1. Show all Angajati");
            Console.WriteLine("2. Show all Sarcini");
            Console.WriteLine("3. Show all Pontaje");
            Console.WriteLine("4. Show all Angajati (Ordered)");
            Console.WriteLine("5. Average task duration");
            Console.WriteLine("6. Top 2 hard-working employees");
            Console.WriteLine("0. Exit");
            Console.Write("Choose an option: ");
            
            string choice = Console.ReadLine();

            switch (choice)
            {
                case "1":
                    ShowAllAngajati();
                    break;
                case "2":
                    ShowAllSarcini();
                    break;
                case "3":
                    ShowAllPontaje();
                    break;
                case "4":
                    ShowAllAngajatiOrdered();
                    break;
                case "5":
                    ShowAverageTaskDuration();
                    break;
                case "6":
                    ShowTop2HardWorkingEmployees();
                    break;
                case "0":
                    Console.WriteLine("Exiting the program...");
                    return;
                default:
                    Console.WriteLine("Invalid option, please try again.");
                    break;
            }
            
            Console.WriteLine("\nPress any key to return to the menu...");
            Console.ReadKey();
        }
    }

    private void ShowTop2HardWorkingEmployees()
    {
        foreach (var id in _pontajService.Top2Method())
        {
            Console.WriteLine(_angajatService.FindOne(id));
        }
        
        Console.WriteLine("\n\n\n");

        foreach (var angajat in _pontajService.Top2Sql())
        {
            Console.WriteLine(angajat);
        }
    }

    private void ShowAverageTaskDuration()
    {
        foreach (var sarcina in _sarcinaService.CalculateAverageMethod())
        {
            Console.WriteLine(sarcina.Item1 + " -> " + sarcina.Item2);
        }
        
        Console.WriteLine("\n\n\n");
        
        foreach (var sarcina in _sarcinaService.CalculateAverageSQL())
        {
            Console.WriteLine(sarcina.Item1 + " -> " + sarcina.Item2);
        }
    }

    private void ShowAllAngajatiOrdered()
    {
        Console.WriteLine("All Angajati (Employees):");
        var angajati = _angajatService.FindAllOrderedMethod();

        foreach (var angajat in angajati)
        {
            Console.WriteLine(angajat);
        }
        
        Console.WriteLine("\n\n\n");
        
        angajati = _angajatService.FindAllOrderedSQL();

        foreach (var angajat in angajati)
        {
            Console.WriteLine(angajat);
        }
    }

    private void ShowAllAngajati()
    {
        Console.WriteLine("All Angajati (Employees):");
        var angajati = _angajatService.FindAll();

        foreach (var angajat in angajati)
        {
            Console.WriteLine(angajat);
        }
    }

    private void ShowAllSarcini()
    {
        Console.WriteLine("All Sarcini (Tasks):");
        var sarcini = _sarcinaService.FindAll();

        foreach (var sarcina in sarcini)
        {
            Console.WriteLine(sarcina);
        }
    }

    private void ShowAllPontaje()
    {
        Console.WriteLine("All Pontaje (Time Entries):");
        var pontaje = _pontajService.FindAll();

        foreach (var pontaj in pontaje)
        {
            Console.WriteLine(pontaj);
        }
    }
}
