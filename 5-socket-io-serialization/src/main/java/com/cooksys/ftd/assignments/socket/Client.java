package com.cooksys.ftd.assignments.socket;

import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Client {

	/**
	 * The client should load a
	 * {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
	 * <project-root>/config/config.xml path, using the "port" and "host"
	 * properties of the embedded
	 * {@link com.cooksys.ftd.assignments.socket.model.RemoteConfig} object to
	 * create a socket that connects to a {@link Server} listening on the given
	 * host and port.
	 *
	 * The client should expect the server to send a
	 * {@link com.cooksys.ftd.assignments.socket.model.Student} object over the
	 * socket as xml, and should unmarshal that object before printing its
	 * details to the console.
	 * 
	 * @throws JAXBException
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws JAXBException, UnknownHostException, IOException {
		Config config = Utils.loadConfig("./config/config.xml", Utils.createJAXBContext());
		int port = config.getRemote().getPort();
		String host = config.getRemote().getHost();
		
		Socket server = new Socket(host, port);
		
		JAXBContext jaxb = JAXBContext.newInstance(Student.class);
		
		Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
		Student student = (Student) jaxbUnmarshaller.unmarshal(server.getInputStream());
		System.out.println(student.toString());
		
		 server.close();
		
	}
}
