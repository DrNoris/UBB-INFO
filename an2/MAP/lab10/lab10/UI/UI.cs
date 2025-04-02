
using lab10.Service;

namespace lab10.UI;
    
public class UI
    {
        public MainService mainService;

        public UI(MainService mainService)
        {
            this.mainService = mainService;
        }

        public void Start()
        {
            while (true)
            {
                Console.Clear();
                Console.WriteLine("NBA Management System");
                Console.WriteLine("=====================");
                Console.WriteLine("1. Display players of a team");
                Console.WriteLine("2. Display active players from a match");
                Console.WriteLine("3. Display matches between two dates");
                Console.WriteLine("4. Get total score of a match");
                Console.WriteLine("0. Exit");
                Console.Write("Select an option: ");

                var choice = Console.ReadLine();

                switch (choice)
                {
                    case "1":
                        DisplayTeamPlayers();
                        break;
                    case "2":
                        DisplayActivePlayersFromMatch();
                        break;
                    case "3":
                        DisplayMatchesBetweenDates();
                        break;
                    case "4":
                        DisplayMatchTotalScore();
                        break;
                    case "0":
                        return;
                    default:
                        Console.WriteLine("Invalid option. Press Enter to try again.");
                        Console.ReadLine();
                        break;
                }
            }
        }

        private void DisplayTeamPlayers()
        {
            Console.Write("Enter team ID: ");
            if (int.TryParse(Console.ReadLine(), out int teamId))
            {
                var players = mainService.GetTeamPlayers(teamId);
                Console.WriteLine($"Players found:");
                foreach (var player in players)
                {
                    Console.WriteLine($"- {player.name} (ID: {player.Id})");
                }
            }
            else
            {
                Console.WriteLine("Invalid team ID.");
            }
            Pause();
        }

        private void DisplayActivePlayersFromMatch()
        {
            Console.Write("Enter match ID: ");
            if (int.TryParse(Console.ReadLine(), out int matchId))
            {
                Console.Write("Enter team ID: ");
                if (int.TryParse(Console.ReadLine(), out int teamId))
                {
                    var activePlayers = mainService.GetActivePlayersFromMatch(matchId, teamId);
                    Console.WriteLine($"Active Players from Match {matchId} for Team {teamId}:");
                    foreach (var activePlayer in activePlayers)
                    { 
                        var player = mainService.playerRepository.FindOne(activePlayer.playerId);
                        Console.WriteLine($"- {player.name}");
                    }
                }
                else
                {
                    Console.WriteLine("Invalid team ID.");
                }
            }
            else
            {
                Console.WriteLine("Invalid match ID.");
            }
            Pause();
        }

        private void DisplayMatchesBetweenDates()
        {
            Console.Write("Enter start date (yyyy-MM-dd): ");
            if (DateTime.TryParse(Console.ReadLine(), out DateTime startDate))
            {
                Console.Write("Enter end date (yyyy-MM-dd): ");
                if (DateTime.TryParse(Console.ReadLine(), out DateTime endDate))
                {
                    var matches = mainService.GetMatchesBetweenTwoDates(startDate, endDate);
                    Console.WriteLine($"Matches between {startDate:yyyy-MM-dd} and {endDate:yyyy-MM-dd}:");
                    foreach (var match in matches)
                    {
                        Console.WriteLine($"- Match ID: {match.Id}, Date: {match.date:yyyy-MM-dd}");
                    }
                }
                else
                {
                    Console.WriteLine("Invalid end date.");
                }
            }
            else
            {
                Console.WriteLine("Invalid start date.");
            }
            Pause();
        }

        private void DisplayMatchTotalScore()
        {
            Console.Write("Enter match ID: ");
            if (int.TryParse(Console.ReadLine(), out int matchId))
            {
                var totalScore = mainService.GetTotalScoreOnMatch(matchId);
                Console.WriteLine($"Total score for Match {matchId}: {totalScore} points.");
            }
            else
            {
                Console.WriteLine("Invalid match ID.");
            }
            Pause();
        }

        private void Pause()
        {
            Console.WriteLine("\nPress Enter to return to the main menu...");
            Console.ReadLine();
        }
    }
