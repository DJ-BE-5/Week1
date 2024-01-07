import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Async {
    static class Receiver extends Thread{
        BufferedInputStream input;

        public Receiver(BufferedInputStream input){
            this.input=input;
        }
        @Override
        public void run(){

        }
    }

    public static void main(String[] args) {
        try{
            Socket socket = new Socket("180.210.81.192",12345);
            BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream());

            Receiver receiver = new Receiver(input);

        }catch (UnknownHostException e){
            System.err.println("Unknown Host");
        }catch ( IOException e){
            System.err.println("I/O Error");
        }
    }
}
