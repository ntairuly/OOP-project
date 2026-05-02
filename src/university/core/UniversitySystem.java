package university.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import university.models.other.Language;

public class UniversitySystem {
    private static UniversitySystem obj;


    private static boolean isRunning = true;
    private static boolean isLoggedIn = false;
    protected static List<User> users = new ArrayList<>();;
    private static String myEmail;
    private static String myPassword;
    private static User myUser;
    static Scanner input = new Scanner(System.in); 
    
    private UniversitySystem() {

    }
    static {
        Admin.createSuperAdmin();
    }
    public static void main(String[] args) {
        while (isRunning) {
            if (isLoggedIn) {
                break;
            } else {
                System.out.println(Language.INSTANCE.get("UniversitySystem.getEmail"));
                myEmail = input.nextLine();

                System.out.println(Language.INSTANCE.get("UniversitySystem.getPw"));
                myPassword = input.nextLine();

                if (!(myEmail.endsWith("@kbtu.kz"))) {
                    myEmail += "@kbtu.kz";
                }

                isLoggedIn = login(myEmail, myPassword);
                
                if (isLoggedIn) {
                    System.out.println(Language.INSTANCE.get("UniversitySystem.successlog"));
                    while (myUser.isFirstLogin) {
                        System.out.println(Language.INSTANCE.get("UniversitySystem.pwChange"));
                        changePasswordInput();
                    }
                } else {
                    System.out.println(Language.INSTANCE.get("UniversitySystem.logError"));
                }
            }
        }
    }

    private static boolean login(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equals(email) && u.checkPassword(password)) {
                myUser = u;
                return true;
            }
        }
        return false;
    }

    public static void logout() {
        isLoggedIn = false;
        myEmail = "";
        myPassword = "";
    }

    public static void changePasswordInput() {
        String curPassword = "";
        if (!myUser.isFirstLogin) {
            System.out.print(Language.INSTANCE.get("UniversitySystem.pwCur"));
            curPassword = input.nextLine();
        }
        System.out.print(Language.INSTANCE.get("UniversitySystem.pwNew"));
        String newPassword = input.nextLine();
        System.out.print(Language.INSTANCE.get("UniversitySystem.pwConfirm"));
        String repeatedPassword = input.nextLine();
        myUser.changePassword(curPassword, newPassword, repeatedPassword);
    }


    public static synchronized UniversitySystem getInstance(){
        if (obj == null)
            obj = new UniversitySystem();
        return obj;
    }
}