package mivs.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtils {

    private static final Scanner scanner = new Scanner(System.in);


    public static String scanString(String message) {
        System.out.println(message);
        return scanner.next();
    }

    public static int scanInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Wrong Input Please Enter Number");
            scanner.next();
        }
        int num1 = scanner.nextInt();
            return num1;

    }

    public static int scanInt(String message) {
        System.out.println(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Wrong Input Please Enter Number");
            scanner.next();
        }
        int num1 = scanner.nextInt();
        return num1;

    }

    public static int scanInt(String message, int min, int max) {
        System.out.println(message);
        int enteredNumber;
        while (true) {
            try {
                enteredNumber = scanner.nextInt();
                if (min <= enteredNumber && enteredNumber <= max) {
                    return enteredNumber;
                } else {
                    System.out.println("Entered number should be between " + min + " and " + max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter number");
                scanner.nextLine();
            }
        }
    }

}
