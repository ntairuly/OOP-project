package university.models.other;

import java.util.ArrayList;
import java.util.List;
import university.models.students.*;

public class StudentOrganization {

    private String name;
    private Student head;
    private List<Student> members;

    public StudentOrganization(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addMember(Student student) {
        if (!members.contains(student)) {
            members.add(student);
        }
    }

    public void removeMember(Student student) {
        members.remove(student);
    }

    public void setHead(Student student) {
        if (!members.contains(student)) {
            members.add(student);
        }
        this.head = student;
    }

    public String getName() { return name; }
    public Student getHead() { return head; }
    public List<Student> getMembers() { return members; }

    @Override
    public String toString() {
        return "StudentOrganization{name='" + name + "', head=" + head
                + ", members=" + members.size() + "}";
    }
}
