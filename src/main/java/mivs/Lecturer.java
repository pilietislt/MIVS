package mivs;

import java.util.Scanner;

public class Lecturer extends User {
    public Lecturer(String username, String password, Role role, String firstName, String secondName, String code) {
        super(username, password, role, firstName, secondName, code);
    }



    public void lecturerMeniu() {
        System.out.println("Hello " +  this.getRole()+ " "+this.getFirstName());
        System.out.println("1. Add User");
        System.out.println("2. User List");
        System.out.println("3. Add Course");
        System.out.println("4. Course List");
        System.out.println("5. Exit");
        Scanner scanner = new Scanner(System.in);
        int chooice = scanner.nextInt();
    }
}
