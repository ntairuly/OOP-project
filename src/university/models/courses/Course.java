package university.models.courses;

// Core Classes

import java.util.List;
import university.core.*;
import university.models.employee.*;
import university.models.grading.*;
import university.models.students.Student;

public class Course {

	private String courseId;
	private String name;
	private int credits;
	private CourseType courseType;
	private List<Teacher> instructors;
	private List<Lesson> lessons;
	private List<Student> enrolledStudents;
	private String school;
	private int targetYear;

	public Course(String courseId,String name, int credits, CourseType courseType,List<Teacher> instructors,List<Lesson>lessons,)

	public void addInstructor() {
		// TODO - implement Course.addInstructor
		throw new UnsupportedOperationException();
	}

	public Mark getStudentMark() {
		// TODO - implement Course.getStudentMark
		throw new UnsupportedOperationException();
	}

	public int compareTo() {
		// TODO - implement Course.compareTo
		throw new UnsupportedOperationException();
	}

}