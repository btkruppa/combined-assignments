package com.cooksys.ftd.assignments.concurrency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assignments.concurrency.model.config.ClientInstanceConfig;
import com.cooksys.ftd.assignments.concurrency.model.message.Request;

import Util.ClientServerOutput;

public class ClientInstance implements Runnable {

	static Logger log = LoggerFactory.getLogger(ClientInstance.class);

	private int delay;
	private List<Request> requests;
	private Socket clientSocket;
	private String host;
	private int port;

	public ClientInstance(ClientInstanceConfig config, String host, int port) {
		this.delay = config.getDelay();
		this.requests = config.getRequests();
		this.host = host;
		this.port = port;
		// this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try {
			Socket clientSocket = new Socket(host, port);
			// make sure that the socket is not closed
			if (!clientSocket.isClosed()) {
				// initialize the readers and writers for communicating with the
				// server
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String receivedMessage;
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

				// the server will send a message confirming connection, wait
				// until we receive this to move on
				receivedMessage = reader.readLine();
						
				ClientServerOutput.clientOutput("Message received from server: " + receivedMessage);
				log.info("Client received message from server: " + receivedMessage);

				// once connection is confirmed begin sending requests one at a
				// time
				for (Request request : requests) {
					ClientServerOutput.clientOutput("Requesting " + request.getType() + " from server");
					log.info("Client is requesting " + request.getType() + " from server");
					writer.write(request.getType().name() + "\n");
					writer.flush();

					// wait until we receive a response to our request before
					// moving on to the next request
					receivedMessage = reader.readLine();
					ClientServerOutput.clientOutput("Message received from server: " + receivedMessage);
					log.info("Client received message from server: " + receivedMessage);
				}

				log.info("Client received all messages, closing client socket");
				clientSocket.close();
			}
		} catch (IOException e) {
			log.error("IOException", e);
		}
	}
}
