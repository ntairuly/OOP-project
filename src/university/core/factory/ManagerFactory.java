package university.core.factory;

import university.core.*;
import university.models.employee.*;

public class ManagerFactory extends UserFactory {

    protected ManagerFactory(UniversitySystem uSystem) {
        super(uSystem);
    }

    public static ManagerFactory createFactory(UniversitySystem uSystem) {
        return new ManagerFactory(uSystem);
    }

    @Override
    protected User createUser(String email, String password) {
        return Manager.createManager(email, password);
    }
}