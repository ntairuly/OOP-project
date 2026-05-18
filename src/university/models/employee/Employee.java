package university.models.employee;

import java.util.Date;
import university.core.*;
import university.models.message.*;

/**
 * Base class for all employees.
 */
public abstract class Employee extends User {

	private double salary;
	private Date hireDate;
	private String department;

	protected Employee(String email, String password) {
		super(email, password);
	}

	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		if (salary < 0) {
			throw new IllegalArgumentException("Salary cannot be negative");
		}
		this.salary = salary;
	}

	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public void sendMessage() {
        System.out.print("Receiver email: ");
        String email = input.nextLine().trim();

        User receiver = UniversitySystem.getInstance().findUserByEmail(email);
        if (receiver == null) {
            System.out.println("User not found: " + email);
            return;
        }

        System.out.print("Subject: ");
        String subject = input.nextLine().trim();

        System.out.print("Content: ");
        String content = input.nextLine().trim();

		String urgStr;
		UrgencyLevel urgency = null;
        
		while(urgency == null){
			System.out.print("Urgency (LOW/MEDIUM/HIGH): ");
        	urgStr = input.nextLine().toUpperCase();

			try {
				urgency = UrgencyLevel.valueOf(urgStr);
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid urgency repeat input");
			}
		}

        Message msg = new Message(this, receiver, subject, content, urgency);
        UniversitySystem.getInstance().addMessage(msg);

        receiver.update("New message: " + msg.toString());
        System.out.println("Message sent");
    }
}