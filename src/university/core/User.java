package university.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import university.models.news.*;
import university.models.other.*;


public abstract class User implements Notifiable  {

	protected boolean isFirstLogin = true;
	private String id = "Unknown";
	private String firstName = "Unknown";
	private String lastName = "Unknown";
	private String email;
	private String password;
	private List<Journal> SubsсribedJournals;
	protected Scanner input = new Scanner(System.in);

	public User(String email, String password) {
		this.email = email;
		this.password = password;
		this.SubsсribedJournals = new ArrayList<>();
	}

	public abstract void userMenu();

	@Override
	public abstract void update(String message);

	// Used for login
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	public String getEmail() {
		return this.email;
	}

	// Getters/setters
	public String getId() { 
		return id; 
	}
	public void setId(String id) { 
		this.id = id; 
	}

	public String getFirstName() { 
		return firstName; 
	}
	public void setFirstName(String firstName) { 
		this.firstName = firstName; 
	}

	public String getLastName() { 
		return lastName; 
	}
	public void setLastName(String lastName) { 
		this.lastName = lastName; 
	}

	public String getFullName() { 
		return firstName + " " + lastName; 
	}

	public void setEmail(String email) {	
		if (!email.endsWith("@kbtu.kz")) {
			email += "@kbtu.kz";
		}
		this.email = email;
	}


	//Journal
	public List<Journal> getSubscribedJournals() {
		return SubsсribedJournals;
	}

	public void subscribeJournal(Journal j) {
		if (j != null && !SubsсribedJournals.contains(j)) SubsсribedJournals.add(j);
	}

	public void unsubscribeJournal(Journal j) {
		SubsсribedJournals.remove(j);
	}

	// Change password logic
	public void changePasswordInput() {
		String curPassword = "";
		if (!this.isFirstLogin) {
			System.out.print(Language.INSTANCE.get("UniversitySystem.pwCur"));
			curPassword = input.nextLine();
		}
		System.out.print(Language.INSTANCE.get("UniversitySystem.pwNew"));
		String newPassword = input.nextLine();
		System.out.print(Language.INSTANCE.get("UniversitySystem.pwConfirm"));
		String repeatedPassword = input.nextLine();
		this.changePassword(curPassword, newPassword, repeatedPassword);
	}

	public void changePassword(String curPassword, String newPassword, String repeatedPassword) {
		if (curPassword.equals(this.password) || isFirstLogin) {
			if (newPassword.equals(repeatedPassword)) {
				if (newPassword.length() >= 8) {
					this.password = newPassword;
					this.isFirstLogin = false;
					System.out.println(Language.INSTANCE.get("User.pwUpdated"));
				} else {
					System.out.println(Language.INSTANCE.get("User.pwTooShort"));
				}
			} else {
				System.out.println(Language.INSTANCE.get("User.pwMismatch"));
			}
		} else {
			System.out.println(Language.INSTANCE.get("User.pwIncorrect"));
		}
	}

	// Overrides
	@Override
	public String toString() {
		String name = firstName + " " + lastName;
		return String.format(
			Language.INSTANCE.get("User.toString"),
			id,
			name,
			email
		);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof User)) return false;
		User oUser = (User) o;
		return Objects.equals(id, oUser.id) &&
				Objects.equals(email, oUser.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email);
	}
}