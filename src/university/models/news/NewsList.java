package university.models.news;

import java.util.ArrayList;
import java.util.List;


public class NewsList {
    private List<News> newsList = new ArrayList<>();
    private List<Notifiable> subscribers = new ArrayList<>();

    public void addSubscriber(Notifiable user) {
        if (!subscribers.contains(user)) {
            subscribers.add(user);
        }
    }

    public void removeSubscriber(Notifiable user) {
        subscribers.remove(user);
    }

    public void addNews(News news) {
        if (news == null) return;
        newsList.add(news);
        notifyAllSubscribers("Added news: " + news.getTitle());
    }

    private void notifyAllSubscribers(String msg) {
        for (Notifiable n : subscribers) {
            n.update(msg);
        }
    }

    public List<News> getAllNews() { 
        return newsList; 
    }
    public List<Notifiable> getSubscribers() { 
        return subscribers; 
    }
}