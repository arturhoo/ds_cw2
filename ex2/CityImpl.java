import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/**
 * @(#)CityImpl.java
 * @author Karim Djemame
 * October 2010
 */


public class CityImpl extends UnicastRemoteObject implements City {
    private String name = "";         // city name
    private String country = "";         // country name
    private int minTemperature = 0;   // min. temperature
    private int maxTemperature = 0;   // max. temperature


    // Create a new City with the given name

   public CityImpl(String str) throws RemoteException {
      name = str;
   }

    // return the City's country name

    public String getCountry() throws RemoteException{
      return country;
   }


    // return the City's min temperature

   public int getMinTemperature() throws RemoteException {
      return minTemperature;
   }

      // return the City's max temperature

   public int getMaxTemperature() throws RemoteException {
      return maxTemperature;
   }

      // return the City's average temperature

   public double getAvgTemperature() throws RemoteException {
      double avgTemperature = (maxTemperature+minTemperature)/2;
      return avgTemperature;
   }

    // set the City's min temperature

   public void setCountry(String country) throws RemoteException {
      this.country = country;
   }

    // set the City's min temperature

   public void setMinTemperature(int min) throws RemoteException {
    this.minTemperature = min;
   }

      // return the City's max temperature

   public void setMaxTemperature(int max) throws RemoteException {
    this.maxTemperature = max;
   }
}
