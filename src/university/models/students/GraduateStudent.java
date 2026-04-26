package university.models.students;

import university.core.*;
import university.models.research.*;

public abstract class GraduateStudent extends Student {

	private Researcher supervisor;
	private DiplomaProject diplomaProject;
	private DegreeType degreeType;

	public void setSupervisor() {
		// TODO - implement GraduateStudent.setSupervisor
		throw new UnsupportedOperationException();
	}

	public void submitDiploma() {
		// TODO - implement GraduateStudent.submitDiploma
		throw new UnsupportedOperationException();
	}

}