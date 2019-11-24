package lab3;

import java.util.ArrayList;

public interface IServices {
    boolean addAirline(int code, String name) throws Exception;
    Airline getAirline(int code) throws Exception;
    ArrayList<Airline> getAirlines() throws Exception;
    boolean deleteAirline(int code) throws Exception;
}
