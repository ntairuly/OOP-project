package university.models.students;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import university.core.UniversitySystem;
import university.exceptions.LowHIndexException;
import university.models.research.*;
public class PhDStudent extends GraduateStudent implements Researcher {

	private int studyYears = 3;
	private String dissertationTopic;
	private boolean canTeachBachelor = true;
	private boolean dissertationSubmitted = false;
	private List<ResearchPaper> papers;
	private List<ResearchProject> projects;



	private PhDStudent(String email, String password) {
		super(email, password);
		this.papers = new ArrayList<>();
		this.projects = new ArrayList<>();
	}

	public static PhDStudent createPhDStudent(String email, String password) {
		return new PhDStudent(email, password);
	}


	//Getters


	public int getStudyYears() {
		return studyYears;
	}
	public String getDissertationTopic() {
		return dissertationTopic;
	}
	public void setDissertationTopic(String dissertationTopic) {
		this.dissertationTopic=dissertationTopic;
	}

	public boolean canTeachBachelor() {
		return canTeachBachelor;
	}

	public void setCanTeachBachelor(boolean canTeachBachelor) {
		this.canTeachBachelor = canTeachBachelor;
	}

	public boolean isDissertationSubmitted(){
		return dissertationSubmitted;
	}


	//PHDs actions
	public void submitDissertation() {
		if (dissertationTopic == null || dissertationTopic.isBlank()){
			throw new IllegalStateException("Dissertation topic must be set first");
		}
		if (papers.isEmpty()){
			throw new IllegalStateException("Dissertation cannot submit  without published papers");
		}

		if(dissertationSubmitted){
			throw new IllegalStateException("Dissertation already submitted");
		}
		this.dissertationSubmitted=true;
		System.out.println("PhD Student " + getStudentId()
				+ " submitted dissertation: " + dissertationTopic);
	}



	public void publishPaper(ResearchPaper paper) {
		addPaper(paper);
		System.out.println("PhD Student " + getStudentId()+ " published paper: " + paper.getTitle());
	}




	//Researcher

	@Override
	public List<ResearchPaper> getPapers(){
		return papers;
	}

	@Override
	public void addPaper(ResearchPaper paper) {
		if(paper == null){
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
                __PhD Student Panel__
                register - register for a course
                marks    - view marks
                gpa      - view current GPA
                trans    - view transcript
                courses  - view registered courses
                rate     - rate a teacher
                ___research___
                papers   - view my papers
                hindex   - view h-index
                super    - set supervisor
                join     - join research project
                publish  - publish a new paper
                topic    - set dissertation topic
                defend   - submit dissertation
                teaching - toggle teaching bachelors
                ___account___
                edit     - edit my profile (name / id)
                change   - change password
                info     - get info about yourself
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
			case "join":
				joinProjectInput();
				break;
			case "publish":
				publishPaperInput();
				break;
			case "topic":
				setDissertationTopicInput();
				break;
			case "defend":
				defendDissertation();
				break;
			case "teaching":
				toggleTeaching();
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
			default:
				System.out.println("Not available option");
				break;
		}
	}

	private void viewPapers() {
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

	private void publishPaperInput() {
		System.out.print("Enter paper title: ");
		String title = input.nextLine().trim();
		System.out.print("Enter journal name: ");
		String journal = input.nextLine().trim();
		System.out.print("Enter DOI: ");
		String doi = input.nextLine().trim();
		System.out.print("Enter pages: ");
		int pages;
		try {
			pages = Integer.parseInt(input.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Invalid pages, expected a number");
			return;
		}
		List<String> authors = new ArrayList<>();
		authors.add(getEmail());
		ResearchPaper paper = new ResearchPaper(
				title, authors, journal, pages, 0, java.time.LocalDate.now(), doi);
		publishPaper(paper);
	}


	private void setDissertationTopicInput() {
		System.out.print("Enter dissertation topic: ");
		String topic = input.nextLine().trim();
		if (topic.isBlank()) {
			System.out.println("Topic cannot be empty");
			return;
		}
		setDissertationTopic(topic);
		System.out.println("Dissertation topic set: " + topic);
	}

	private void defendDissertation() {
		try {
			submitDissertation();
		} catch (IllegalStateException e) {
			System.out.println("Cannot defend: " + e.getMessage());
		}
	}


	private void toggleTeaching() {
		canTeachBachelor = !canTeachBachelor;
		System.out.println("Teaching bachelors: " + (canTeachBachelor ? "enabled" : "disabled"));
	}




	//Notifiable
	@Override
	public void update() {
		System.out.println("PhD Student " + getStudentId() + " received notification");
	}

	@Override
	public void update(String message) {
		System.out.println("PhD Student " + getStudentId() + " received: " + message);
	}

}