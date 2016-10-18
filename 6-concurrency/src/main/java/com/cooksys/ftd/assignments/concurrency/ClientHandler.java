package com.cooksys.ftd.assignments.concurrency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Util.ClientServerOutput;

public class ClientHandler implements Runnable {

	private Socket clientSocket;
	static Logger log = LoggerFactory.getLogger(ClientHandler.class);

	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try {
			// initialize our readers and writers for communication with the
			// client
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String receivedMessage;
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			String writtenMessage;

			// send message to client acknowledging connection
			writtenMessage = "You have connected to the server.\n";
			writer.write(writtenMessage);
			writer.flush();
			ClientServerOutput.serverOutput("sent message: " + writtenMessage + " to client");

			// as long as we are connected to the client continue looking for
			// requests and responding to them
			while (!clientSocket.isClosed()) {
				// check to see if a request was made
				receivedMessage = reader.readLine();
				// receivedMessage.
				ClientServerOutput.serverOutput("Message received from Client: " + receivedMessage);
				log.info("Server received a request from a client");

				// case statement to determine what request was made and
				// fulfill it
				switch (receivedMessage) {
				case "IDENTITY":
					writer.write("Identity: " + clientSocket.getRemoteSocketAddress() + "\n");
					writer.flush();
					ClientServerOutput.serverOutput("Sent identity back to client");
					break;
				case "TIME":
					writer.write("Time: " + LocalDateTime.now() + "\n");
					writer.flush();
					ClientServerOutput.serverOutput("Sent time back to client");
					break;
				default:
					writer.write("Request unknown\n");
					break;
				}
				log.info("Server sent response to client");

			}
			log.info("Ending a Client Handler");
		} catch (IOException e) {
			try {
				clientSocket.close();
			} catch (IOException e1) {
				log.error("IOException", e1);
			}
			log.error("IOException", e);
		}
	}
}