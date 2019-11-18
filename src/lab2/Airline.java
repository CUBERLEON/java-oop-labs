package lab2;

public class Airline {
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
