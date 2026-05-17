package university.models.employee;

import java.util.Date;
import university.core.*;

public abstract class Employee extends User {

	private double salary;
	private Date hireDate;
	private String department;


	public Employee(String email, String password){
		super(email, password);
	}

	public void sendMessage() {
		//Not done because Notifications are not complete
		throw new UnsupportedOperationException();
	}

}