package university.models.research;

public class NotAResearcherException extends Exception {
    public NotAResearcherException(String name) {
        super(name + " is not a Researcher and cannot join a ResearchProject");
    }
}
