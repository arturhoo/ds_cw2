import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
  * @author K. Djemame
  * October 2010
 */

public class CityServer {

  public static final String propsFile = "jdbc.properties";

  /**
   * Establishes a connection to the database.
   *
   * @return Connection object representing the connection
   * @throws IOException if properties file cannot be accessed
   * @throws SQLException if connection fails
   */

  public static Connection getConnection() throws IOException, SQLException
  {
    // Load properties
    FileInputStream in = new FileInputStream(propsFile);
    Properties props = new Properties();
    props.load(in);

    // Define JDBC driver
    String drivers = props.getProperty("jdbc.drivers");
    if (drivers != null)
      System.setProperty("jdbc.drivers", drivers);
      // Setting standard system property jdbc.drivers
      // is an alternative to loading the driver manually
      // by calling Class.forName()

    // Obtain access parameters and use them to create connection
    String url = props.getProperty("jdbc.url");
    String user = props.getProperty("jdbc.user");
    String password = props.getProperty("jdbc.password");

    return DriverManager.getConnection(url, user, password);
  }

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

        // Defines the connection and queries for the city the
        // server was initiated with
        Connection connection = null;
        try {
          connection = getConnection();
          Statement statement = connection.createStatement();
          ResultSet results = statement.executeQuery(
            "SELECT * FROM cities WHERE name ='" + argv[0] + "'");
          while (results.next()) {
            city.setCountry(results.getString("country"));
            city.setMinTemperature(Integer.parseInt(results.getString("minTemperature")));
            city.setMaxTemperature(Integer.parseInt(results.getString("maxTemperature")));
          }
          statement.close();
        }
        catch (Exception error) {
          error.printStackTrace();
        }

        // Register the object with the local RMI registry
        Naming.rebind(argv[0], city);
       
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
