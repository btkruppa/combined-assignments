package com.cooksys.serialization.assignment.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Session {

	@XmlAttribute
	private String location;
	@XmlAttribute
	private String startDate;

	
	private Instructor instructor;

	@XmlElement(name = "student")
	private List<Student> students;

	public Session() {
	}

	public Session(String location, String startDate, Instructor instructor, List<Student> students) {
		super();
		this.location = location;
		this.startDate = startDate;
		this.instructor = instructor;
		this.students = students;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		for (Student student : students) {
			return "Session [location=" + location + ", startDate=" + startDate + ", instructor="
					+ instructor.getContact().toString() + "," + "studnet = " + student.getContact().toString() + "]";
		}
		return "";
	}

}
