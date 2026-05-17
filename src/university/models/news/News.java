package university.models.news;

import java.time.LocalDate;
import java.util.List;
import university.core.*;

public class News {

	private String title;
	private String content;
	private String topic;
	private boolean isPinned;
	private List<String> сomments;
	private LocalDate publishedDate;
	private User author;

	public void addComment() {
		// TODO - implement News.addComment
		throw new UnsupportedOperationException();
	}

	public int compareTo() {
		// TODO - implement News.compareTo
		throw new UnsupportedOperationException();
	}

	public News createResearchNews() {
		// TODO - implement News.createResearchNews
		throw new UnsupportedOperationException();
	}

}