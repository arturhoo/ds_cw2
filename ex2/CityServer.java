import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/**
  * @author K. Djemame
  * October 2010
 */

public class CityServer {

  public static void main(String[] argv)
  {
    try {
      if (argv.length > 0) {

        // Could install a security manager by uncommenting line below,
        // but this isn't really necessary unless our server is itself an
        // RMI client of another server somewhere...

        //System.setSecurityManager(new RMISecurityManager());

        // Make a City with the desired name

        CityImpl city = new CityImpl(argv[0]);

        // Register the object with the local RMI registry

        Naming.rebind(argv[0], city);
        //     <---->
        //     Can use bind instead if we are sure that
        //     no other object is bound to this name

        System.out.println(
         "Registered city " + argv[0] + "...");

        // Server does not terminate here, despite there being
        // no more code to execute; this is because creation of
        // an CityImpl object starts another thread, to
        // service remote invocations...

      }
      else {
        System.err.println("usage: java CityServer cityName");
        System.exit(1);
      }
    }
    catch (Exception error) {
      System.err.println(error);
      System.exit(1);
    }
  }
}
