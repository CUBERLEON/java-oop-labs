package lab4;

import common.Configs;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    private static final int PORT = 12300;

    public static void main(String args[]) throws Exception {
        AirServices services = new AirServices();
        services.connect(Configs.URL, Configs.USER, Configs.PASSWORD);

        IServices stub = (IServices) UnicastRemoteObject.exportObject(services, 0);

        Registry registry = LocateRegistry.createRegistry(PORT);

        registry.bind("IServices", stub);

        System.err.println("Server ready");
    }


}