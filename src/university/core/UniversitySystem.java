package university.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import university.models.other.Language;

public class UniversitySystem {
    private static UniversitySystem obj;


    private boolean isRunning = true;
    private boolean isLoggedIn = false;
    protected List<User> users = new ArrayList<>();;
    private String myEmail;
    private String myPassword;
    private User myUser;
    static Scanner input = new Scanner(System.in); 
    
    protected UniversitySystem() {
        Admin.createSuperAdmin(this);
    }

    public List<User> getUsers() {
        return users;
    }

    public static synchronized UniversitySystem getInstance(){
        if (obj == null)
            obj = new UniversitySystem();
        return obj;
    }

    public void start(){
        while(isRunning){
            if (isLoggedIn){
                mainMenu();
            } else {
                loginMenu();
            }
        }
    }


    //Login and main menu
    private void loginMenu() {
        System.out.print(Language.INSTANCE.get("UniversitySystem.getEmail"));
        myEmail = input.nextLine();

        System.out.print(Language.INSTANCE.get("UniversitySystem.getPw"));
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

    private void mainMenu(){
        System.out.println(Language.INSTANCE.get("UniversitySystem.menuTitle"));
        System.out.println(Language.INSTANCE.get("UniversitySystem.menuOptions"));
        System.out.println(Language.INSTANCE.get("UniversitySystem.optionProfile"));
        System.out.println(Language.INSTANCE.get("UniversitySystem.optionPassword"));
        System.out.println(Language.INSTANCE.get("UniversitySystem.optionLogout"));
        System.out.print(Language.INSTANCE.get("UniversitySystem.chooseOption") + " ");
        String option = input.nextLine(); 
        if (option.equals("1")){
            System.out.println(this.myUser);
        } else if (option.equals("2")){
            changePasswordInput();
        } else if (option.equals("3")){
            logout();
        } else {
            System.out.println(("UniversitySystem.invalidOption"));
        }
    }


    //Login and logout logic
    private boolean login(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equals(email) && u.checkPassword(password)) {
                myUser = u;
                return true;
            }
        }
        return false;
    }

    private void logout() {
        System.out.println(Language.INSTANCE.get("UniversitySystem.logout"));
        isLoggedIn = false;
        myEmail = "";
        myPassword = "";
        myUser = null;
    }


    //Change passwrod logic
    private void changePasswordInput() {
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



}