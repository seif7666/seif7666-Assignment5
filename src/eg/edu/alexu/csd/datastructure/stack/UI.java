package eg.edu.alexu.csd.datastructure.stack;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is to implement all UI methods .
 */
public class UI {
    private static Scanner in=new Scanner(System.in);

    /**
     * Asks user to enter an integer.
     * Check that input is correct.
     * @param input
     * It is the message that will appear to the user.
     * @return The integer entered.
     */
    public static int getInt(String input){
        while(true) {
            System.out.print(input);
            boolean x = in.hasNextInt();
            if (x) {
                int ans = in.nextInt();
                in.nextLine();
                return ans;
            }
            in.nextLine();
            System.out.println("Error!");

        }
    }

    /**
     * The method takes a string from the user.
     * @param input
     * The message printed to the user.
     * @return
     * The string entered.
     */
    public static Object getLine(String input){
        System.out.print(input);
        return in.nextLine();
    }
}
