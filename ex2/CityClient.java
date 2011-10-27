import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @(#)CityClient.java
 * @author Artur Rodrigues
 * October 2011
 * Based on previous examples
 */

public class CityClient {
	public static void main(String[] argv) {
		
	    String host = "cslin023.leeds.ac.uk";

	    try {
	    	if (argv.length > 1) {

		        // Don't strictly need to install security manager unless
		        // client will be downloading class files (e.g. stubs)
		        // across the network
		        System.setSecurityManager(new RMISecurityManager());

		        // Look up account object using RMI registry
		        Registry registry = LocateRegistry.getRegistry(host);
		        City city = (City)registry.lookup(argv[0]);

		        // Update city
		        city.setCountry(argv[1]);

		        // Update minTemp and maxTemp
		        int minTemp = Integer.parseInt(argv[2]);
		        int maxTemp = Integer.parseInt(argv[3]);
		        city.setMinTemperature(minTemp);
		        city.setMaxTemperature(maxTemp);

		        // Report on new city details
		        System.out.print("The city " + argv[0] + "has is now part of the country " + argv[1]);
		        System.out.print(",and its minimum temperature was set to " + argv[2]);
		        System.out.println(" and its maximun temperature to " + argv[3]);

	      	}
	      	else {
	        	System.err.println(
	         		"usage: java [-Djava.security.policy=...] city country minTemp maxTemp");
	        	System.exit(1);
	      	}
	    }
	    catch (Exception error) {
	    	System.out.println(error);
	    	System.exit(1);
	    }
	}
}
