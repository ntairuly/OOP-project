package university.models.students;

import java.util.ArrayList;
import java.util.List;
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
	public void update() {
		System.out.println("Master Student "+ getStudentId()+" received notification");
	}
}