package lab4;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Map;

public interface IServices extends Remote {
    boolean addAirline(int code, String name) throws Exception;
    Airline getAirline(int code) throws Exception;
    ArrayList<Airline> getAirlines() throws Exception;
    boolean deleteAirline(int code) throws Exception;

    boolean addFlight(int code, String name, String airportFrom, String airportTo,
                      String aircraft, String departure, String arrival,
                      int airlineCode) throws Exception;
    boolean updateFlight(int code, Map<String,String> changes) throws Exception;
    Flight getFlight(int code) throws Exception;
    ArrayList<Flight> getFlights() throws Exception;
    ArrayList<Flight> getFlightsByAirline(int airlineCode) throws Exception;
    boolean deleteFlight(int code) throws Exception;
}