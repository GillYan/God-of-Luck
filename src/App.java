import java.util.Random;
import java.util.Scanner;

public class App {
    // declare text colours
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    // declare text colour reset
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws Exception {
        //initialize variables
        int min = 0, level = 1, input;
        boolean correct;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println(ANSI_CYAN + "\n---------Level " + level + "---------" + ANSI_RESET);
            // get input and compare it to a randomly generated number
            input = getInput(min, level, scan);
            correct = attemptMatch(input, min, level);

            // print result message
            if (correct) {
                System.out.println(ANSI_GREEN + "Correct Answer! On to the next level!\n" + ANSI_RESET);
            } else {
                System.out.println(ANSI_YELLOW + "Wrong Answer, try again next time.\n" + ANSI_RESET);
            }

            level++;
        } while (correct);

        
        scan.close();
    }

    /**
     * Get user input and keep asking until a valid integer is given
     * @param min   Smallest number in range
     * @param max   Largest number in range
     * @param scan  Scanner to get user input
     * @return
     */
    private static int getInput(int min, int max, Scanner scan) {
        int num = -1;

        // ask for a number
        do {
            System.out.print("Enter a number between " + ANSI_BLUE + min + ANSI_RESET + " and " + ANSI_BLUE + max + ANSI_RESET + ":\t");
            String temp = scan.nextLine();
            
            // receive an integer from user within range or show error message
            try {
                int val = Integer.parseInt(temp);
                if (min <= val && val <= max) {
                    num = val;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e){
                System.out.println(ANSI_RED + "Please enter a whole number between " + min + " and " + max + ANSI_RESET + "\n");
            }
        } while (num < min || num > max);

        return num;
    }

    /**
     * Compares input to a randomly generated number
     * @param input User inputted number to compare with
     * @param min   Smallest number in range
     * @param max   Largest number in range
     * @return
     */
    private static boolean attemptMatch(int input, int min, int max) {
        Random r = new Random();
        int randNum = r.nextInt((max + 1) - min) + min;

        System.out.println("Comparing input of: " + input + " to " + randNum);
        if (input == randNum) {
            return true;
        }

        return false;
    }
}
