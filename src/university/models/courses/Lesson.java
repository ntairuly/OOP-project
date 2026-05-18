package university.models.courses;


import java.util.Objects;
import java.time.LocalDateTime;
import university.models.employee.*;

public class Lesson {

	private LessonType lessonType;
	private Teacher teacher;
	private LocalDateTime dateTime;
	private String room;
	private Course course;

	public Lesson(LessonType lessonType, Teacher teacher, LocalDateTime dateTime, String room, Course course){
		this.lessonType = lessonType;
		this.teacher = teacher;
		this.dateTime = dateTime;
		this.room = room;
		this.course = course;
	}

	
	//геттеры
	public LessonType getLessonType() {
		return lessonType; 
	}
    public Teacher getTeacher() {
		return teacher;
	}
    public LocalDateTime getDateTime() {
		return dateTime;
	}
    public String getRoom() {
		return room;
	}
    public Course getCourse() {
		return course;
	}


	//сеттеры
	public void setRoom(String room){
		this.room = room;
	}
	public void setDateTime(LocalDateTime dateTime){
		this.dateTime = dateTime;
	}



	@Override
	public String toString(){
		return String,.format("%s | %s | Room: %s | %s", lessonType, course.getName(),room, dateTime);
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson)) return false;
        Lesson l = (Lesson) o;
        return Objects.equals(dateTime, l.dateTime) &&
               Objects.equals(room, l.room);
    }


	@Override
    public int hashCode() {
        return Objects.hash(dateTime, room);
    }


}