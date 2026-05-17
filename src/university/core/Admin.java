package university.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import university.core.factory.*;
import university.models.news.Notifiable;


public class Admin extends User implements Notifiable {

	private static List<String> logs = new ArrayList<>();
	private static String action;
	static Scanner input = new Scanner(System.in);

	private Admin(String email, String password) {
		super(email, password);
	}

	public static Admin createAdmin(String email, String password) {
		return new Admin(email, password);
	}

	protected static void createSuperAdmin(UniversitySystem uSystem) {
		if (uSystem != null) {
			Admin superAdmin = new Admin("superAdmin@kbtu.kz", "SuperAdmin12345678");
			uSystem.getUsers().add(superAdmin);
		}
	}

	// Admin: add new user
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
				System.out.println("Such occupation doesn't exist or grammatical error in input\n");
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
					factory = ManagerFactory.createFactory(uSystem);
					break;
				case TEACHER:
					factory = TeacherFactory.createFactory(uSystem);
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
		logAction("Added user: " + email);
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
			logAction("Removed user: " + email);
			System.out.println("User removed succesfully");
		} else {
			System.out.println("User wasn't removed");
			System.out.println("because it doesnt exist");
		}
	}

	public void updateUser() {
		System.out.print("Enter email of user to update: ");
		String email = input.nextLine().trim();
		User u = UniversitySystem.getInstance().findUserByEmail(email);

		if (u == null) {
			System.out.println("User not found: " + email);
			return;
		}

		System.out.println("What to update? (id / firstname / lastname / email)");
		String field = input.nextLine().trim().toUpperCase();

		switch (field) {
			case "ID":
				System.out.print("New id: ");
				u.setId(input.nextLine().trim());
				break;
			case "FIRSTNAME":
				System.out.print("New first name: ");
				u.setFirstName(input.nextLine().trim());
				break;
			case "LASTNAME":
				System.out.print("New last name: ");
				u.setLastName(input.nextLine().trim());
				break;
			case "EMAIL":
				System.out.print("New email: ");
				u.setEmail(input.nextLine().trim());
				break;
			default:
				System.out.println("Unknown field");
				return;
		}

		logAction("Updated user: " + email + " field=" + field);
		System.out.println("User updated successfully");
	}

	public List<String> viewLogs() {
		System.out.println("--Admin logs--");
		for (String l : logs) {
			System.out.println(l);
		}
		return logs;
	}

	private void logAction(String message) {
		logs.add(message);
	}

	@Override
	public void userMenu(){
		String menuView = """
		--Admin panel--
		add    - add new User
		remove - remove User
		update - update User info
		logs   - view logs
		-- account -- 
		change - change password 
		info   - get info about yourself
		""";
		System.out.println(menuView);
		String command = input.nextLine().toUpperCase();
		switch (command) {
			case "ADD":
				addUser();
				break;
			case "REMOVE":
				removeUser();
				break;
			case "UPDATE":
				updateUser();
				break;
			case "LOGS":
				viewLogs();
				break;
			case "CHANGE":
				changePasswordInput();
				break;
			case "INFO":
				System.out.println(toString());
				break;
			default:
				System.out.println("Not available option");
				break;
		}
	}

	@Override
	public void update(String message) {
		System.out.println("Admin notification: " + message);
	}
}