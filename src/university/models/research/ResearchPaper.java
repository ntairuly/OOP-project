package university.models.research;

import java.time.LocalDate;
import java.util.List;

public class ResearchPaper {

	private String title;
	private List<String> authors;
	private String journal;
	private int pages;
	private int citations;
	private LocalDate publishedDate;

	public String getCitation() {
		// TODO - implement ResearchPaper.getCitation
		throw new UnsupportedOperationException();
	}

	public int compareTo() {
		// TODO - implement ResearchPaper.compareTo
		throw new UnsupportedOperationException();
	}

}