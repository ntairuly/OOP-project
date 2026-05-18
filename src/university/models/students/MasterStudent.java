package university.models.students;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import university.core.UniversitySystem;
import university.exceptions.LowHIndexException;
import university.models.research.*;

public class MasterStudent extends GraduateStudent implements Researcher {

	private int maxCredits;
	private int studyYears =2;
	private List<ResearchPaper> papers;
	private List<ResearchProject> projects;

	private MasterStudent(String email, String password) {
		super(email, password);
		this.papers = new ArrayList<>();
		this.projects = new ArrayList<>();
	}

	public static MasterStudent createMasterStudent(String email, String password) {
		return new MasterStudent(email, password);
	}




	@Override
	public List<ResearchPaper> getPapers(){
		return papers;
	}

	@Override
	public void addPaper(ResearchPaper paper) {
		if (paper == null){
			throw new IllegalArgumentException("Paper must not be null");
		}
		if(!papers.contains(paper)){
			papers.add(paper);
		}
	}


	@Override
	public List<ResearchProject> getProjects() {
		return projects;
	}

	@Override
	public void joinProject(ResearchProject project) throws NotAResearcherException {
		if(project == null){
			throw new IllegalArgumentException("Project must not be null");
		}
		project.addParticipant(this);
		if(!projects.contains(project)){
			projects.add(project);
		}
	}


	@Override
	public void userMenu() {
		String menuView = """
				--Master Student Panel--
				register - register for a course
				marks    - view marks
				gpa      - view current GPA
				trans    - view transcript
				courses  - view registered courses
				rate     - rate a teacher
				-- research --
				papers   - view my papers
				hindex   - view h-index
				super    - set supervisor
				diploma  - submit diploma
				join     - join research project
				-- account --
				edit     - edit my profile (name / id)
				change   - change password
				info     - get info about yourself
				logout   - to get back
				""";
		System.out.println(menuView);


		String command = input.nextLine().trim().toLowerCase();
		switch (command) {

			case "register":
				registerForCourseInput();
				break;
			case "marks":
				viewMarks();
				break;
			case "gpa":
				System.out.println("Current GPA: " + String.format("%.2f", getGpa()));
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
			case "papers":
				viewPapers();
				break;
			case "hindex":
				System.out.println("Your h-index: " + calculateHIndex());
				break;
			case "super":
				setSupervisorInput();
				break;
			case "diploma":
				submitDiplomaInput();
				break;
			case "join":
				joinProjectInput();
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
				System.out.println("Not available option");
				break;
		}

	}


	private void viewPapers(){
		if (papers.isEmpty()) {
			System.out.println("No papers yet");
			return;
		}
		System.out.println("--- My papers ---");
		printPapers(Comparator.comparingInt(ResearchPaper::getCitations).reversed());

	}




	private void setSupervisorInput() {
		System.out.print("Enter supervisor email: ");
		String email = input.nextLine().trim();
		var user = UniversitySystem.getInstance().findUserByEmail(email);
		if (user == null) {
			System.out.println("User not found: " + email);
			return;
		}
		if (!(user instanceof Researcher)) {
			System.out.println("This user is not a Researcher and cannot be your supervisor");
			return;
		}
		try {
			setSupervisor((Researcher) user);
			System.out.println("Supervisor set: " + email);
		} catch (LowHIndexException e) {
			System.out.println("Cannot set supervisor: " + e.getMessage());
		}
	}

	private void submitDiplomaInput() {
		if (getSupervisor() == null) {
			System.out.println("You need a supervisor before submitting a diploma");
			return;
		}
		System.out.print("Enter diploma title: ");
		String title = input.nextLine().trim();
		System.out.print("Enter diploma topic: ");
		String topic = input.nextLine().trim();
		if (papers.isEmpty()) {
			System.out.println("You need at least one published paper to submit a diploma");
			return;
		}
		try {
			DiplomaProject project = new DiplomaProject(
					title, topic, this, getSupervisor(), new ArrayList<>(papers));
			submitDiploma(project);
			System.out.println("Diploma submitted: " + title);
		} catch (IllegalStateException e) {
			System.out.println("Cannot submit: " + e.getMessage());
		}
	}


	private void joinProjectInput() {
		System.out.print("Enter project title: ");
		String title = input.nextLine().trim();
		ResearchProject project = UniversitySystem.getInstance().findProject(title);
		if (project == null) {
			System.out.println("Project not found: " + title);
			return;
		}
		try {
			joinProject(project);
			System.out.println("Joined project: " + project.getTitle());
		} catch (NotAResearcherException e) {
			System.out.println("Cannot join: " + e.getMessage());
		}
	}


	// Уведомления

	@Override
	public void update() {
		System.out.println("Master Student " + getStudentId() + " received notification");
	}

	@Override
	public void update(String message) {
		System.out.println("Master Student " + getStudentId() + " received: " + message);
	}
}