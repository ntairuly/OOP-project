package university.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import university.core.factory.*;
import university.models.news.Notifiable;
import university.models.other.*;


public class Admin extends User implements Notifiable {

	private static List<String> logs = new ArrayList<>();
	static Scanner input = new Scanner(System.in);

	private Admin(String email, String password) {
		super(email, password);
	}

	public static Admin createAdmin(String email, String password) {
		return new Admin(email, password);
	}

	protected static void createSuperAdmin(UniversitySystem uSystem){
    	if (uSystem != null) {
        	Admin superAdmin = new Admin("superAdmin@kbtu.kz", "SuperAdmin12345678");
        	uSystem.getUsers().add(superAdmin);
    	}
	}

	// Admin: add new user
	public void addUser() {
		System.out.println(Language.INSTANCE.get("Admin.selectEmail"));
		String email = input.nextLine();

		System.out.println(Language.INSTANCE.get("Admin.selectPassword"));
		String password = input.nextLine();

		UniversitySystem uSystem = UniversitySystem.getInstance();
		UserFactory factory = null;

		OccupationType occupation;
		String occupationS;

		while (factory == null) {
			getOccupations();
			System.out.println(Language.INSTANCE.get("Admin.selectOccupation"));
			occupationS = (input.nextLine()).toUpperCase().replace(" ", "");

			try {
				occupation = OccupationType.valueOf(occupationS);
			} catch (IllegalArgumentException e) {
				System.out.println(Language.INSTANCE.get("Admin.occupationError"));
				continue;
			}

			switch (occupation) {
				case USER:
				case EMPLOYEE:
				case GRADUATESTUDENT:
					System.out.println(Language.INSTANCE.get("Admin.occupationCannotSelect"));
					System.out.println(Language.INSTANCE.get("Admin.occupationAbstract"));
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
					factory = PHDStudentFactory.createFactory(uSystem);
					break;
			}
		}

		factory.addUser(email, password);
		logAction(Language.INSTANCE.get("Admin.logAddUser") + email);
		System.out.println(Language.INSTANCE.get("Admin.userAddedSuccessfully"));
	}

	public void getOccupations() {
		System.out.println(Language.INSTANCE.get("Admin.availableOccupations"));
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

		System.out.print(String.format(availableOccup, Language.INSTANCE.get("Admin.cannotBeSelected")));
	}

	public void removeUser() {
		System.out.println(Language.INSTANCE.get("Admin.enterEmailRemove"));
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
			logAction(Language.INSTANCE.get("Admin.logRemoveUser") + email);
			System.out.println(Language.INSTANCE.get("Admin.userRemovedSuccessfully"));
		} else {
			System.out.println(Language.INSTANCE.get("Admin.userNotRemoved"));
			System.out.println(Language.INSTANCE.get("Admin.userDoesNotExist"));
		}
	}

	public void updateUser() {
		System.out.print(Language.INSTANCE.get("Admin.enterEmailUpdate"));
		String email = input.nextLine().trim();
		User u = UniversitySystem.getInstance().findUserByEmail(email);

		if (u == null) {
			System.out.println(Language.INSTANCE.get("Admin.userNotFound") + email);
			return;
		}

		System.out.println(Language.INSTANCE.get("Admin.whatToUpdate"));
		String field = input.nextLine().trim().toUpperCase();

		switch (field) {
			case "ID":
				System.out.print(Language.INSTANCE.get("Admin.newId"));
				u.setId(input.nextLine().trim());
				break;
			case "FIRSTNAME":
				System.out.print(Language.INSTANCE.get("Admin.newFirstName"));
				u.setFirstName(input.nextLine().trim());
				break;
			case "LASTNAME":
				System.out.print(Language.INSTANCE.get("Admin.newLastName"));
				u.setLastName(input.nextLine().trim());
				break;
			case "EMAIL":
				System.out.print(Language.INSTANCE.get("Admin.newEmail"));
				u.setEmail(input.nextLine().trim());
				break;
			default:
				System.out.println(Language.INSTANCE.get("Admin.unknownField"));
				return;
		}

		logAction(Language.INSTANCE.get("Admin.logUpdateUser") + email + Language.INSTANCE.get("Admin.logField") + field);
		System.out.println(Language.INSTANCE.get("Admin.userUpdatedSuccessfully"));
	}

	public List<String> viewLogs() {
		System.out.println(Language.INSTANCE.get("Admin.adminLogs"));
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
		String menuView = Language.INSTANCE.get("Admin.menu");
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
				System.out.println(Language.INSTANCE.get("UniversitySystem.invalidOption"));
				break;
		}
	}

	@Override
	public void update() {
		System.out.println("Notification!");
	}

	@Override
	public void update(String message) {
		System.out.println(Language.INSTANCE.get("Admin.notification") + message);
	}

	@Override
	public String toString() {
		String name = firstName + " " + lastName;
		return String.format(
			Language.INSTANCE.get("Admin.toString"),
			id,
			name,
			email
		);
	}
}
