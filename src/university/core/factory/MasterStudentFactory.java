package university.core.factory;

import university.core.UniversitySystem;
import university.core.User;
import university.models.students.MasterStudent;

public class MasterStudentFactory extends UserFactory {

    public MasterStudentFactory(UniversitySystem uSystem) {
        super(uSystem);
    }

    @Override
    protected User createUser(String email, String password) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password too short");
        }

        MasterStudent student = MasterStudent.createMasterStudent(email, password);
        student.setStudentId(generateId());
        student.setYear(1);
        student.setMajor("Undeclared");
        return student;
    }

    private String generateId() {
        int year = java.time.LocalDate.now().getYear() % 100;
        String randomNum = String.format("%06d", (int)(Math.random() * 1000000));
        return year + "M" + randomNum;
    }
}