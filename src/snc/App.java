package snc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class App {
    public static void main(String[] args) {
        String command = args[0];
        Client client;
        Server server;


        if((command.charAt(0)=='-' && command.charAt(1)=='l') && command.length() > 1) {
            String[] tok = args[1].split(" ");
            int port = Integer.parseInt(tok[0]);

            try(ServerSocket serverSocket = new ServerSocket(port)){
                while(!serverSocket.isClosed()){
                    Socket socket = serverSocket.accept();
                    server= new Server(socket.getInputStream(), socket.getOutputStream());
                    server.start();
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }else {
            String addr = args[0];
            int port = Integer.parseInt(args[1]);
            client = new Client(addr,port);
            client.run();
        }
    }
}
