package com.cooksys.ftd.assignments.socket;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Server extends Utils {

	/**
	 * Reads a {@link Student} object from the given file path
	 *
	 * @param studentFilePath
	 *            the file path from which to read the student config file
	 * @param jaxb
	 *            the JAXB context to use during unmarshalling
	 * @return a {@link Student} object unmarshalled from the given file path
	 * @throws JAXBException
	 */
	public static Student loadStudent(String studentFilePath, JAXBContext jaxb) throws JAXBException {
		Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
		File filePath = new File(studentFilePath);
		Student student = (Student) jaxbUnmarshaller.unmarshal(filePath);

		return student;
	}

	/**
	 * The server should load a
	 * {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
	 * <project-root>/config/config.xml path, using the "port" property of the
	 * embedded {@link com.cooksys.ftd.assignments.socket.model.LocalConfig}
	 * object to create a server socket that listens for connections on the
	 * configured port.
	 *
	 * Upon receiving a connection, the server should unmarshal a
	 * {@link Student} object from a file location specified by the config's
	 * "studentFilePath" property. It should then re-marshal the object to xml
	 * over the socket's output stream, sending the object to the client.
	 *
	 * Following this transaction, the server may shut down or listen for more
	 * connections.
	 * 
	 * @throws JAXBException
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws JAXBException, UnknownHostException, IOException {
		JAXBContext jaxbStudent = JAXBContext.newInstance(Student.class);
		JAXBContext jaxbConfig = Utils.createJAXBContext();
		Config config = loadConfig("./config/config.xml", jaxbConfig);
		int port = config.getLocal().getPort();
		ServerSocket server = new ServerSocket(port);
		boolean bool = true;
		while (bool) {

			

			Socket client = server.accept();

			Marshaller jaxbMarshaller = jaxbStudent.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			Student student = loadStudent("./config/student.xml", jaxbStudent);

			jaxbMarshaller.marshal(student, client.getOutputStream());

			client.close();
			
			
		}
		server.close();

	}
}
