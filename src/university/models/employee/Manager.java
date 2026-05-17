package university.models.employee;

import java.util.List;
import java.util.ArrayList;
import university.models.message.*;
import university.models.students.*;

public class Manager extends Employee {

	private ManagerType managerType;
	private List<Message> pendingRequests;

	protected Manager(String email, String password){
		super(email, password);
	}

	public static Manager createManager(String email, String password){
		return new Manager(email, password);
	}

	public boolean approveRegistration() {
        if (pendingRequests == null) pendingRequests = new ArrayList<>();

        if (pendingRequests.isEmpty()) {
            System.out.println("No pending registration requests.");
            return false;
        }

        System.out.println("Approved " + pendingRequests.size() + " pending request(s).");
        pendingRequests.clear();
        return true;
    }

	public void assignTeacher() {
		// TODO - implement Manager.assignTeacher
		throw new UnsupportedOperationException();
	}

	public void addCourse() {
		// TODO - implement Manager.addCourse
		throw new UnsupportedOperationException();
	}

	public String generateReport() {
		// TODO - implement Manager.generateReport
		throw new UnsupportedOperationException();
	}

	public void manageNews() {
		// TODO - implement Manager.manageNews
		throw new UnsupportedOperationException();
	}

	public List<Student> viewStudentsSortedByGpa() {
		// TODO - implement Manager.viewStudentsSortedByGpa
		throw new UnsupportedOperationException();
	}
}