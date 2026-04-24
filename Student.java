public class Student extends User {

	private string studentId;
	private double gpa;
	private int credits;
	private int year;
	private string major;
	private List<Course> courses;
	private int failCount;
	private StudentOrganization organization;

	public void registerForCourse() {
		// TODO - implement Student.registerForCourse
		throw new UnsupportedOperationException();
	}

	public Map<Course, Mark> viewMarks() {
		// TODO - implement Student.viewMarks
		throw new UnsupportedOperationException();
	}

	public string getTranscript() {
		// TODO - implement Student.getTranscript
		throw new UnsupportedOperationException();
	}

	public void rateTeacher() {
		// TODO - implement Student.rateTeacher
		throw new UnsupportedOperationException();
	}

	public double getGpa() {
		return this.gpa;
	}

}