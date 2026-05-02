package university.models.students;

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


	public Student(String email, String password,String studentId,double gpa,int credits,int year,String major,List<Course> courses,int failCount, StudentOrganization organization){
		super(email,password);
		this.studentId = studentId;
		this.gpa = gpa;
		this.credits = credits;
		this.year = year;
		this.major = major;
		this.courses = courses;
		this.failCount = failCount;
		this.organization = organization;
	}


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

	@Override
	public void update() {
		// TODO - implement Student.update
		throw new UnsupportedOperationException();
	}

	public String getStudentId() {
		return studentId;
	}

	@Override
	public String toString() {
		return String.format(
				Language.INSTANCE.get("Student.toString"),
				studentId, gpa, year, major
		);
	}
}