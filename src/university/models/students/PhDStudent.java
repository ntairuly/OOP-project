package university.models.students;

import java.util.List;

import university.models.courses.Course;
import university.models.grading.Mark;
import university.models.research.*;
import java.util.ArrayList;
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

	public boolean CanTeachBachelor() {
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


	//Notifiable
	@Override
	public void update() {
		System.out.println("PhD Student " + getStudentId() + " received notification");
	}
}