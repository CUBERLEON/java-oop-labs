package lab2;

import java.util.HashMap;


public class Main {
    public static String URL = "jdbc:mariadb://localhost:3306/airservices";
    public static String USER = "root";
    public static String PASSWORD = "123";

    public static void main(String[] args) throws Exception {
        example1();
        example2();
        example3();
        example4();
    }

    public static AirServices getServicesInstance() throws Exception {
        AirServices services = new AirServices();
        services.connect(URL, USER, PASSWORD);
        return services;
    }

    public static void example1() throws Exception {
        System.out.println("\nExample 1");
        AirServices services = getServicesInstance();

        assert services.addAirline(3, "BBB");
        System.out.println(services.getAirlines());
        assert services.getAirline(3).name.equals("BBB");
        assert services.deleteAirline(3);
        System.out.println(services.getAirlines());

        services.disconnect();
    }

    public static void example2() throws Exception {
        System.out.println("\nExample 2");
        AirServices services = getServicesInstance();

        System.out.println(services.getFlights());
        services.addFlight(5, "RA5324", "KPB", "WAW", "Airbus 320",
                "2019-12-17 16:20:00", "2019-12-17 17:30:00", 1);
        System.out.println(services.getFlights());
        assert services.deleteFlight(5);
        System.out.println(services.getFlights());

        services.disconnect();
    }

    public static void example3() throws Exception {
        System.out.println("\nExample 3");
        AirServices services = getServicesInstance();

        System.out.println(services.getFlightsByAirline(1));
        System.out.println(services.getFlightsByAirline(2));

        services.disconnect();
    }

    public static void example4() throws Exception {
        System.out.println("\nExample 4");
        AirServices services = getServicesInstance();

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

        services.disconnect();
    }
}
