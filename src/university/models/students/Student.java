package university.core;

import java.util.List;
import java.util.Map;
import university.models.courses.*;
import university.models.grading.*;
import university.models.other.*;



public class Student extends User {

	private String studentId;
	private double gpa;
	private int credits;
	private int year;
	private String major;
	private List<Course> courses;
	private int failCount;
	private StudentOrganization organization;

	public void registerForCourse() {
		// TODO - implement Student.registerForCourse
		throw new UnsupportedOperationException();
	}

	public Map<Course, Mark> viewMarks() {
		// TODO - implement Student.viewMarks
		throw new UnsupportedOperationException();
	}

	public String getTranscript() {
		// TODO - implement Student.getTranscript
		throw new UnsupportedOperationException();
	}

	public void rateTeacher() {
		// TODO - implement Student.rateTeacher
		throw new UnsupportedOperationException();
	}

	public double getGpa() {
		return this.gpa;
	}

}