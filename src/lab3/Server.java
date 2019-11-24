package lab3;

import common.Configs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket server = null;
    private Socket sock = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private AirServices services = new AirServices();

    public void run(int port) throws Exception {
        server = new ServerSocket(port);
        services.connect(Configs.URL, Configs.USER, Configs.PASSWORD);

        while (true) {
            sock = server.accept();

            in = new DataInputStream(sock.getInputStream());
            out = new DataOutputStream(sock.getOutputStream());

            while (processQuery()) ;
        }
    }

    public void stop() throws Exception {
        server.close();
        services.disconnect();
    }

    private boolean processQuery() {
        try {
            int oper = in.readInt();
            System.out.println(oper);

            if (oper == OperTypes.ADD_AIRLINE) {
                int code = in.readInt();
                String name = in.readUTF();
                out.writeBoolean(services.addAirline(code, name));
            } else if (oper == OperTypes.QUERY_AIRLINE) {
                int code = in.readInt();
                services.getAirline(code).serialize(out);
            } else if (oper == OperTypes.LIST_AIRLINES) {
                ArrayList<Airline> airlines = services.getAirlines();
                out.writeInt(airlines.size());
                for (Airline airline : airlines) {
                    airline.serialize(out);
                }
            } else if (oper == OperTypes.DELETE_AIRLINE) {
                int code = in.readInt();
                out.writeBoolean(services.deleteAirline(code));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        Server srv = new Server();
        srv.run(12345);
        srv.stop();
    }

}
