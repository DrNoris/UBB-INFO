using lab10.Domain;
using lab10.Repository;
using lab10.Service;
using lab10.UI;

public class Program
{
    public static void Main(string[] args)
    {
        var teamRepository = new TeamRepository();
        var pupilRepository = new PupilRepository();
        var gameRepository = new GameRepository(teamRepository);
        var playerRepository = new PlayerRepository(teamRepository);
        var activePlayerRepository = new ActivePlayerRepository(playerRepository, gameRepository);

        var mainService = new MainService(teamRepository, pupilRepository, activePlayerRepository, gameRepository,
            playerRepository);

        var UI = new UI(mainService);
        
        UI.Start();
    }
}
