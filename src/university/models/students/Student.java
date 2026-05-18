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



	protected Student(String email, String password) {
		super(email, password);
		this.courses = new ArrayList<>();
		this.marks = new HashMap<>();
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
	public void viewMarks() {
		if(marks.isEmpty()){
			System.out.println("No marks yet");
			return;
		}
		System.out.println("--- MARKS ---");
		for(Map.Entry<Course, Mark> entry : marks.entrySet()){
			System.out.println(entry.getValue());
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
		this.gpa = total / marks.size();
	}

	//транскрипт
	public String getTranscript() {
		StringBuilder s = new StringBuilder();

		s.append("------ TRANSCRIPT -------\n");
		s.append("ID       : ").append(studentId).append("\n");
		s.append("Name     : ").append(getFullName()).append("\n");
		s.append("Major    : ").append(major).append("\n");
		s.append("Year     : ").append(year).append("\n");
		s.append("Credits  : ").append(credits).append("\n");
		s.append("GPA      : ").append(String.format("%.2f", gpa)).append("\n");
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
	public void rateTeacher(Teacher teacher, int rating) {
		if(rating < 1 || rating > 5){
			System.out.println("Rating must be between 1 and 5!");
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


	public void viewRegisteredCourses() {
		if (courses.isEmpty()) {
			System.out.println("No courses registered");
			return;
		}
		System.out.println("--- Registered courses ---");
		for (Course c : courses) {
			System.out.println("- " + c.getName() + " (" + c.getCredits() + " credits)");
		}
		System.out.println("Total credits: " + credits + "/21");
	}

	//Menu


	@Override
	public void userMenu() {
		String menuView= """
				--Student Panel--
				register - register for a course
				marks    - view marks
				gpa      - view current GPA
				trans    - view transcript
				courses  - view registered courses
				rate     - rate a teacher
				edit     - edit my profile (name / id)
				change   - change password
				info     - get info about yourself
				logout   - to get back
				""";
		System.out.println(menuView);

		String command = input.nextLine().trim().toLowerCase();
		switch(command){
			case "register":
				registerForCourseInput();
				break;
			case "marks":
				viewMarks();
				break;
			case "gpa":
				System.out.println("Current GPA: "+String.format("%.2f",gpa));
				break;
			case "trans":
				System.out.println(getTranscript());
				break;
			case "courses":
				viewRegisteredCourses();
				break;
			case "rate":
				rateTeacherInput();
				break;
			case "edit":
				editProfileInput();
				break;
			case "change":
				changePasswordInput();
				break;
			case "info":
				System.out.println(this);
				break;
			case "logout":
				UniversitySystem.getInstance().logout();
				break;
			default:
				System.out.println("Not available function");
				break;
		}
	}

	protected void registerForCourseInput() {
		System.out.print("Enter course ID: ");
		String courseId = input.nextLine().trim();

		Course c = UniversitySystem.getInstance().findCourse(courseId);
		if (c == null) {
			System.out.println("Course not found: " + courseId);
			return;
		}
		registerForCourse(c);
	}

	protected void rateTeacherInput() {
		System.out.print("Enter teacher email: ");
		String email = input.nextLine().trim();
		System.out.print("Enter rating (1-5): ");
		try {
			int rating = Integer.parseInt(input.nextLine().trim());

			Teacher t = UniversitySystem.getInstance().findTeacherByEmail(email);
			if (t == null) {
				System.out.println("Teacher not found: " + email);
				return;
			}
			rateTeacher(t, rating);
		} catch (NumberFormatException e) {
			System.out.println("Invalid rating, expected a number");
		}
	}

	// Редактирование our профиля
	public void editProfileInput() {
		System.out.println(Language.INSTANCE.get("Student.editPrompt"));
		String field = input.nextLine().trim().toLowerCase();

		switch (field) {
			case "firstname":
				System.out.print(Language.INSTANCE.get("Student.newFirstName"));
				setFirstName(input.nextLine().trim());
				break;
			case "lastname":
				System.out.print(Language.INSTANCE.get("Student.newLastName"));
				setLastName(input.nextLine().trim());
				break;
			case "id":
				System.out.print(Language.INSTANCE.get("Student.newId"));
				String newId = input.nextLine().trim();
				setId(newId);
				setStudentId(newId);   // синхронизируем studentId с общим id
				break;
			default:
				System.out.println(Language.INSTANCE.get("Student.unknownField"));
				return;
		}
		System.out.println(Language.INSTANCE.get("Student.profileUpdated"));
	}

	// Уведомления
	@Override
	public void update(String message) {
		System.out.println("Notification for " + studentId + ": " + message);
	}

	@Override
	public void update() {
		System.out.println("No notifications for " + studentId );
	}

	@Override
	public String toString() {
		return String.format(
				Language.INSTANCE.get("Student.toString"),
				studentId, getFullName(), gpa, year, major
		);
	}


}