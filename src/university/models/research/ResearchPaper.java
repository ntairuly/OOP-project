package university.models.research;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class ResearchPaper implements Serializable, Comparable<ResearchPaper> {

    private String title;
    private List<String> authors;
    private String journal;
    private int pages;
    private int citations;
    private LocalDate publishedDate;
    private String doi;

    public ResearchPaper(String title, List<String> authors, String journal,
                         int pages, int citations, LocalDate publishedDate, String doi) {
        this.title = title;
        this.authors = authors;
        this.journal = journal;
        this.pages = pages;
        this.citations = citations;
        this.publishedDate = publishedDate;
        this.doi = doi;
    }

    public String getCitation(CitationFormat format) {
        if (format == CitationFormat.PLAIN_TEXT) {
            return String.join(", ", authors) + ". \"" + title + "\". "
                    + journal + ". " + publishedDate.getYear() + ". DOI: " + doi;
        } else {
            return "@article{" + doi + ",\n"
                    + "  title={" + title + "},\n"
                    + "  author={" + String.join(" and ", authors) + "},\n"
                    + "  journal={" + journal + "},\n"
                    + "  year={" + publishedDate.getYear() + "},\n"
                    + "  doi={" + doi + "}\n"
                    + "}";
        }
    }

    @Override
    public int compareTo(ResearchPaper other) {
        return Integer.compare(other.citations, this.citations);
    }

    @Override
    public String toString() {
        return "\"" + title + "\" | citations: " + citations
                + " | " + publishedDate.getYear() + " | pages: " + pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchPaper)) return false;
        ResearchPaper p = (ResearchPaper) o;
        return doi.equals(p.doi);
    }

    @Override
    public int hashCode() {
        return doi.hashCode();
    }

    public String getTitle() { return title; }
    public int getCitations() { return citations; }
    public LocalDate getPublishedDate() { return publishedDate; }
    public int getPages() { return pages; }
    public List<String> getAuthors() { return authors; }
    public String getJournal() { return journal; }
    public String getDoi() { return doi; }
}
