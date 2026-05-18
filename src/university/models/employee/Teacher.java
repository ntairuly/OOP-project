package university.models.employee;

import java.util.ArrayList;
import java.util.List;

import university.core.*;
import university.models.courses.*;
import university.models.grading.Mark;
import university.models.message.Message;
import university.models.message.UrgencyLevel;
import university.models.research.*;
import university.models.students.*;


public class Teacher extends Employee implements Researcher {

	private TeacherTitle title;
	private List<Course> courses;
	private double rating;
	private boolean isProfessor;
	private List<Integer> ratings = new ArrayList<>();
	UniversitySystem uSystem = UniversitySystem.getInstance();

	private List<ResearchPaper> papers = new ArrayList<>();
	private List<ResearchProject> projects = new ArrayList<>();

	protected Teacher(String email, String password) {
		super(email, password);
		this.courses = new ArrayList<>();
		this.ratings = new ArrayList<>();
	}

	public static Teacher createTeacher(String email, String password) {
		return new Teacher(email, password);
	}

	public TeacherTitle getTitle() { return title; }
	public void setTitle(TeacherTitle title) { this.title = title; }

	public List<Course> getCourses() { return courses; }

	public double getRating() { return rating; }
	public boolean isProfessor() { return isProfessor; }
	public void setProfessor(boolean isProfessor) { this.isProfessor = isProfessor; }

	public void putMark() {
		System.out.print("Enter student ID: ");
		String studentId = input.nextLine().trim();
		System.out.print("Enter course ID: ");
		String courseId = input.nextLine().trim();

		Student student = uSystem.findStudentById(studentId);
		Course course = uSystem.findCourse(courseId);

		if (student == null || course == null) {
			System.out.println("Student or course not found.");
			return;
		}
		if (!courses.contains(course)) {
			System.out.println("You are not assigned to this course.");
			return;
		}

		System.out.print("First attestation (0-30): ");
		double a1 = Double.parseDouble(input.nextLine().trim());
		System.out.print("Second attestation (0-30): ");
		double a2 = Double.parseDouble(input.nextLine().trim());
		System.out.print("Final exam (0-40): ");
		double fin = Double.parseDouble(input.nextLine().trim());

		Mark mark = new Mark(student, course, a1, a2, fin);
		student.addMark(course, mark);
		System.out.println("Mark added: " + mark);
	}

	public void sendComplaint() {
		System.out.print("Complaint receiver email: ");
		String email = input.nextLine().trim();

		User receiver = uSystem.findUserByEmail(email);
		if (receiver == null) {
			System.out.println("User not found: " + email);
			return;
		}

		System.out.print("Subject: ");
		String subject = input.nextLine().trim();
		System.out.print("Complaint text: ");
		String content = input.nextLine().trim();

		Message msg = new Message(this, receiver, subject, content, UrgencyLevel.HIGH);
		uSystem.addMessage(msg);
		receiver.update("New complaint: " + msg.toString());

		System.out.println("Complaint sent.");
	}

	public List<Student> viewStudents() {
		List<Student> result = new ArrayList<>();
		for (Course c : courses) {
			for (Student s : c.getEnrolledStudents()) {
				if (!result.contains(s)) result.add(s);
			}
		}
		System.out.println("--- My students ---");
		for (Student s : result) {
			System.out.println(s);
		}
		return result;
	}

	public String getTranscript() {
		System.out.print("Enter student ID: ");
		String studentId = input.nextLine().trim();

		Student s = uSystem.findStudentById(studentId);
		if (s == null) {
			return "Student not found.";
		}
		return s.getTranscript();
	}

	public void addRating(int rating) {
		if (rating < 1 || rating > 5) { 
			return;
		}
		ratings.add(rating);
		double sum = 0;
		for (int r : ratings) sum += r;
		this.rating = sum / ratings.size();
	}

	// Researcher methods
	@Override
	public List<ResearchPaper> getPapers() { 
		return papers; 
	}

	@Override
	public void addPaper(ResearchPaper paper) {
		if (paper != null && !papers.contains(paper)) papers.add(paper);
	}

	@Override
	public List<ResearchProject> getProjects() { 
		return projects; 
	}

	@Override
	public void joinProject(ResearchProject project) throws NotAResearcherException {
		if (project == null) return;
		project.addParticipant(this);
		if (!projects.contains(project)) projects.add(project);
	}

	@Override
	public void update(String message) {
		System.out.println("Teacher notification: " + message);
	}
}