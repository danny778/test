import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by danie on 05/11/2016.
 */
public class Cards {

    public static void main(String[] args) throws IOException {
        playYourCardsRightLeaderBoard();
    }

    public static void playYourCardsRightLeaderBoard() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean win = true;
        int lastCard = 0;
        boolean firstGame = true;
        Random random = new Random();
        int score = 0;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> scores = new ArrayList<>();
        String name;

        System.out.println("Enter your name");
        name = scanner.next();

        while (win) {

            int[] cards = new int[52];
            String[] suit = {"Spades", "Hearts", "Diamonds", "Clubs"};
            String[] number = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "Jack", "Queen", "King"};

            for (int i = 0; i < 52; i++) {
                cards[i] = i;

            }

            for (int i = 0; i < 52; i++) {
                int position = random.nextInt(51);
                int placeHolder = cards[i];
                cards[i] = cards[position];
                cards[position] = placeHolder;
            }

            String suitName = suit[cards[0] / 13];
            String numberName = number[cards[0] % 13];

            if (!firstGame) {
                System.out.println("Will the next card be lower, higher, or equal?");
                String guess = scanner.next();

                if (lastCard > cards[1] % 13) {
                    if (guess.equalsIgnoreCase("lower")) {
                        System.out.println("You were right!");
                        score++;
                    } else {
                        System.out.println("You lose!");
                        System.out.println("You got: " + score);
                        win = false;

                    }
                } else if (lastCard < cards[1] % 13) {
                    if (guess.equalsIgnoreCase("higher")) {
                        System.out.println("You were right!");
                        score++;
                    } else {
                        System.out.println("You Lose!");
                        System.out.println("You got: " + score);
                        win = false;

                    }

                } else if (lastCard == cards[1] % 13) {
                    if (guess.equalsIgnoreCase("equal")) {
                        System.out.println("You were right!");
                    } else {
                        System.out.println("You lose!");
                        System.out.println("You got: " + score);
                        win = false;

                    }

                }

            }
            if (win == true) {
                lastCard = (cards[1] % 13);
                firstGame = false;
                System.out.println("The Card is " + numberName + " of " + suitName);
            }

        }
        names.add(name);
        scores.add(score);
        System.out.println(names);
        System.out.println(scores);
        leaderBoardHelper(scores, names, false);
    }

    public static void leaderBoardHelper(ArrayList<Integer> scores, ArrayList<String> names, boolean read) throws IOException {
        if (!read) {
            File scoreList = new File("Test.txt");

            if (!scoreList.exists()) {
                scoreList.createNewFile();
            }

            FileWriter fW = new FileWriter(scoreList.getAbsoluteFile(), true);
            BufferedWriter bW = new BufferedWriter(fW);

            for (int i = 0; i < scores.size(); i++) {
                bW.write(names.get(i) + "\n");
                bW.write(scores.get(i) + "\n");
            }

            bW.close();


        }
        if (read) {
            FileInputStream inputStream = new FileInputStream("Test.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();

        }

    }
}

