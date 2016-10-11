import com.cooksys.ftd.assignments.socket.Client;
import com.cooksys.ftd.assignments.socket.Server;

public class Launcher {
	
	/**
	 * Launches a server and client to operate on different threads. 
	 * The Server will await a connection and then send back student xml
	 * in response. The Cleint will connect to the server and unmarshal 
	 * the xml into a 
	 *  {@link com.cooksys.ftd.assignments.socket.model.Student} object
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		//System.out.println(Runtime.getRuntime().availableProcessors());
		Client client = new Client();
		client.start();
		Thread.sleep(2000);
		Server server = new Server();
		server.start();
		
//		boolean breakMyCode = true;
//		while(breakMyCode) {
//			new uselessThread().start();
//		}
		
	}

}
