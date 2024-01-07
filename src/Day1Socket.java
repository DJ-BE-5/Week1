import java.lang.*;
import java.io.*;
import java.net.Socket;

class Day1Socket {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("180.210.81.192",12345);
            System.out.println("Server Connected");

            socket.getOutputStream().write("hello".getBytes());
            byte[] buffer = new byte[256];
            int length = socket.getInputStream().read(buffer);

            System.out.println("Rec : " + length);

            socket.close();
        }catch(IOException e){
            System.err.println(e);
        }
    }
}