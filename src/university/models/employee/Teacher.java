package university.models.employee;

import java.util.List;
import university.models.courses.*;
import university.models.research.*;
import university.models.students.*;


public class Teacher extends Employee implements Researcher {

	private TeacherTitle title;
	private List<Course> courses;
	private double rating;
	private boolean isProfessor;


	public Teacher(String email, String password){
		super(email, password);
	}


	public void putMark() {
		// TODO - implement Teacher.putMark
		throw new UnsupportedOperationException();
	}

	public void sendComplaint() {
		// TODO - implement Teacher.sendComplaint
		throw new UnsupportedOperationException();
	}

	public List<Student> viewStudents() {
		// TODO - implement Teacher.viewStudents
		throw new UnsupportedOperationException();
	}

	public String getTranscript() {
		// TODO - implement Teacher.getTranscript
		throw new UnsupportedOperationException();
	}

	@Override
	public void printPapers(){
		throw new UnsupportedOperationException();
	};

    @Override
	public int calculateHIndex(){
		throw new UnsupportedOperationException();
	};

	@Override
	public void addPaper(){
		throw new UnsupportedOperationException();
	};

	@Override
	public List<ResearchPaper> getPapers(){
		throw new UnsupportedOperationException();
	};

	@Override
	public List<ResearchProject> getProjects(){
		throw new UnsupportedOperationException();
	};

	@Override
	public void joinProject(){
		throw new UnsupportedOperationException();
	};

	@Override
	public void update(){
		// TODO - implement Admin.update
		throw new UnsupportedOperationException();
	}

}