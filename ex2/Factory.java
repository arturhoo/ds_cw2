import java.rmi.*;
import java.util.List;

/**
 * @(#)Factory.java
 * @author Artur Rodrigues
 * October 2010
 */

public interface Factory extends Remote {
	CityImpl getCity(String name) throws RemoteException;
}