package com.cooksys.ftd.assignments.concurrency;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBException;

import com.cooksys.ftd.assignments.concurrency.model.config.Config;

import Util.Debugger;

public class ClientMain {
	public static void main(String[] args) throws JAXBException, InterruptedException {
		Path configPath = Paths.get("./config/config.xml");
		Config config = Config.load(configPath);

		// start the thread to configure client instances if they are not disabeled
		if (!config.getClient().isDisabled()) {
			Client client = new Client(config.getClient());
			Debugger.debugMessage("Starting up client manager on new thread");
			new Thread(client).start();

		}

		while (true) {
			Thread.sleep(2000);
			System.out.println(Thread.activeCount());
		}
	}
}
