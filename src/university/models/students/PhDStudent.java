package university.models.students;

import java.util.List;
import university.models.research.*;

public class PhDStudent extends GraduateStudent implements Researcher {

	private int studyYears;
	private String dissertationTopic;
	private boolean canTeachBachelor;

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