package university.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import university.core.factory.*;
import university.models.courses.Course;
import university.models.employee.Teacher;
import university.models.message.Message;
import university.models.news.NewsList;
import university.models.other.Language;
import university.models.research.ResearchProject;
import university.models.students.Student;


public class UniversitySystem {
    private static UniversitySystem obj;

    private boolean isRunning = true;
    private boolean isLoggedIn = false;
    protected List<User> users = new ArrayList<>();
    protected List<Course> courses = new ArrayList<>();
    protected List<ResearchProject> researchProjects = new ArrayList<>();
    protected List<Message> messages = new ArrayList<>();
    private final NewsList newsList = new NewsList();
    private String myEmail;
    private String myPassword;
    private User myUser;
    static Scanner input = new Scanner(System.in);

    protected UniversitySystem() {
        obj = this;
        Admin.createSuperAdmin(this);

        AdminFactory adminFactory = AdminFactory.createFactory(this);
        adminFactory.addUser("admin1@kbtu.kz", "AdminPass123");
        adminFactory.addUser("admin2@kbtu.kz", "AdminPass456");

        ManagerFactory managerFactory = ManagerFactory.createFactory(this);
        managerFactory.addUser("manager1@kbtu.kz", "ManagerPass123");
        managerFactory.addUser("manager2@kbtu.kz", "ManagerPass456");

        TeacherFactory teacherFactory = TeacherFactory.createFactory(this);
        teacherFactory.addUser("teacher1@kbtu.kz", "TeacherPass123");
        teacherFactory.addUser("teacher2@kbtu.kz", "TeacherPass456");

        StudentFactory studentFactory = StudentFactory.createFactory(this);
        studentFactory.addUser("student1@kbtu.kz", "StudentPass123");
        studentFactory.addUser("student2@kbtu.kz", "StudentPass456");

        MasterStudentFactory masterStudentFactory = MasterStudentFactory.createFactory(this);
        masterStudentFactory.addUser("mStudent1@kbtu.kz", "StudentPass123");
        masterStudentFactory.addUser("mStudent2@kbtu.kz", "StudentPass456");

        PHDStudentFactory phdStudentFactory = PHDStudentFactory.createFactory(this);
        phdStudentFactory.addUser("phdStudent1@kbtu.kz", "StudentPass123");
        phdStudentFactory.addUser("phdStudent2@kbtu.kz", "StudentPass456");
    }

    // Getters
    public List<User> getUsers() { 
        return users; 
    }
    public List<Course> getCourses() { 
        return courses; 
    }
    public List<ResearchProject> getResearchProjects() { 
        return researchProjects; 
    }
    public List<Message> getMessages() { 
        return messages; 
    }
    public NewsList getNewsList() { 
        return newsList; 
    }


    public static synchronized UniversitySystem getInstance() {
        if (obj == null) obj = new UniversitySystem();
        return obj;
    }

    public ResearchProject findProject(String title){
        if(title == null){
            return null;
        }
        for (ResearchProject p : researchProjects) {
            if (title.equalsIgnoreCase(p.getTitle())) {
                return p;
            }
        }
        return null;
    }

    public void addResearchProject(ResearchProject project) {
        if (project != null && !researchProjects.contains(project)) {
            researchProjects.add(project);
        }
    }

    public User findUserByEmail(String email) {
        if (email == null) return null;
        if (!email.endsWith("@kbtu.kz")) {
            email+= "@kbtu.kz";
        }
        for (User u :users) {
            if (u.getEmail().equalsIgnoreCase(email)){
                return u;
            }
        }
        return null;
    }

    public Student findStudentById(String studentId){
        if(studentId == null){
            return null;
        }
        for (User user : users){
            if (user instanceof Student){
                Student student = (Student) user;
                if(studentId.equalsIgnoreCase(student.getStudentId())){
                    return student;
                }
            }
        }
        return null;
    }


    public Teacher findTeacherByEmail(String email) {
        User u = findUserByEmail(email);
        return (u instanceof Teacher) ? (Teacher) u : null;
    }

    public Course findCourse(String courseId) {
        if (courseId == null) return null;
        for (Course c : courses) {
            if (courseId.equalsIgnoreCase(c.getCourseId())) {
                return c;
            }
        }
        return null;
    }

    public void addCourse(Course course) {
        if (!courses.contains(course)){
            courses.add(course);
        }
    }

    public void addMessage(Message m) {
        messages.add(m);
    }


    public void start(){
        while(isRunning){
            if (isLoggedIn){
                mainMenu();
            } else {
                loginMenu();
            }
        }
    }


    //Login and main menu
    private void loginMenu() {
        System.out.print(Language.INSTANCE.get("UniversitySystem.getEmail"));
        myEmail = input.nextLine();

        System.out.print(Language.INSTANCE.get("UniversitySystem.getPw"));
        myPassword = input.nextLine();

        if (!(myEmail.endsWith("@kbtu.kz"))) {
            myEmail += "@kbtu.kz";
        }

        isLoggedIn = login(myEmail, myPassword);

        if (isLoggedIn) {
            System.out.println(Language.INSTANCE.get("UniversitySystem.successlog"));
            while (myUser.isFirstLogin) {
                System.out.println(Language.INSTANCE.get("UniversitySystem.pwChange"));
                myUser.changePasswordInput();
            }
        } else {
            System.out.println(Language.INSTANCE.get("UniversitySystem.logError"));
        }
            }

    private void mainMenu(){
        myUser.userMenu();
    }

    // Login and logout logic
    private boolean login(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equals(email) && u.checkPassword(password)) {
                myUser = u;
                return true;
            }
        }
        return false;
    }

    private void logout() {
        System.out.println(Language.INSTANCE.get("UniversitySystem.logout"));
        isLoggedIn = false;
        myEmail = "";
        myPassword = "";
        myUser = null;
    }
}