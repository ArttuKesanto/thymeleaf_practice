package hh.swd.stlist.friendlist.domain;

public class Student {
	private String fullName; // Meant to use Friend as the Class name... My bad.

	public Student(String fullName) {
		super();
		this.fullName = fullName;
	}
	
	public Student() {
		super();
		this.fullName = null;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "Student [fullName=" + fullName + "]";
	}
	
	
	
	

}
