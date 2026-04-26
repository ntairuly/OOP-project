package university.models.research;

import java.time.LocalDate;
import java.util.List;

public class ResearchProject {

	private String topic;
	private String title;
	private List<Researcher> participants;
	private List<ResearchPaper> publishedPapers;
	private LocalDate startDate;

	public void addParticipant() {
		// TODO - implement ResearchProject.addParticipant
		throw new UnsupportedOperationException();
	}

	public void publishPaper() {
		// TODO - implement ResearchProject.publishPaper
		throw new UnsupportedOperationException();
	}

}