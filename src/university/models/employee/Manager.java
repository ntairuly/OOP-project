package university.models.employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import university.core.*;
import university.models.courses.Course;
import university.models.message.*;
import university.models.news.News;
import university.models.students.Student;


public class Manager extends Employee {

	private ManagerType managerType;
	private List<Message> pendingRequests;
	UniversitySystem uSystem = UniversitySystem.getInstance();

	protected Manager(String email, String password) {
		super(email, password);
		this.pendingRequests = new ArrayList<>();
	}

	public static Manager createManager(String email, String password){
		return new Manager(email, password);
	}

	// Getters/setters
	public ManagerType getManagerType() { 
		return managerType; 
	}
	public void setManagerType(ManagerType managerType) { 
		this.managerType = managerType; 
	}

	public List<Message> getPendingRequests() { 
		return pendingRequests; 
	}

	public void addPendingRequest(Message msg) {
		pendingRequests.add(msg);
	}


	public boolean approveRegistration() {
		if (pendingRequests.isEmpty()) {
			System.out.println("No pending requests to approve.");
			return false;
		}
		System.out.println("Approved " + pendingRequests.size() + " request.");
		pendingRequests.clear();
		return true;
	}

	public void assignTeacher() {
		System.out.print("Enter teacher email: ");
		String email = input.nextLine().trim();
		System.out.print("Enter course ID: ");
		String courseId = input.nextLine().trim();

		Teacher teacher = uSystem.findTeacherByEmail(email);
		Course course = uSystem.findCourse(courseId);

		if (teacher == null) {
			System.out.println("Teacher not found: " + email);
			return;
		}
		if (course == null) {
			System.out.println("Course not found: " + courseId);
			return;
		}

		if (!teacher.getCourses().contains(course)) teacher.getCourses().add(course);
		course.addInstructor(teacher);

		System.out.println("Teacher assigned to course successfully.");
	}


	public void addCourse() {
		System.out.print("Enter course ID to add (must exist in memory): ");
		String courseId = input.nextLine().trim();

		Course course = uSystem.findCourse(courseId);
		if (course == null) {
			System.out.println("Course not found. Create Course object first.");
			return;
		}
		uSystem.addCourse(course);
		System.out.println("Course added: " + course.getName());
	}

	public String generateReport() {
		long studentCount = 0;
		for (User u : uSystem.getUsers()) {
    	if (u instanceof Student) {
    	    studentCount++;
    	}
		}

		String report = """
		---Manager Report---
		Total users  : %d
		Students     : %d
		Courses      : %d
		Pending reqs : %d
		""".formatted(uSystem.getUsers().size(), studentCount, uSystem.getCourses().size(), pendingRequests.size());

		System.out.println(report);
		return report;
	}

	public void manageNews() {
		System.out.print("News title: ");
		String title = input.nextLine().trim();
		System.out.print("News content: ");
		String content = input.nextLine().trim();
		System.out.print("News topic: ");
		String topic = input.nextLine().trim();

		News news = new News(title, content, topic, this);
		uSystem.getNewsList().addNews(news);

		System.out.println("News published.");
	}

	public List<Student> viewStudentsSortedByGpa() {
		List<Student> sorted = new ArrayList<>();

		for (User u : uSystem.getUsers()) {
    		if (u instanceof Student student) {
    		    sorted.add(student);
   			}
		}	
		
		sorted.sort(Comparator.comparingDouble(Student::getGpa).reversed());

		System.out.println("--- Students sorted by GPA ---");
		for (Student s : sorted) {
			System.out.println(s.getStudentId() + " | GPA: " + String.format("%.2f", s.getGpa()));
		}
		return sorted;
	}

	@Override
	public void userMenu() {
		String menuView = """
		--Manager Panel--
		approve  - approve registration requests
		assign   - assign teacher to course
		add      - add course
		report   - generate report
		news     - manage news
		gpa      - view students sorted by GPA
		msg      - send message
		change   - change password
		info     - get info about yourself
		back     - exit panel
		""";

		System.out.println(menuView);
		String command = input.nextLine().toUpperCase();

		switch (command) {
			case "APPROVE":
				approveRegistration();
				break;
			case "ASSIGN":
				assignTeacher();
				break;
			case "ADD":
				addCourse();
				break;
			case "REPORT":
				generateReport();
				break;
			case "NEWS":
				manageNews();
				break;
			case "GPA":
				viewStudentsSortedByGpa();
				break;
			case "MSG":
				sendMessage();
				break;
			case "CHANGE":
				changePasswordInput();
				break;
			case "INFO":
				System.out.println(this);
				break;
			case "back":
				return;
			default:
				System.out.println("Not available option");
				break;
		}
	}

	@Override
	public void update(String message) {
		System.out.println("Manager notification: " + message);
	}

	@Override
	public void update() {
		System.out.println("Notification!");
	}
	
	@Override
	public String toString() {
    	return String.format("Manager[email=%s, type=%s]", getEmail(), managerType);
	}
}