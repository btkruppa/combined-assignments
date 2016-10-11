package com.cooksys.ftd.assignments.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Client extends Thread {

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
	 */
	@Override
	public void run() {
		System.out.println("CLIENT: Client starting up");
		try {
			Config config = Utils.loadConfig("./config/config.xml", Utils.createJAXBContext());
			int port = config.getRemote().getPort();
			String host = config.getRemote().getHost();

			Socket server = new Socket(host, port);

			JAXBContext jaxb = JAXBContext.newInstance(Student.class);

			Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
			System.out.println("CLIENT: Requesting information from server");
			Student student = (Student) jaxbUnmarshaller.unmarshal(server.getInputStream());
			System.out.println("CLIENT: Student received");
			System.out.println(student.toString());

			server.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
