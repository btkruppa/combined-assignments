package com.cooksys.ftd.assignments.concurrency;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assignments.concurrency.model.config.ClientConfig;
import com.cooksys.ftd.assignments.concurrency.model.config.ClientInstanceConfig;
import com.cooksys.ftd.assignments.concurrency.model.config.SpawnStrategy;

import Util.Debugger;

public class Client implements Runnable {
	
	static Logger log = LoggerFactory.getLogger(Client.class);

	private static int port;
	private static String host;
	private static int maxInstances;
	private static SpawnStrategy spawnStrategy;
	private static List<ClientInstanceConfig> instances;

	public Client(ClientConfig config) {
		this.port = config.getPort();
		this.host = config.getHost();
		this.maxInstances = config.getMaxInstances();
		this.spawnStrategy = config.getSpawnStrategy();
		this.instances = config.getInstances();
	}

	@Override
	public void run() {
		Debugger.debugMessage("Determining client spawn Strategy" + spawnStrategy);
		try {
			switch (spawnStrategy) {
			case NONE:
				break;
			case PARALLEL:
				log.info("Starting up clients in parallel mode");
				//Debugger.debugMessage("Starting up clients in parallel mode");
				for (ClientInstanceConfig clientInstanceConfig : instances) {
					new Thread(new ClientInstance(clientInstanceConfig, host, port)).start();
					Thread.sleep(50);
				}
				break;
			case SEQUENTIAL:
				
				log.info("Starting up clients in sequential mode");
				for (ClientInstanceConfig clientInstanceConfig : instances) {
					new ClientInstance(clientInstanceConfig, host, port).run();
				}
				break;
			}
		} catch (InterruptedException e) {
			Debugger.errorStackTrace(e);
		}
	}
}
