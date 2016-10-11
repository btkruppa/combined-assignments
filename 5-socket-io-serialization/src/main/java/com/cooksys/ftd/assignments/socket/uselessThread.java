package com.cooksys.ftd.assignments.socket;

public class uselessThread extends Thread{

	public void run() {
		System.out.println(this.currentThread().getName());
		try {
			sleep(1000000);
			System.out.println("done");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("interrupted");
			e.printStackTrace();
		}
	}
	
}
