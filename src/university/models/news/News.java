package university.models.news;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import university.core.*;

public class News {

	private String title;
	private String content;
	private String topic;
	private boolean isPinned;
	private List<String> comments;
	private LocalDate publishedDate;
	private User author;

	public News() {
        this.comments = new ArrayList<>();
    }

	public void addComment(String comment) {
		comments.add(comment);
		throw new UnsupportedOperationException();
	}

	public int compareTo(News other) {
		if (this.isPinned && !other.isPinned) {
			return -1;
		}
		else if (!this.isPinned && other.isPinned) {
			return 1;
		}
		else{
			return other.publishedDate.compareTo(this.publishedDate);//Built in LocalDate comparing
		}
	}

	public News createResearchNews(String title, String content, String topic, User author) {
		News news = new News();
        news.title = title;
        news.content = content;
        news.topic = topic;
        news.isPinned = false;
        news.publishedDate = LocalDate.now();
        news.author = author;
        return news;
	}

	public String commentsToString(){
		String stringComments = "";
		for (String comment : comments) {
			stringComments += " -- " + comment;
		}
		return stringComments;
	}

	@Override
    public String toString() {
        return "{" + topic + "}\n      " + title + " (" + publishedDate + ")\n" + content + commentsToString();
    }
}