package university.core;

import java.util.List;
import java.util.Scanner;
import university.core.factory.*;
import university.models.news.Notifiable;

public class Admin extends User implements Notifiable{

	private static List<String> logs;
	private static String action;
	static Scanner input = new Scanner(System.in); 

	private Admin(String email, String password) {
		super(email, password);
	}

	public static Admin createAdmin(String email, String password){
		if (!(email.endsWith("@kbtu.kz"))) {
            email += "@kbtu.kz";
        }
		
		Admin newAdmin = new Admin(email, password);
		return newAdmin;
	}


	protected static void createSuperAdmin(UniversitySystem uSystem){
		if (uSystem != null) {
            Admin superAdmin = new Admin("superAdmin@kbtu.kz", "SuperAdmin12345678");
            uSystem.getUsers().add(superAdmin);
    	}
	}


	//Not full done needed more details to work
	//Translation doesnt complete
	public void addUser() {
		System.out.println("Select user email: ");
		String email = input.nextLine();
		
		System.out.println("Select user password: ");
		String password = input.nextLine();

		UniversitySystem uSystem = UniversitySystem.getInstance();
		UserFactory factory = null;

		OccupationType occupation;
		String occupationS;
		
		while (factory == null) {
			getOccupations();
			System.out.println("Select user occupation(eng): ");
			occupationS = (input.nextLine()).toUpperCase().replace(" ", "");
			
			try {
            	occupation = OccupationType.valueOf(occupationS);
        	} catch (IllegalArgumentException e) {
            	System.out.println("Such occupation doesn't exist or grammatical error in input.\n");
            	continue;
        	}

			switch (occupation) {
				case USER:
				case EMPLOYEE: 
				case GRADUATESTUDENT:
					System.out.println("This occupation cant be selected");
					System.out.println("Because it is abstract");
					break;
				case ADMIN:
					factory = AdminFactory.createFactory(uSystem);
					break;
				case MANAGER:
					factory = AdminFactory.createFactory(uSystem); // ManagerFactory is not created so AdminFactory for now
					break;
				case TEACHER:
					factory = AdminFactory.createFactory(uSystem); // TeacherFactory is not created so AdminFactory for now
					break;
				case STUDENT:
					factory = StudentFactory.createFactory(uSystem);
				    break;
				case MASTERSTUDENT:
					factory = MasterStudentFactory.createFactory(uSystem);
					break;
				case PHDSTUDENT:
					factory = StudentFactory.createFactory(uSystem);
					break;
			}
		}

		factory.addUser(email, password);
		System.out.println("User added succesfully");
	}

	public void getOccupations() {
		System.out.println("Available occupations:");
		String availableOccup = """
		USER(%1$s)
		|
		|----> ADMIN
		|
		|----> EMPLOYEE(%1$s)
		|      |
		|      |----> MANAGER
		|      |
		|      |----> TEACHER
		|
		|----> STUDENT
		       |
			   |----> GRADUATESTUDENT(%1$s)
			          |
					  |----> MASTERSTUDENT 
					  |
					  |----> PHDSTUDENT
		""";

		System.out.print(String.format(availableOccup, "cant be selected"));
	}

	public void removeUser() {
		System.out.println("Enter email of user to remove: ");
    	String email = input.nextLine();
    	List<User> users = UniversitySystem.getInstance().getUsers();
		int prevSize = users.size();
		
		for (int i = prevSize - 1; i >= 0; i--) {
    		User u = users.get(i);
    		if (u.getEmail().equals(email)) {
        		users.remove(i);
    		}
		}
    	
		int curSize = users.size();
		if (curSize != prevSize) { 
    		System.out.println("User removed succesfully");
		}
		else{
			System.out.println("User wasn't removed");
			System.out.println("because it doesnt exist");
		}
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
	public void userMenu(){
		String menuView = """
		--Admin panel--
		add    - add new User
		remove - remove User
		update - update User info
		logs   - view logs
		change - change password 
		info   - get info about yourself
		""";
		System.out.println(menuView);
		String command = input.nextLine().toUpperCase();
		switch (command) {
			case "add":
				addUser();
				break;
			case "remove":
				removeUser();
				break;
			case "update":
				updateUser();
				break;
			case "logs":
				viewLogs();
				break;
			case "change":
				changePasswordInput();
				break;
			case "info":
				System.out.println(toString());
				break;
			default:
				System.out.println("Not available option");
				break;
		}
	}

	@Override
	public void update(String message){
		// TODO - implement Admin.update
		throw new UnsupportedOperationException();
	}

}