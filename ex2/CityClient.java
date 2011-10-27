import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @(#)CityImpl.java
 * @author Artur Rodrigues
 * October 2011
 * Based on previous examples
 */

public class CityClient {
	public static void main(String[] argv) {
		
		    String host = "cslin-gps.leeds.ac.uk";

		    try {
		      if (argv.length > 1) {

		        // Don't strictly need to install security manager unless
		        // client will be downloading class files (e.g. stubs)
		        // across the network
		         System.setSecurityManager(new RMISecurityManager());

		        // Look up account object using RMI registry
		        Registry registry = LocateRegistry.getRegistry(host);
		        CityImpl city = (CityImpl)registry.lookup(argv[0]);

		        // Update city
		        city.setCoutry(argv[1]);

		        // Update minTemp and maxTemp
		        city.setMinTemperature(argv[2]);
		        city.setMaxTemperature(argv[3]);

		        // Report on new city details
		        System.out.print("The city " argv[0] "has is now part of the country"+ argv[1]);
		        System.out.print("And its minimum temperature is " + argv[2]);
		        System.out.println("And its maximun temperature is " + argv[3]);

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
	}
}