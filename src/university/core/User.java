package university.core;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import university.models.news.*;
import university.models.other.*;
public abstract class User implements Notifiable  {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<Journal> attendanceJournals;
	//Previous SubsсribedJournals not understandable 

	Scanner input = new Scanner(System.in); 

	public boolean login() {
		// TODO - implement User.login
		
		throw new UnsupportedOperationException();
	}

	public void logout() {
		// TODO - implement User.logout
		throw new UnsupportedOperationException();
	}

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
		// TODO - implement User.equals
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