package university.models.courses;


import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import university.core.*;
import university.models.employee.*;
import university.models.grading.*;
import university.models.students.Student;

public class Course implements Comparable<Course>{

	private String courseId;
	private String name;
	private int credits;
	private CourseType courseType;
	private List<Teacher> instructors;
	private List<Lesson> lessons;
	private List<Student> enrolledStudents;
	private String school;
	private int targetYear;

	public Course(String courseId,String name, int credits, CourseType courseType,List<Teacher> instructors,List<Lesson>lessons,String school,int targetYear,List<Student> enrolledStudents){
		this.courseId = courseId;
		this.name = name;
		this.credits = credits;
		this.courseType = courseType;
		this.instructors = new ArrayList<>();
		this.lessons= new ArrayList<>();
		this.school=school;
		this.enrolledStudents = new ArrayList<>();
		this.targetYear = targetYear;
	}



	//геттеры
	public String getCourseId(){
		return courseId;
	}
	public String getName(){
		return name;
	}
	public CourseType getCourseType(){
		return courseType;
	}
	public int getCredits() {
		return credits;
	}
	public List<Teacher> getInstructors() {
		return instructors;
	}
	public List<Lesson> getLessons() {
		return lessons;
	}
	public List<Student> getEnrolledStudents(){
		return enrolledStudents;
	}
	public String getSchoole(){
		return school;
	}
	public int getTargetYear(){
		return targetYear;
	}


	
	//сеттеры
	public void setCourseType(CourseType type){
		this.courseType = type;
	}


	public void addInstructor(Teacher teacher) {
        if (!instructors.contains(teacher)) {
            instructors.add(teacher);
            System.out.println("Added instructor: "
                + teacher.getEmail());
        }
    }

	public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }



	public void enrollStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }


	public Mark getStudentMark(Student student) {
        return student.getMarks().get(this);
    }


	@Override
	public int compareTo(Course other){
		return this.name.compareTo(other.name);
	}

	@Override
	public String toString(){
		return String.format(
            "[%s] %s | %d credits | %s | School: %s | Year: %d", courseId, name, credits,
            courseType, school, targetYear);
	}

	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(!(o instanceof Course)) return false;
		Course c = (Course) o;
		return Objects.equals(courseId, c.courseId);
	}

	@Overridepublic int hashCode(){
		return Objects.hash(courseId);
	}
}