public abstract class User implements Notifiable {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Language language;
	private List<Journal> subscribedJournals;

	public boolean login() {
		// TODO - implement User.login
		throw new UnsupportedOperationException();
	}

	public void logout() {
		// TODO - implement User.logout
		throw new UnsupportedOperationException();
	}

	public string toString() {
		// TODO - implement User.toString
		throw new UnsupportedOperationException();
	}

	public boolean equals() {
		// TODO - implement User.equals
		throw new UnsupportedOperationException();
	}

	public int hashCode() {
		// TODO - implement User.hashCode
		throw new UnsupportedOperationException();
	}

}