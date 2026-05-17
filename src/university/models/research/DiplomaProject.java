package university.models.research;

import java.util.List;
import university.models.students.*;

public class DiplomaProject {

	private String title;
	private String topic;
	private GraduateStudent student;
	private Researcher supervisor;
	private List<ResearchPaper> publishedPapers;
	public DiplomaProject(String title, String topic, GraduateStudent student, Researcher supervisor,List<ResearchPaper> publishedPapers){
		this.title = title;
		this.topic= topic;
		this.student = student;
		this.supervisor =supervisor;
		this.publishedPapers= publishedPapers;
	}

	public List<ResearchPaper> getResearchPapers(){
		return publishedPapers;
	}

	@Override
	public String toString() {
		return String.format("DiplomaProject{title='%s',papers=%d}",title,publishedPapers.size());
	}
}