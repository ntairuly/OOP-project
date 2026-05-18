package university.models.other;

import java.util.ArrayList;
import java.util.List;
import university.models.news.*;
import university.models.research.*;

public class Journal {

    private String name;
    private List<ResearchPaper> papers;
    private List<Notifiable> subscribers;

    public Journal(String name) {
        this.name = name;
        this.papers = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public void subscribe(Notifiable subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Notifiable subscriber) {
        subscribers.remove(subscriber);
    }

    public void publishPaper(ResearchPaper paper) {
        papers.add(paper);
        sendNotifications();
    }

    public void sendNotifications() {
        for (Notifiable subscriber : subscribers) {
            subscriber.update("");
        }
    }

    public String getName() { return name; }
    public List<ResearchPaper> getPapers() { return papers; }

    @Override
    public String toString() {
        return "Journal{name='" + name + "', papers=" + papers.size()
                + ", subscribers=" + subscribers.size() + "}";
    }
}
