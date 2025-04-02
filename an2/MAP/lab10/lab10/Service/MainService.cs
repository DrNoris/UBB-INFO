using lab10.Domain;
using lab10.Repository;

namespace lab10.Service;

public class MainService
{
    public TeamRepository teamRepository;
    public PlayerRepository playerRepository;
    public PupilRepository pupilRepository;
    public ActivePlayerRepository activePlayerRepository;
    public GameRepository gameRepository;

    public MainService(TeamRepository teamRepository, PupilRepository pupilRepository,
        ActivePlayerRepository activePlayerRepository, GameRepository gameRepository, PlayerRepository playerRepository)
    {
        this.teamRepository = teamRepository;
        this.pupilRepository = pupilRepository;
        this.activePlayerRepository = activePlayerRepository;
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }
    
    public delegate IEnumerable<Player> TeamPlayersDelegate(int teamId);
    public delegate IEnumerable<ActivePlayer> MatchActivePlayersDelegate(int match, int team);
    public delegate IEnumerable<Game> MatchGamesDelegate(DateTime start, DateTime end);
    public delegate int TotalScoreDelegate(int match);
    
    public TeamPlayersDelegate GetTeamPlayers => (team) =>
        playerRepository.FindAll().Where(player => player.team.Id == team);

    public MatchActivePlayersDelegate GetActivePlayersFromMatch => (match, team) =>
        activePlayerRepository.FindAll().Where(ap =>
            ap.gameId == match &&
            playerRepository.FindOne(ap.playerId).team.Id == team);
    
    public MatchGamesDelegate GetMatchesBetweenTwoDates => (start, end) =>
        gameRepository.FindAll().Where(game => game.date >= start && game.date <= end);

    public TotalScoreDelegate GetTotalScoreOnMatch => (match) =>
        activePlayerRepository.FindAll().Where(ap => ap.gameId == match).Sum(ap => ap.points);

}