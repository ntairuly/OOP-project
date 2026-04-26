package university.models.research;

import java.util.List;

public interface Researcher {

	void printPapers();

	int calculateHIndex();

	void addPaper();

	List<ResearchPaper> getPapers();

	List<ResearchProject> getProjects();

	void joinProject();

}