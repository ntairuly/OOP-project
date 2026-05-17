package university.models.research;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResearchProject {

    private String topic;
    private String title;
    private List<Researcher> participants;
    private List<ResearchPaper> publishedPapers;
    private LocalDate startDate;

    public ResearchProject(String title, String topic) {
        this.title = title;
        this.topic = topic;
        this.participants = new ArrayList<>();
        this.publishedPapers = new ArrayList<>();
        this.startDate = LocalDate.now();
    }

    public void addParticipant(Object person) throws NotAResearcherException {
        if (!(person instanceof Researcher)) {
            throw new NotAResearcherException(person.toString());
        }
        participants.add((Researcher) person);
    }

    public void publishPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    public List<ResearchPaper> getPublishedPapers() { return publishedPapers; }
    public List<Researcher> getParticipants() { return participants; }
    public String getTopic() { return topic; }
    public String getTitle() { return title; }

    @Override
    public String toString() {
        return "ResearchProject{title='" + title + "', topic='" + topic
                + "', participants=" + participants.size()
                + ", papers=" + publishedPapers.size() + "}";
    }
}
