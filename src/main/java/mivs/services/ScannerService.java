package mivs.services;

import java.util.Scanner;

public class ScannerService {

    private static final Scanner scanner = new Scanner(System.in);

    public static String scanString() {
        return scanner.next();
    }
    public static int scanInt() {
        return scanner.nextInt();
    }
}
