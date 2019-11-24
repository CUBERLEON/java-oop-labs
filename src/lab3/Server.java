package lab3;

import db.DbConfigs;
import db.DbAirServices;
import db.DbAirline;

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
    private DbAirServices services = new DbAirServices();

    public void run(int port) throws Exception {
        server = new ServerSocket(port);
        services.connect(DbConfigs.URL, DbConfigs.USER, DbConfigs.PASSWORD);

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

            if (oper == OperationTypes.ADD_AIRLINE) {
                int code = in.readInt();
                String name = in.readUTF();
                out.writeBoolean(services.addAirline(code, name));
            } else if (oper == OperationTypes.QUERY_AIRLINE) {
                int code = in.readInt();
                services.getAirline(code).serialize(out);
            } else if (oper == OperationTypes.LIST_AIRLINES) {
                ArrayList<DbAirline> airlines = services.getAirlines();
                out.writeInt(airlines.size());
                for (DbAirline airline : airlines) {
                    airline.serialize(out);
                }
            } else if (oper == OperationTypes.DELETE_AIRLINE) {
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
