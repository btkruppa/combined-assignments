package com.cooksys.ftd.assignments.concurrency;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assignments.concurrency.model.config.ServerConfig;

import Util.Debugger;

public class Server implements Runnable {
	static Logger log = LoggerFactory.getLogger(Server.class);
	
	private int port;
	private int maxClients;

	public Server(ServerConfig config) {
		log.info("configuring server");
		this.port = config.getPort();
		this.maxClients = config.getMaxClients();
		log.info("server configured");
	}

	@Override
	public void run() {

		try {
			log.info("Starting server on port " + port);
			ServerSocket serverSocket = new ServerSocket(port);
 
			while (true) {
				log.info("Server is waiting for client to connect");
				Socket client = serverSocket.accept();
				log.info("Client accepted, creating new thread for client handler");
				new Thread(new ClientHandler(client)).start();
			}

		} catch (IOException e) {
			Debugger.errorStackTrace(e);
		}
	}
}
