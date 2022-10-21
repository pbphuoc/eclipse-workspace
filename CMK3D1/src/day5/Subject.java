package day5;

public class Subject {
	private String subjectID;
	private String subjectName;

	public Subject(String subjectID, String subjectName) {
		this.subjectID = subjectID;
		this.subjectName = subjectName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public String getSubjectID() {
		return subjectID;
	}
}
