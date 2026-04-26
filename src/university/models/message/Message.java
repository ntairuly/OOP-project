package university.models.message;

import java.time.LocalDateTime;
import university.core.*;

public class Message {

	private Employee sender;
	private Employee receiver;
	private String content;
	private String subject;
	private UrgencyLevel urgency;
	private LocalDateTime sentDate;
	private boolean isSignedByDean;
	
}