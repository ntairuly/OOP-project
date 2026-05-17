package university.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
        Admin.createSuperAdmin(this);
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
        for (User u : users){
            if (u instanceof Student){
                Student s = (Student) u;
                if(studentId.equalsIgnoreCase(s.getStudentId())){
                    return s;
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
        if (course != null && !courses.contains(course)) {
            courses.add(course);
        }
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
        System.out.println(Language.INSTANCE.get("UniversitySystem.menuTitle"));
        System.out.println(Language.INSTANCE.get("UniversitySystem.menuOptions"));
        System.out.println(Language.INSTANCE.get("UniversitySystem.optionProfile"));
        System.out.println(Language.INSTANCE.get("UniversitySystem.optionPassword"));
        System.out.println(Language.INSTANCE.get("UniversitySystem.optionLogout"));
        System.out.print(Language.INSTANCE.get("UniversitySystem.chooseOption") + " ");
        String option = input.nextLine(); 
        if (option.equals("1")){
            System.out.println(this.myUser);
        } else if (option.equals("2")){
            myUser.changePasswordInput();
        } else if (option.equals("3")){
            logout();
        } else {
            System.out.println(("UniversitySystem.invalidOption"));
        }
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