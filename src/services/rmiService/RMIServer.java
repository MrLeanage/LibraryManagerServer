package services.rmiService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
	public static String serverStatus = "Server Stopped";
	public static void startServer(){
		try {
			RMIInterface rmiInterface = new RMIImplementation();
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("RMIServer",rmiInterface);
			serverStatus = "Running";

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}


