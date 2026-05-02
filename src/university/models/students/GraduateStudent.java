package university.models.students;

import university.exceptions.LowHIndexException;
import university.models.other.Language;
import university.models.research.*;

public abstract class GraduateStudent extends Student {

	private Researcher supervisor;
	private DiplomaProject diplomaProject;

	protected GraduateStudent(String email, String password) {
		super(email, password);
	}

	public void setSupervisor(Researcher supervisor) throws LowHIndexException {
		if (supervisor.calculateHIndex() < 3) {
			throw new LowHIndexException("Supervisor h-index is too low: must be at least 3");
		}
		this.supervisor = supervisor;
	}

	public void submitDiploma(DiplomaProject diplomaProject) {
		if (diplomaProject.getResearchPapers() == null || diplomaProject.getResearchPapers().isEmpty()) {
			throw new IllegalStateException("Diploma must have at least one research paper");
		}
		this.diplomaProject = diplomaProject;
	}

	public Researcher getSupervisor() { return supervisor; }
	public DiplomaProject getDiplomaProject() { return diplomaProject; }

	@Override
	public String toString() {
		return String.format(
				Language.INSTANCE.get("GraduateStudent.toString"),
				getStudentId(), getGpa(), supervisor
		);
	}
}