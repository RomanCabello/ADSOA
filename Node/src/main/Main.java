package main;

import server.Server;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PoolHandler brain = new PoolHandler();
		new Server(brain).start();
	}

}
