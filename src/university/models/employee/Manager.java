package university.models.employee;

import java.util.List;
import university.models.message.*;
import university.models.students.*;

public class Manager extends Employee {

	private ManagerType managerType;
	private List<Message> pendingRequests;

	public Manager(String email, String password){
		super(email, password);
	}

	public boolean approveRegistration() {
		// TODO - implement Manager.approveRegistration
		throw new UnsupportedOperationException();
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

	@Override
	public void update(){
		// TODO - implement Admin.update
		throw new UnsupportedOperationException();
	}

}