package lab4;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

public class Client {
    private static final int PORT = 12300;

    private static IServices services;

    public static void main(String[] args) throws Exception {
        services = getServicesInstance();
        example1();
        example2();
        example3();
        example4();
    }

    public static IServices getServicesInstance() throws Exception {
        Registry registry = LocateRegistry.getRegistry(PORT);
        IServices stub = (IServices) registry.lookup("IServices");
        return stub;
    }

    public static void example1() throws Exception {
        System.out.println("\nExample 1");

        assert services.addAirline(3, "BBB");
        System.out.println(services.getAirlines());
        assert services.getAirline(3).name.equals("BBB");
        assert services.deleteAirline(3);
        System.out.println(services.getAirlines());
    }

    public static void example2() throws Exception {
        System.out.println("\nExample 2");

        System.out.println(services.getFlights());
        services.addFlight(5, "RA5324", "KPB", "WAW", "Airbus 320",
                "2019-12-17 16:20:00", "2019-12-17 17:30:00", 1);
        System.out.println(services.getFlights());
        assert services.deleteFlight(5);
        System.out.println(services.getFlights());
    }

    public static void example3() throws Exception {
        System.out.println("\nExample 3");

        System.out.println(services.getFlightsByAirline(1));
        System.out.println(services.getFlightsByAirline(2));
    }

    public static void example4() throws Exception {
        System.out.println("\nExample 4");

        System.out.println(services.getFlightsByAirline(1));
        assert services.updateFlight(1, new HashMap<String, String>() {{
            put("airport_to", "LWO");
            put("arrival", "2019-12-17 17:45:00");
        }});
        System.out.println(services.getFlightsByAirline(1));
        assert services.updateFlight(1, new HashMap<String, String>() {{
            put("airport_to", "WAW");
            put("arrival", "2019-12-17 17:30:00");
        }});
    }
}