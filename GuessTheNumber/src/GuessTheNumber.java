import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class GuessTheNumber {

    static int highScoreEasy = Integer.MAX_VALUE, highScoreMedium = Integer.MAX_VALUE, highScoreHard = Integer.MAX_VALUE;
    static int secondsEasy = 15, secondMedium = 30, secondsHard = 60;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        boolean playAgain;

        do {
            int difficulty;
            int maxValue = 0, counter = 0;
            int numberUser, randomNumber = 0;
            long startTime = 0;
            long timeLimit = 1000;
            boolean isTimeLimitExceeded = false;
            playAgain = true;

            do {
                System.out.print("Choose difficulty:\n1.Easy (1-10)\n2.Medium (1-100)\n3.Hard (1-1000)\nYour choice: ");
                difficulty = scanner.nextInt();
                switch (difficulty) {
                    case 1:
                        maxValue = 10;
                        timeLimit *= 15;
                        randomNumber = rand.nextInt(maxValue);
                        break;
                    case 2:
                        maxValue = 100;
                        timeLimit *= 30;
                        randomNumber = rand.nextInt(maxValue);
                        break;
                    case 3:
                        maxValue = 1000;
                        timeLimit *= 60;
                        randomNumber = rand.nextInt(maxValue);
                        break;
                    default:
                        System.out.println("Choose a number in the range 1-3");
                }
            } while (difficulty < 1 || difficulty > 3);

            startTime = System.currentTimeMillis();
            do {

                if(System.currentTimeMillis() - startTime > timeLimit) {
                    System.out.println("SORRY, YOU LOST, TIME LIMIT EXCEEDED");
                    isTimeLimitExceeded = true;
                    break;
                }

                System.out.print("Choose a number between 1-" + maxValue + ": ");
                numberUser = scanner.nextInt();

                if (numberUser < 1 || numberUser > maxValue) {
                    System.out.println("Choose a number in the range 1-" + maxValue);
                    break;
                }

                if (numberUser < randomNumber) {
                    System.out.println("The number is smaller than the target number");
                } else if (numberUser > randomNumber) {
                    System.out.println("The number is greatest than the target number");
                }

                counter++;

            } while (numberUser != randomNumber);

            if(!isTimeLimitExceeded) {
                switch (difficulty) {
                    case 1:
                        if (counter < highScoreEasy) {
                            highScoreEasy = counter;
                            System.out.println("Congratulations YOU WIN\nNEW EASY HIGHSCORE: " + highScoreEasy);
                        } else {
                            System.out.println("Congratulations YOU WIN\nCURRENT EASY HIGHSCORE: " + highScoreEasy);
                        }
                        break;
                    case 2:
                        if (counter < highScoreMedium) {
                            highScoreMedium = counter;
                            System.out.println("Congratulations YOU WIN\nNEW MEDIUM HIGHSCORE: " + highScoreMedium);
                        } else {
                            System.out.println("Congratulations YOU WIN\nCURRENT MEDIUM HIGHSCORE: " + highScoreMedium);
                        }
                        break;
                    case 3:
                        if (counter < highScoreHard) {
                            highScoreHard = counter;
                            System.out.println("Congratulations YOU WIN\nNEW HARD HIGHSCORE: " + highScoreHard);
                        } else {
                            System.out.println("Congratulations YOU WIN\nCURRENT HARD HIGHSCORE: " + highScoreHard);
                        }
                        break;
                    default:
                        System.out.println("ERROR");
                }
            }

            System.out.println("Attempts: " + counter);
            System.out.println("Play Again?\n1. YES\n2. NO");

            if(scanner.nextInt() == 2) playAgain = false;
        } while(playAgain);
    }
}