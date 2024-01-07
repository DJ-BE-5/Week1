package TicTacToe;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) throws InterruptedException{
        Socket socket;
        BufferedInputStream bi = new BufferedInputStream(System.in);
        try {
            socket = new Socket("localhost", 5944);
            BufferedInputStream bufferedInput = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream BufferedOutput = new BufferedOutputStream(socket.getOutputStream());

            byte[] buffer = new byte[256];
            while(socket.isConnected()){
                try{
                    int length = bufferedInput.read(buffer,0, buffer.length);
                    if(length > 0){
                        String message = new String(Arrays.copyOf(buffer,length));
                        System.out.println(message);
                    }else if(length<0){
                        socket.close();
                    }
                    length = bufferedInput.read(buffer,0, buffer.length);
                    if(length > 0){
                        String message = new String(Arrays.copyOf(buffer,length));
                        System.out.print(message);
                    }else if(length<0){
                        socket.close();
                    }

                }catch(IOException e){
                    System.err.println(e.getMessage());
                }
                int length_key = bi.read(buffer,0,buffer.length);
                String message2 = new String(Arrays.copyOf(buffer,length_key));
                BufferedOutput.write(message2.getBytes());
                BufferedOutput.flush();

            }
            System.out.println("Client End");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
