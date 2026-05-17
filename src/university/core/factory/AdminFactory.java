package university.core.factory;

import university.core.*;

public class AdminFactory extends UserFactory {
    protected AdminFactory(UniversitySystem uSystem) {
        super(uSystem);
    }


    public static AdminFactory createFactory(UniversitySystem uSystem) {
        return new AdminFactory(uSystem);
    }

    @Override
    protected  User createUser(String email, String password){
        return Admin.createAdmin(email, password); 
    }
}