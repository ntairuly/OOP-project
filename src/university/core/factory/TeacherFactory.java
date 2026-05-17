package university.core.factory;

import university.core.*;
import university.models.employee.*;

public class TeacherFactory extends UserFactory {

    protected TeacherFactory(UniversitySystem uSystem) {
        super(uSystem);
    }

    public static TeacherFactory createFactory(UniversitySystem uSystem) {
        return new TeacherFactory(uSystem);
    }

    @Override
    protected User createUser(String email, String password) {
        return Teacher.createTeacher(email, password);
    }
}