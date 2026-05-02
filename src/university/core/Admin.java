package university.core;

import java.util.List;
import java.util.Scanner;

public class Admin extends User {

	private static final Admin superAdmin = new Admin("superAdmin@kbtu.kz", "12345678");
	private static List<String> logs;
	private static String action;
	static Scanner input = new Scanner(System.in); 

	private Admin(String email, String password) {
		super(email, password);
	}


	protected static void createSuperAdmin(){
		if (UniversitySystem.users != null) {
        	UniversitySystem.users.add(superAdmin);
    	}
	}


	//Not full done needed more details to work
	public void addUser() {
		// TODO - implement Admin.addUser
		System.out.println("Select user email: ");
		String email = input.nextLine();
		System.out.println("Select user password: ");
		String password = input.nextLine();
		System.out.println("Select user occupation: ");
		String occupation = input.nextLine();
		throw new UnsupportedOperationException();
	}

	public void removeUser() {
		// TODO - implement Admin.removeUser
		throw new UnsupportedOperationException();
	}

	public void updateUser() {
		// TODO - implement Admin.updateUser
		throw new UnsupportedOperationException();
	}

	public List<String> viewLogs() {
		// TODO - implement Admin.viewLogs
		throw new UnsupportedOperationException();
	}


	@Override
	public void update(){
		// TODO - implement Admin.update
		throw new UnsupportedOperationException();
	}
}