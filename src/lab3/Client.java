package lab3;

import db.DbExamples;

public class Client {
    private static String URL = "localhost";
    private static int PORT = 12345;

    public static void main(String[] args) throws Exception {
        DbExamples examples = new DbExamples(getServicesInstance());
        examples.example1();
//        examples.example2();
//        examples.example3();
//        examples.example4();
    }

    public static SocketServices getServicesInstance() throws Exception {
        SocketServices services = new SocketServices(URL, PORT);
        return services;
    }
}
