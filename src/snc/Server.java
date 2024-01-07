package snc;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Server extends Thread {
    private int port;
    private boolean running = false;
    private BufferedInputStream bufferedInput;
    private BufferedOutputStream bufferedOutput;
    private Scanner scanner = new Scanner(System.in);


    public Server(InputStream input, OutputStream outputStream){
        bufferedInput=new BufferedInputStream(input);
        bufferedOutput=new BufferedOutputStream(outputStream);

    }

    public void stop2(){
        running=false;
    }
    public void write(String message){
        try{
            bufferedOutput.write(message.getBytes());
            bufferedOutput.flush();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run(){
        byte[] buffer = new byte[256];
        String message ="";
        running=true;

        try{

            while(running){
                int length = bufferedInput.read(buffer,0, buffer.length);
                if(length > 0){
                    message = new String(Arrays.copyOf(buffer,length));
                    System.out.println("Receive : "+message);
                }else if(length<0){
                    running=false;
                }

                write(scanner.next());
            }

        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}
