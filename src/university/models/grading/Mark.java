package university.models.grading;

import java.util.Objects;
import university.models.courses.*;
import university.models.students.Student;

public class Mark {

	private Student student;
	private Course course;
	private double firstAttestation;
	private double secondAttestation;
	private double finalExam;

	public Mark(Student student, Course course, double firstAttestation, 
					double secondAttestation, double finalExam){
		

		if(firstAttestation < 0 || firstAttestation > 30){
			throw new IllegalArgumentException(
				"First attestation must be 0-30! Got: " + firstAttestation
			);
		}

		if(secondAttestation < 0 || secondAttestation > 30){
			throw new IllegalArgumentException(
				"Second attestation must be 0-30! Got: " + secondAttestation
			);
		}

		if(finalExam < 0 || finalExam > 40){
			throw new IllegalArgumentException(
				"Final exam must be 0-40! Got: " + finalExam
			);
		}

		this.student = student;
		this.course = course;
		this.firstAttestation = firstAttestation;
		this.secondAttestation = secondAttestation;
		this.finalExam = finalExam;		
	}

	public String getLetterGrade() {
		// TODO - implement Mark.getLetterGrade
		throw new UnsupportedOperationException();
	}
	public double getGpaPoints() {
		double total = getTotal();
		if(total >= 95){
			return 4.0;
		}
		if(total >= 90){
			return 3.7;
		}
		if(total >= 85){
			return 3.3;
		}
		if(total >= 80){
			return 3.0;
		}
		if(total >= 75){
			return 2.7;
		}
		if(total >= 70){
			return 2.3;
		}
		if(total >= 65){
			return 2.0;
		}
		if(total >= 60){
			return 1.7;
		}
		if(total >= 55){
			return 1.3;
		}
		if(total >= 50){
			return 1.0;
		}
		else{
			return 0.0;
		}
	}


    // геттеры
	public boolean isPassed() {
		return getTotal() >= 50;
	}

    public double getTotal() {
        return firstAttestation+secondAttestation+finalExam;
    }

	public Student getStudent(){
		return student;
	}

	public Course getCourse(){
		return course;
	}

	public double getFirstAttestation(){
		return firstAttestation;
	}

	public double getSecondAttestation(){
		return secondAttestation;
	}

	public double getFinalExam(){
		return finalExam;
	}


	// сетттеры
	public void setFirstAttestation(double v){
		this.firstAttestation = v;
	}

	public void setSecondAttestation(double v){
		this.secondAttestation = v;
	}

	public void setFinalExam(double v){
		this.finalExam = v;
	}


	public int compareTo(Mark other){
		return Double.compare(other.getTotal(), this.getTotal());
	}

	@Override
	public String toString(){
		return String.format("Course: %s  |  ATT1: %.1f  |  ATT2: %.1f  |  Final: %.1f  |  Total: %.1f   |  Grade: %s",
			course.getName(), firstAttestation, secondAttestation, 
			finalExam, getTotal(), getLetterGrade()
		);
	}


	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(!(o instanceof Mark)) return false;
		Mark m = (Mark) o;
		return Objects.equals(student, m.student) && Objects.equals(course, m.course);
	}



	@Override
	public int hashCode(){
		return Objects.hash(student, course);
	}
}