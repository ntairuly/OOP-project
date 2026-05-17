package university.core;

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
	Scanner input = new Scanner(System.in); 

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public abstract void userMenu();
	
	public abstract void update(String message);


	//Used for login
	public boolean checkPassword(String password){
		return this.password.equals(password);
	}

	public String getEmail(){
		return this.email;
	}

	//Change passwrod logic
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

	//Used to change password
	public void changePassword(String curPassword, String newPassword, String repeatedPassword){
		if (curPassword.equals(this.password) || isFirstLogin){
			if (newPassword.equals(repeatedPassword)) {
				if (newPassword.length() >= 8){
					this.password = newPassword;
					this.isFirstLogin = false;
					System.out.println(Language.INSTANCE.get("User.pwUpdated"));
				}
				else {
					System.out.println(Language.INSTANCE.get("User.pwTooShort"));
				}
			}
			else {
				System.out.println(Language.INSTANCE.get("User.pwMismatch"));
			}
		}
		else {
			System.out.println(Language.INSTANCE.get("User.pwIncorrect"));
		}
	}


	//Overrides
	@Override
	public String toString() {
		// Return only necessary fields: id, name, email
		// Other fields are ignored for better readability and security
		String name = firstName + " " +  lastName;

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
		if (!(o instanceof User)){
			return false;
		}
		User oUser = (User) o;
		return Objects.equals(id, oUser.id) &&
				Objects.equals(email, oUser.email);
	}

	@Override
	public int hashCode() {
		// Return hash using only id and email
		// because they are unique and stable
		return Objects.hash(id, email);
	}
}