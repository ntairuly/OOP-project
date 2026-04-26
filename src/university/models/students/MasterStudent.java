package university.models.students;

import java.util.List;
import university.models.research.*;

public class MasterStudent extends GraduateStudent implements Researcher {

	private int maxCredits;
	private int studyYears;
	private List<ResearchPaper> papers;
	private List<ResearchProject> projects;

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