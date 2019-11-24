package lab3;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Airline {
    public int code;
    public String name;

    public Airline(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public Airline(DataInputStream in) throws Exception {
        code = in.readInt();
        name = in.readUTF();
    }

    public void serialize(DataOutputStream out) throws Exception {
        out.writeInt(code);
        out.writeUTF(name);
    }

    @Override
    public String toString() {
        return "Airline{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
