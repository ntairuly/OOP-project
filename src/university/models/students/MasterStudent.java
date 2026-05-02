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