package university.models.students;

import java.util.List;
import university.models.research.*;
import java.util.ArrayList;
public class PhDStudent extends GraduateStudent implements Researcher {

	private int studyYears = 3;
	private String dissertationTopic;
	private boolean canTeachBachelor = true;
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


	public void setDissertationTopic(String dissertationTopic) {
		this.dissertationTopic = dissertationTopic;
	}

	public void submitDissertation() {
		// TODO - implement PhDStudent.submitDissertation
		throw new UnsupportedOperationException();
	}

	public void publishPaper() {
		// TODO - implement PhDStudent.publishPaper
		throw new UnsupportedOperationException();
	}

	@Override
	public void printPapers() {
		// TODO - implement MasterStudent.printPapers
		throw new UnsupportedOperationException();
	}

	@Override
	public int calculateHIndex() {
		// TODO - implement MasterStudent.calculateHIndex
		throw new UnsupportedOperationException();
	}

	@Override
	public void addPaper(){
		throw new UnsupportedOperationException();
	}

	@Override
	public List<ResearchPaper> getPapers(){
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void joinProject() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<ResearchProject> getProjects() {
		throw new UnsupportedOperationException();
	}
}