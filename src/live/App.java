package room;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class App {
    static class Receiver extends Thread{
        BufferedInputStream input;
        boolean running = false;
        public Receiver(BufferedInputStream input){
            this.input=input;
            setName("receiver");
        }
        @Override
        public void run(){
            byte[] buffer = new byte[256];
            running= true;
            while(running){
                try{
                    int length = input.read(buffer, 0, buffer.length);
                    if(length<0){
                        running=false;
                    }else{
                        System.out.println(new String(Arrays.copyOf(buffer,length)));
                    }
                }catch(IOException e){
                    System.err.println("Error : " + e.getMessage());
                    running=false;
                }
            }
        }
    }

    public static void main(String[] args) {
        try{
            String host = "localhost";
            int port = 2234;
            Socket socket = new Socket(host,port);
            BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream());
            

            Receiver receiver = new Receiver(input);
            receiver.start();

            for(int i=0; i<5; i++){
                String message = "hello [" + i +"]";
                output.write(message.getBytes());
                output.flush();
                Thread.sleep(1000);
            }

            socket.close();

        }catch(InterruptedException e){
            System.err.println("Interrupted Thread");
        }catch (UnknownHostException e){
            System.err.println("Unknown Host");
        }catch ( IOException e){
            System.err.println("I/O Error");
        }
    }
}