package lab3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class RemoteServices implements IServices {
    private Socket sock = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;

    public RemoteServices(String ip, int port) throws IOException {
        sock = new Socket(ip, port);
        in = new DataInputStream(sock.getInputStream());
        out = new DataOutputStream(sock.getOutputStream());
    }

    public void disconnect() throws IOException {
        sock.close();
    }

    public boolean addAirline(int code, String name) throws Exception {
        out.writeInt(OperTypes.ADD_AIRLINE);
        out.writeInt(code);
        out.writeUTF(name);
        return in.readBoolean();
    }

    public Airline getAirline(int code) throws Exception {
        out.writeInt(OperTypes.QUERY_AIRLINE);
        out.writeInt(code);
        return new Airline(in);
    }

    public ArrayList<Airline> getAirlines() throws Exception {
        out.writeInt(OperTypes.LIST_AIRLINES);
        ArrayList<Airline> airlines = new ArrayList<>();
        int cnt = in.readInt();
        for (int i = 0; i < cnt; ++i) {
            airlines.add(new Airline(in));
        }
        return airlines;
    }

    public boolean deleteAirline(int code) throws Exception {
        out.writeInt(OperTypes.DELETE_AIRLINE);
        out.writeInt(code);
        return in.readBoolean();
    }
}
