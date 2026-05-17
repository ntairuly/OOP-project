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

    public Message(Employee sender, Employee receiver, String subject,
                   String content, UrgencyLevel urgency) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.urgency = urgency;
        this.sentDate = LocalDateTime.now();
        this.isSignedByDean = false;
    }

    public void signByDean() {
        this.isSignedByDean = true;
    }

    public Employee getSender() { return sender; }
    public Employee getReceiver() { return receiver; }
    public String getContent() { return content; }
    public String getSubject() { return subject; }
    public UrgencyLevel getUrgency() { return urgency; }
    public boolean isSignedByDean() { return isSignedByDean; }

    @Override
    public String toString() {
        return "Message{from=" + sender + ", to=" + receiver
                + ", subject='" + subject + "', urgency=" + urgency
                + ", signed=" + isSignedByDean + "}";
    }
}
