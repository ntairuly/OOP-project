package university.models.courses;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import university.models.employee.*;
import university.models.grading.*;
import university.models.students.Student;


public class Course implements Comparable<Course> {

	private String courseId;
	private String name;
	private int credits;
	private CourseType courseType;
	private List<Teacher> instructors;
	private List<Lesson> lessons;
	private List<Student> enrolledStudents;
	private String school;
	private int targetYear;

	public Course(String courseId, String name, int credits, CourseType courseType,
				  List<Teacher> instructors, List<Lesson> lessons,
				  String school, int targetYear, List<Student> enrolledStudents) {
		
		this.courseId = courseId;
		this.name = name;
		this.credits = credits;
		this.courseType = courseType;
		this.school = school;
		this.targetYear = targetYear;

		if (instructors == null){
			this.instructors = new ArrayList<>();
		} else {
			this.instructors = instructors;
		}
		if (lessons == null){
			this.lessons = new ArrayList<>();
		} else {
			this.lessons = lessons;
		}
		if (enrolledStudents == null){
			this.enrolledStudents = new ArrayList<>();
		} else {
			this.enrolledStudents = enrolledStudents;
		}
	}

	// Getters/setters
	public int getCredits(){ 
		return credits; 
	}
	public void setCredits(int credits) { 
		this.credits = credits;  
	}

	public String getName(){ 
		return name;  
	}
	public void setName(String name) { 
		this.name = name; 
	}
	
	public String getCourseId(){ 
		return courseId; 
	}
	public void setCourseId(String courseId) { 
		this.courseId = courseId; 
	}

	public CourseType getCourseType() { 
		return courseType; 
	}
	public void setCourseType(CourseType courseType) { 
		this.courseType = courseType; 
	}

	public String getSchool() { 
		return school; 
	}
	public void setSchool(String school) { 
		this.school = school; 
	}

	public int getTargetYear(){ 
		return targetYear; 
	}
	public void setTargetYear(int targetYear) { 
		this.targetYear = targetYear; 
	}

	public List<Teacher> getInstructors() { 
		return instructors; 
	}
	public List<Lesson> getLessons() { 
		return lessons; 
	}
	public List<Student> getEnrolledStudents() { 
		return enrolledStudents; 
	}


	public void addInstructor(Teacher teacher) {
		if (!instructors.contains(teacher)) {
			instructors.add(teacher);
			System.out.println("Teacher added sucessfuly");
		}
	}

	public void addStudent(Student s) {
		if (!enrolledStudents.contains(s)) {
			enrolledStudents.add(s);
			System.out.println("Student added sucessfuly");
		}
	}

	public Mark getStudentMark() {
		for (Student s : enrolledStudents) {
			Mark m = s.getMarks().get(this);
			if (m != null) { 
				return m;
			}
		}
		return null;
	}

	@Override
	public int compareTo(Course other) {
		if (other == null) return 1;
		return this.courseId.compareToIgnoreCase(other.courseId);
	}

	@Override
	public String toString() {
		return "Course[" +
				"id='" + courseId + '\'' +
				", name='" + name + '\'' +
				", credits=" + credits +
				", type=" + courseType +
				", school='" + school + '\'' +
				", year=" + targetYear +
				"]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Course)) return false;
		Course course = (Course) o;
		return Objects.equals(courseId, course.courseId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseId);
	}
}