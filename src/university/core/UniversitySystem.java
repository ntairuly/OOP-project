package university.core;

import java.util.ArrayList;
import java.util.Scanner;

public class UniversitySystem {
    private static boolean isRunning = true;
    private static boolean isLogedIn = false;
    private static ArrayList<User> users = new ArrayList<>();
    private static String myEmail;
    private static String myPassword;
    
    public static void main(String[] args) {
        while (isRunning) {
            Scanner input = new Scanner(System.in); 
            if (isLogedIn) {
                System.out.println("");
            }
            else {
                //User data inputs for login
                System.out.println("User name(email): ");
		        myEmail = input.nextLine();

		        System.out.println("Password:");
		        myPassword = input.nextLine();

                //Check if user has @ at the end
		        if (!(myEmail.endsWith("@kbtu.kz"))){
			        myEmail += "@kbtu.kz";
		        }

                //Actual login
                isLogedIn = login(myEmail, myPassword);
                if (isLogedIn){
                    System.out.println("Success");
                }
                else {
                    System.out.println("The login information you entered is incorrect.");
                }
            }
        }
    }


    private static boolean login(String email, String password){
        for( User u: users){
            if (u.getEmail().equals(email) && u.checkPassword(password)){
                return true;
            }
        }
        return false;
    }


    public static void logout(){
        isLogedIn = false;
        myEmail = "";
        myPassword = "";
    }
}