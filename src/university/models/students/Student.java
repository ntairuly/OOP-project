package university.models.students;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import university.models.courses.*;
import university.models.grading.*;
import university.models.other.*;
import university.core.*;



public class Student extends User  {

	private String studentId;
	private double gpa;
	private int credits;
	private int year;
	private String major;
	private List<Course> courses;
	private int failCount;
	private StudentOrganization organization;



	protected Student(String email, String password) {  // private!
		super(email, password);
		this.courses = new ArrayList<>();
		this.gpa = 0.0;
		this.credits = 0;
		this.failCount = 0;
	}

	public static Student createStudent(String email, String password) {
		return new Student(email, password);
	}


	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setOrganization(StudentOrganization organization) {
		this.organization = organization;
	}


	public String getStudentId() {
		return studentId;
	}
	public double getGpa() {
		return gpa;
	}
	public int getCredits() {
		return credits;
	}
	public int getYear() {
		return year;
	}
	public String getMajor() {
		return major;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public int getFailCount() {
		return failCount;
	}

	public void registerForCourse() {

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



	@Override
	public void update() {
		// TODO - implement Student.update
		throw new UnsupportedOperationException();
	}


	@Override
	public String toString() {
		return String.format(
				Language.INSTANCE.get("Student.toString"),
				studentId, gpa, year, major
		);
	}
}