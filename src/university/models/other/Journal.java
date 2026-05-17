package university.models.other;

import java.util.List;
import university.models.news.*;
import university.models.research.*;

public class Journal {

	private String name;
	private List<ResearchPaper> papers;
	private List<Notifiable> subscribers;

	public void subscribe() {
		// TODO - implement Journal.subscribe
		throw new UnsupportedOperationException();
	}

	public void unsubscribe() {
		// TODO - implement Journal.unsubscribe
		throw new UnsupportedOperationException();
	}

	public void publishPaper() {
		// TODO - implement Journal.publishPaper
		throw new UnsupportedOperationException();
	}

	//notifyAll cant be name for method
	//because it is special built-in method
	//so i renamed it
	public void sendNotifications() {
		// TODO - implement Journal.notifyAll
		throw new UnsupportedOperationException();
	}

}