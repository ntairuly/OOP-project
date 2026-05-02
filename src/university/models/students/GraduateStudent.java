package university.models.students;

import university.core.*;
import university.exceptions.LowHIndexException;
import university.models.courses.Course;
import university.models.other.Language;
import university.models.other.StudentOrganization;
import university.models.research.*;

import java.util.List;

public abstract class GraduateStudent extends Student {




	private Researcher supervisor;
	private DiplomaProject diplomaProject;
	private DegreeType degreeType;


	public GraduateStudent(String email, String password, String studentId, double gpa, int credits, int year, String major, List<Course> courses, int failCount, StudentOrganization organization,Researcher supervisor,DiplomaProject diplomaProject,DegreeType degreeType){
		super(email, password, studentId, gpa, credits, year, major, courses, failCount, organization);
		this.supervisor = supervisor;
		this.diplomaProject = diplomaProject;
		this.degreeType = degreeType;
	}



	public void setSupervisor() {
		if (supervisor.calculateHIndex() <3){
			throw new LowHIndexException("Supervisor h-index is too low: must be at least 3");
		}
		this.supervisor = supervisor;


	}

	public void submitDiploma() {
		if (diplomaProject.getResearchPapers()==null || diplomaProject.getResearchPapers().isEmpty()){
			throw new IllegalStateException("Diploma must have at least one research paper");
		}
		this.diplomaProject = diplomaProject;
	}

	@Override
	public double getGpa() {
		return super.getGpa();
	}

	@Override
	public String getStudentId() {
		return super.getStudentId();
	}

	@Override
	public String toString() {
		return String.format(
				Language.INSTANCE.get("GraduateStudent.toString"),
				getGpa(), getStudentId(), degreeType, supervisor
		);
	}
}