package university.models.courses;


import java.util.Objects;
import java.time.LocalDateTime;
import java.util.Objects;
import university.models.employee.*;


public class Lesson {

	private LessonType lessonType;
	private Teacher teacher;
	private LocalDateTime dateTime;
	private String room;
	private Course course;

	public Lesson(LessonType lessonType, Teacher teacher, LocalDateTime dateTime, String room, Course course) {
		this.lessonType = lessonType;
		this.teacher = teacher;
		this.dateTime = dateTime;
		this.room = room;
		this.course = course;
	}

	// Getters/setters
	public LessonType getLessonType() { 
		return lessonType; 
	}
	public void setLessonType(LessonType lessonType) { 
		this.lessonType = lessonType; 
	}

	public Teacher getTeacher() { 
		return teacher; 
	}
	public void setTeacher(Teacher teacher) { 
		this.teacher = teacher;
	}

	public LocalDateTime getDateTime() { 
		return dateTime; 
	}
	public void setDateTime(LocalDateTime dateTime) { 
		this.dateTime = dateTime; 
	}

	public String getRoom() { 
		return room; 
	}
	public void setRoom(String room) { 
		this.room = room; 
	}

	public Course getCourse() { 
		return course; 
	}
	public void setCourse(Course course) { 
		this.course = course; 
	}

	@Override
	public String toString() {
		return "Lesson[" +
				"type=" + lessonType +
				", teacher=" + teacher.getEmail() +
				", dateTime=" + dateTime +
				", room='" + room + '\'' +
				", course=" + course.getName() +
				"]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Lesson)) return false;
		Lesson lesson = (Lesson) o;
		return lessonType == lesson.lessonType &&
				Objects.equals(teacher, lesson.teacher) &&
				Objects.equals(dateTime, lesson.dateTime) &&
				Objects.equals(course, lesson.course);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lessonType, teacher, dateTime, course);
	}
}