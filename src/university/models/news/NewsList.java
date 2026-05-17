package university.models.news;

import java.util.ArrayList;
import java.util.List;

public class NewsList {
    private List<News> newsList = new ArrayList<>();
    private List<Notifiable> subscribers = new ArrayList<>();

    public void addSubscriber(Notifiable user) {
        subscribers.add(user);
    }

    public void removeSubscriber(Notifiable user) {
        subscribers.remove(user);
    }

    public void addNews(News news) {
        newsList.add(news);
        notifyAllSubscribers("Aded news: " + news.getTitle());
    }

    private void notifyAllSubscribers(String msg) {
        for (Notifiable n : subscribers) {
            n.update(msg);
        }
    }

    public List<News> getAllNews() {
        return newsList;
    }
}