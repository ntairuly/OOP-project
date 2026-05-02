package university.models.research;

import java.util.Comparator;
import java.util.List;

public interface Researcher {

    List<ResearchPaper> getPapers();
    void addPaper(ResearchPaper paper);
    List<ResearchProject> getProjects();
    void joinProject(ResearchProject project) throws NotAResearcherException;

    default void printPapers(Comparator<ResearchPaper> comparator) {
        getPapers().stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }

    default int calculateHIndex() {
        List<ResearchPaper> papers = getPapers();
        int[] citations = papers.stream()
                .mapToInt(ResearchPaper::getCitations)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        int h = 0;
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= i + 1) h = i + 1;
            else break;
        }
        return h;
    }
}
