package lab4;

import java.io.Serializable;

public class Airline implements Serializable {
    public int code;
    public String name;

    public Airline(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
