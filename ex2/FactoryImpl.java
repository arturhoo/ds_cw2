import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * @(#)FactoryImpl.java
 * @author Artur Rodrigues
 * October 2011
 * Based on previous examples
 */


public class FactoryImpl extends UnicastRemoteObject implements Factory {
	Connection connection = null;
	// Constructor
	public FactoryImpl(Connection connection) throws RemoteException {
		this.connection = connection;		
	}

	// Returns a city based on the name given
	public City getCity(String name) throws RemoteException {
		City city = new CityImpl(name);
		try {
		    Statement statement = this.connection.createStatement();
		    ResultSet results = statement.executeQuery(
		    	"SELECT * FROM cities WHERE name ='" + name + "'");
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
		return city;
	}
}
