package university;

import university.core.*;
import university.core.factory.*;

public class Main {
    public static void main(String[] args) {
        
        UniversitySystem uSystem = UniversitySystem.getInstance();
        UserFactory factory = AdminFactory.createFactory(uSystem);
        factory.addUser("admin@kbtu.kz", "password");
        uSystem.start();
    }
}