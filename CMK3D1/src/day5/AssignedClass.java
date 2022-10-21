package day5;

public class AssignedClass {
	private String studentID;
	private String semesterID;
	private String classID;

	public AssignedClass(String studentID, String semesterID, String classID) {
		this.studentID = studentID;
		this.semesterID = semesterID;
		this.classID = classID;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getSemesterID() {
		return semesterID;
	}

	public void setSemesterID(String semesterID) {
		this.semesterID = semesterID;
	}

	public String getClassID() {
		return classID;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}

}
