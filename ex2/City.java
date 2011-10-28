import java.rmi.*;
import java.util.List;

/**
 * @(#)City.java
 * @author Karim Djemame
 * October 2010
 */

public interface City extends Remote {

   String getCountry() throws RemoteException;   
   int getMinTemperature() throws RemoteException;
   int getMaxTemperature() throws RemoteException;
   double getAvgTemperature() throws RemoteException;
   void setCountry(String c) throws RemoteException;
   void setMinTemperature(int min) throws RemoteException;
   void setMaxTemperature(int max) throws RemoteException;
}