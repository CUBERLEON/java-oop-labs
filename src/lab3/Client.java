package lab3;

public class Client {
    private static String URL = "localhost";
    private static int PORT = 12345;

    private static IServices services;

    public static void main(String[] args) throws Exception {
        services = getServicesInstance();
        example1();
    }

    public static RemoteServices getServicesInstance() throws Exception {
        RemoteServices services = new RemoteServices(URL, PORT);
        return services;
    }

    public static void example1() throws Exception {
        System.out.println("\nExample 1");

        assert services.addAirline(3, "BBB");
        System.out.println(services.getAirlines());
        assert services.getAirline(3).name.equals("BBB");
        assert services.deleteAirline(3);
        System.out.println(services.getAirlines());
    }
}
