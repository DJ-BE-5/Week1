package room;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class TestClientSocket{
    public static void main(String[] args) throws InterruptedException{
        Socket socket;
        try {
            socket = new Socket("localhost", 5944);
            System.out.println("Connected");
            BufferedInputStream bufferedInput = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream BufferedOutput = new BufferedOutputStream(socket.getOutputStream());


            byte[] buffer = new byte[256];
            while(socket.isConnected()){
                try{
                    int length = bufferedInput.read(buffer,0, buffer.length);
                    if(length > 0){
                        String message = new String(Arrays.copyOf(buffer,length));
                        System.out.println("Echo Receive : "+message);
                    }else if(length<0){
                        socket.close();
                    }
                }catch(IOException e){
                    System.err.println(e.getMessage());
                }
                Thread.sleep(2000);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}