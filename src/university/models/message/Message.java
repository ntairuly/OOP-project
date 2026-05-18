package university.models.message;

import java.time.LocalDateTime;
import java.util.Objects;
import university.core.*;
import university.models.employee.*;

public class Message {

    private Employee sender;
    private User receiver;
    private String content;
    private String subject;
    private UrgencyLevel urgency;
    private LocalDateTime sentDate;
    private boolean isSignedByDean;

    public Message(Employee sender, User receiver, String subject,
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
    public User getReceiver() { return receiver; }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(sender, message.sender) &&
                Objects.equals(receiver, message.receiver) &&
                Objects.equals(subject, message.subject) &&
                Objects.equals(sentDate, message.sentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, subject, sentDate);
    }
}
