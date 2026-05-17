package university.models.news;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import university.core.*;

public class News implements Comparable<News> {

    private String title;
    private String content;
    private String topic;
    private boolean isPinned;
    private List<String> comments;
    private LocalDate publishedDate;
    private User author;

    public News(String title, String content, String topic, User author) {
        this.title = title;
        this.content = content;
        this.topic = topic;
        this.author = author;
        this.publishedDate = LocalDate.now();
        this.comments = new ArrayList<>();
        this.isPinned = topic.equalsIgnoreCase("Research");
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    @Override
    public int compareTo(News other) {
        if (this.isPinned && !other.isPinned) return -1;
        if (!this.isPinned && other.isPinned) return 1;
        return other.publishedDate.compareTo(this.publishedDate);
    }

    public static News createResearchNews(String researcherName, String paperTitle) {
        return new News(
            "New paper published!",
            researcherName + " published: " + paperTitle,
            "Research",
            null
        );
    }

    public String getTitle() { return title; }
    public String getTopic() { return topic; }
    public boolean isPinned() { return isPinned; }
    public List<String> getComments() { return comments; }

    @Override
    public String toString() {
        return "News{title='" + title + "', topic='" + topic
                + "', pinned=" + isPinned
                + ", date=" + publishedDate + "}";
    }
}
