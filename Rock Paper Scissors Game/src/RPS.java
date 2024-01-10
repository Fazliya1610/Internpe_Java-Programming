import java.util.*;

public class RPS {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int wins = 0;
        int losses = 0;
        int ties = 0;

        System.out.println("Welcome to Rock, Paper, Scissors!");

        while (true) {
            System.out.print("Enter your move (rock, paper, or scissors) or 'exit' to quit: ");
            String playerMove = scanner.nextLine().toLowerCase();

            if (playerMove.equals("exit")) {
                System.out.println("Thanks for playing. Here are the results:");
                System.out.println("Wins: " + wins);
                System.out.println("Losses: " + losses);
                System.out.println("Ties: " + ties);
                break;
            }

            if (!isValidMove(playerMove)) {
                System.out.println("Invalid move. Please enter rock, paper, or scissors.");
                continue;
            }

            String computerMove = generateComputerMove(random);
            System.out.println("Computer's move: " + computerMove);

            String result = determineWinner(playerMove, computerMove);
            System.out.println("Result: " + result);

            if (result.equals("You win!")) {
                wins++;
            } else if (result.equals("Computer wins!")) {
                losses++;
            } else {
                ties++;
            }
        }

        scanner.close();
    }

    private static boolean isValidMove(String move) {
        return move.equals("rock") || move.equals("paper") || move.equals("scissors");
    }

    private static String generateComputerMove(Random random) {
        int randomNumber = random.nextInt(3); // Generates a random number between 0 and 2
        switch (randomNumber) {
            case 0:
                return "rock";
            case 1:
                return "paper";
            case 2:
                return "scissors";
            default:
                return "rock"; // Default case, should never happen
        }
    }

    private static String determineWinner(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "It's a tie!";
        } else if ((playerMove.equals("rock") && computerMove.equals("scissors")) ||
                   (playerMove.equals("paper") && computerMove.equals("rock")) ||
                   (playerMove.equals("scissors") && computerMove.equals("paper"))) {
            return "You win!";
        } else {
            return "Computer wins!";
        }
    }
}
