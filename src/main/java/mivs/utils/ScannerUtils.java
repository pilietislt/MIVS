package mivs.utils;

import java.util.Scanner;

public class ScannerUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static String scanString() {
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
}
