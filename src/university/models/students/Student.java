package university.models.students;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import university.models.courses.*;
import university.models.grading.*;
import university.models.other.*;
import university.models.employee.*;
import university.core.*;



public class Student extends User  {

	private String studentId;
	private double gpa;
	private int credits;
	private int year;
	private String major;
	private List<Course> courses;
	private Map<Course, Mark> marks;
	private int failCount;
	private StudentOrganization organization;
	private boolean isResearcher = false;



	protected Student(String email, String password) {  // private!
		super(email, password);
		this.courses = new ArrayList<>();
		this.marks = new HashMap<>()
		this.gpa = 0.0;
		this.credits = 0;
		this.failCount = 0;
	}

	public static Student createStudent(String email, String password) {
		return new Student(email, password);
	}

	// рег на курсы
	public void registerForCourse(Course c) {
		if(credits + c.getCredits() > 21){
			System.out.println("Error: exceeds 21 credit limit! " + "Current: " + credits + ", Course: " + c.getCredits());
			return;
		}

		if(failCount >= 3){
			System.out.println("Error: cannot register, you have failed 3 courses!");
			return;
		}

		if(courses.contains(c)){
			System.out.println("Error: already registered for " + c.getName());
			return;
		}

		courses.add(c);
		credits += c.getCredits();
		System.out.println("Successfully registered for: " + c.getName() + " (" + c.getCredits() + " credits)");

	}


    //сеттеры
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

	//геттеры
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

	public Map<Course, Mark> getMarks(){
		return marks;
	}

	public int getFailCount() {
		return failCount;
	}

	public boolean isResearcher(){
		return isResearcher;
	}


	//смотреть оценки
	public Map<Course, Mark> viewMarks() {
		if(marks.isEmpty()){
			System.out.println("No marks!");
			return;
		}

		System.out.println("--- MARKS ---");
		for(Map.Entry<Course, Mark> entry : marks.entrySet()){
			System.out.println(entry.getValue())''
		}
	}

	//добавить оценки
	public void addMark(Course course, Mark mark){
		marks.put(course, mark);
		if(!mark.isPassed()){
			failCount++;
		}
		updateGpa();
	}

	//обнова гпа
	private void updateGpa(){
		if(marks.isEmpty()){
			this.gpa = 0.0;
			return;
		}

		double total = 0.0;
		for(Mark m : marks.values()){
			total += m.getGpaPoints();
		}
		this.gpa = toatl / marks.size();
	}

	\\транскрипт
	public String getTranscript() {
		StringBuilder s = new StringBuilder();

		s.append("------ TRANSCRIPT -------\n");
		s.append("ID       :").append(studentId).append("\n");
		s.append("Major    :").append(major).append("\n");
		s.append("Year     :").append(year).append("\n");
		s.append("Credits  :").append(credits).append("\n");
		s.append("GPA      :").append(
			String.format("%.2f", gpa)).append("\n");
		s.append("-------------------------\n");
		

		if (marks.isEmpty()){
			s.append("No courses completed. \n");
		}else{
			for(Map.Entry<Course, Mark> entry: marks.entrySet()){
				Mark m = entry.getValue();
				s.append(String.format("%-30s %s (%.1f)\n", entry.getKey().getName(), 
				m.getLetterGrade(), m.getTotal()));
			}
		}

		s.append("-------------------------\n");
		return s.toString();
	}


	//оценка преподавателей
	public void rateTeacher(Tecaher teacher, int rating) {
		if(rating < 1 || rating > 5){
			System.out.println("Rateing must be between 1 and 5!");
			return;
		}

		teacher.addRating(rating);
		System.out.println("Rated " + teacher.getEmail() + ": " + rating + "/5");
	}


	//стать исследователем
	public void becomeResearcher(){
		this.isResearcher = true;
		System.out.println(studentId + " is now a Researcher!");
	}


	// Уведомления
	@Override
	public void update(string message) {
		System.out.println("Notification for " + studentId + ": " + message);
	}


	@Override
	public String toString() {
		return String.format(
				Language.INSTANCE.get("Student.toString"),
				studentId, gpa, year, major
		);
	}
}