import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @(#)CityClient2.java
 * @author Artur Rodrigues
 * October 2011
 * Based on previous examples
 */

public class CityClient2 {
	public static void main(String[] argv) {
		
	    String host = "cslin023.leeds.ac.uk";

	    try {
	    	if (argv.length > 0) {

		        // Don't strictly need to install security manager unless
		        // client will be downloading class files (e.g. stubs)
		        // across the network
		        System.setSecurityManager(new RMISecurityManager());

		        // Look up factory object using RMI registry
		        Registry registry = LocateRegistry.getRegistry(host);
		        Factory factory = (Factory)registry.lookup("Factory");

		        // Get the info
		        City city = (City)factory.getCity(argv[0]);
		        String country = city.getCountry();
		        int minTemp = city.getMinTemperature();
		        int maxTemp = city.getMaxTemperature();
		        double avgTemp = city.getAvgTemperature();

		        // Report on new city details
		        System.out.print("The city " + argv[0] + " is part of the country " + country);
		        System.out.print(", its minimum temperature is " + minTemp);
		        System.out.print(" and its maximun is " + maxTemp);
		        System.out.println(" to an average of " + avgTemp);

	      	}
	      	else {
	        	System.err.println(
	         		"usage: java [-Djava.security.policy=...] city");
	        	System.exit(1);
	      	}
	    }
	    catch (Exception error) {
	    	System.out.println(error);
	    	System.exit(1);
	    }
	}
}
