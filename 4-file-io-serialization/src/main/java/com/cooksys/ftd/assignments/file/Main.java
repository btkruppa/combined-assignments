package com.cooksys.ftd.assignments.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.file.model.Contact;
import com.cooksys.ftd.assignments.file.model.Instructor;
import com.cooksys.ftd.assignments.file.model.Session;
import com.cooksys.ftd.assignments.file.model.Student;

public class Main {

	/**
	 * Creates a {@link Student} object using the given studentContactFile. The
	 * studentContactFile should be an XML file containing the marshaled form of
	 * a {@link Contact} object.
	 *
	 * @param studentContactFile
	 *            the XML file to use
	 * @param jaxb
	 *            the JAXB context to use
	 * @return a {@link Student} object built using the {@link Contact} data in
	 *         the given file
	 */
	public static Student readStudent(File studentContactFile, JAXBContext jaxb) throws JAXBException {
		try {
			Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
			Contact contact = (Contact) jaxbUnmarshaller.unmarshal(studentContactFile);
			System.out.println(contact);

			return new Student(contact);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Creates a list of {@link Student} objects using the given directory of
	 * student contact files.
	 *
	 * @param studentDirectory
	 *            the directory of student contact files to use
	 * @param jaxb
	 *            the JAXB context to use
	 * @return a list of {@link Student} objects built using the contact files
	 *         in the given directory
	 */
	public static List<Student> readStudents(File studentDirectory, JAXBContext jaxb) throws JAXBException {
		try {
			List<Student> studentList = new ArrayList<>();
			for (File studentFile : studentDirectory.listFiles()) {
				studentList.add(readStudent(studentFile, jaxb));
			}
			return studentList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Creates an {@link Instructor} object using the given
	 * instructorContactFile. The instructorContactFile should be an XML file
	 * containing the marshaled form of a {@link Contact} object.
	 *
	 * @param instructorContactFile
	 *            the XML file to use
	 * @param jaxb
	 *            the JAXB context to use
	 * @return an {@link Instructor} object built using the {@link Contact} data
	 *         in the given file
	 */
	public static Instructor readInstructor(File instructorContactFile, JAXBContext jaxb) throws JAXBException {
		try {
			Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
			Contact contact = (Contact) jaxbUnmarshaller.unmarshal(instructorContactFile);
			System.out.println(contact);

			return new Instructor(contact);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Creates a {@link Session} object using the given rootDirectory. A
	 * {@link Session} root directory is named after the location of the
	 * {@link Session}, and contains a directory named after the start date of
	 * the {@link Session}. The start date directory in turn contains a
	 * directory named `students`, which contains contact files for the students
	 * in the session. The start date directory also contains an instructor
	 * contact file named `instructor.xml`.
	 *
	 * @param rootDirectory
	 *            the root directory of the session data, named after the
	 *            session location
	 * @param jaxb
	 *            the JAXB context to use
	 * @return a {@link Session} object built from the data in the given
	 *         directory
	 */
	public static Session readSession(File rootDirectory, JAXBContext jaxb) throws JAXBException {
		try {
			Session session = new Session();
			for (File location : rootDirectory.listFiles()) {
				session.setLocation(location.getName());
				for (File date : location.listFiles()) {
					session.setStartDate(date.getName());
					for (File studentDir : date.listFiles()) {
						if (studentDir.isDirectory()) {
							System.out.println(studentDir.getName());
							List<Student> students = readStudents(studentDir, jaxb);
							session.setStudents(students);
						} else {
							session.setInstructor(readInstructor(studentDir, jaxb));
						}
					}
				}
			}
			return session;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Creates a {@link Session} object using the given XML Session file path
	 *
	 * @param rootDirectory
	 *            the root directory of the session data, named after the
	 *            session location
	 * @param jaxb
	 *            the JAXB context to use
	 * @return a {@link Session} object built from the data in the given
	 *         directory
	 */
	public static Session readSessionXmlFile(File sessionXml, JAXBContext jaxb) throws JAXBException {

		try {
			Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
			Session session = (Session) jaxbUnmarshaller.unmarshal(sessionXml);
			System.out.println(session);
			return session;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Writes a given session to a given XML file
	 *
	 * @param session
	 *            the session to write to the given file
	 * @param sessionFile
	 *            the file to which the session is to be written
	 * @param jaxb
	 *            the JAXB context to use
	 */
	public static void writeSession(Session session, File sessionFile, JAXBContext jaxb) throws JAXBException {

		try {
			Marshaller jaxbMarshaller = jaxb.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(session, System.out);

			jaxbMarshaller.marshal(session, sessionFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Main Method Execution Steps: 1. Configure JAXB for the classes in the
	 * com.cooksys.serialization.assignment.model package 2. Read a session
	 * object from the <project-root>/input/memphis/ directory using the methods
	 * defined above 3. Write the session object to the
	 * <project-root>/output/session.xml file.
	 *
	 * JAXB Annotations and Configuration: You will have to add JAXB annotations
	 * to the classes in the com.cooksys.serialization.assignment.model package
	 *
	 * Check the XML files in the <project-root>/input/ directory to determine
	 * how to configure the {@link Contact} JAXB annotations
	 *
	 * The {@link Session} object should marshal to look like the following:
	 * <session location="..." start-date="..."> <instructor> <contact>...
	 * </contact> </instructor> <students> ...
	 * <student> <contact>...</contact> </student> ... </students> </session>
	 */
	public static void main(String[] args) throws JAXBException {

		Contact contact = new Contact("Blake", "Kruppa", "btkruppa513@gmail.com", "9093804081");
		Contact contact2 = new Contact("Bill", "Jones", "email", "phone");
		Instructor instructor = new Instructor(contact);
		Student student = new Student(contact2);

		List<Student> studentList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			studentList.add(student);
		}

		Session session = new Session("here", "today", instructor, studentList);

		JAXBContext jaxbSessionContext = JAXBContext.newInstance(Session.class);
		JAXBContext jaxbStudentContext = JAXBContext.newInstance(Student.class);

		File file = new File("./output/session.xml");

		session = readSession(new File("./input"), jaxbSessionContext);
		writeSession(session, file, jaxbSessionContext);
		// readSessionXml(file, jaxbSessionContext);
		// readStudent(new
		// File("./input/memphis/08-08-2016/students/adam-fraser.xml"),
		// jaxbStudentContext);
		// readStudents(new File("./input/memphis/08-08-2016/students/"),
		// jaxbStudentContext);
		// readInstructor(new File("./input/memphis/08-08-2016/instructor.xml"),
		// jaxbSessionContext);
	}
}
